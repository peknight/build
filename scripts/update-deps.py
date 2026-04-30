#!/usr/bin/env python3
"""自动检索并更新 build 模块外部依赖的最新版本号。

默认 dry-run 模式（仅打印变更），使用 --apply 参数实际写入。
"""

import argparse
import json
import re
import urllib.request
import xml.etree.ElementTree as ET
from pathlib import Path

# Maven metadata XML 端点：实时获取所有版本
MAVEN_META_URL = "https://repo.maven.apache.org/maven2/{group_path}/{artifact}/maven-metadata.xml"

# Docker Hub API for eclipse-temurin
DOCKER_API = "https://hub.docker.com/v2/repositories/library/eclipse-temurin/tags?page_size=200"

# 排除列表：不更新的依赖名（object 名称）
EXCLUDE_NAMES = {
    "http4s",          # http4s 下所有
    "catsParse",       # cats-parse
}

# @version-check URL 正则：匹配 Scala 块注释格式
BLOCK_RE = re.compile(r"/\*\*\s*@version-check\s*(https?://[^\s]+)\s*\*/")

# @version-check URL 正则：匹配单行注释格式
LINE_RE = re.compile(r"//\s*@version-check\s*(https?://[^\s]+)")

# @version-check URL 正则：匹配 properties 注释格式
PROP_RE = re.compile(r"#\s*@version-check\s*(https?://[^\s]+)")

# Maven Central URL 正则：从注释 URL 中提取 groupId 和 artifactId
MAVEN_URL_RE = re.compile(r"https://repo\.maven\.apache\.org/maven2/(.+)/")

# Docker Hub 版本号正则
DOCKER_TAG_RE = re.compile(r"(\d+)_(\d+)-jdk")


def _fetch_maven_metadata(group: str, artifact: str) -> list[str] | None:
    """从 maven-metadata.xml 获取所有版本号列表。

    优先使用完整 artifact 名（如 scalacheck_3），若不存在则尝试去除
    Scala/sbt 平台后缀（_3, _2.12, _2.12_1.0 等）。
    """
    group_path = group.replace(".", "/")

    # 先尝试用完整 artifact 名
    url = MAVEN_META_URL.format(group_path=group_path, artifact=artifact)
    result = _try_fetch_metadata_url(url)
    if result is not None:
        return result

    # 去后缀重试
    suffixes = ["_3", "_2.12", "_2.13", "_2.11", "_2.12_1.0", "_2.12_1.1", "_2.13_1.0", "_3_1.0"]
    base_artifact = artifact
    for suffix in suffixes:
        if base_artifact.endswith(suffix):
            base_artifact = base_artifact[: -len(suffix)]
            break

    if base_artifact != artifact:
        url = MAVEN_META_URL.format(group_path=group_path, artifact=base_artifact)
        return _try_fetch_metadata_url(url)

    return None


def _try_fetch_metadata_url(url: str) -> list[str] | None:
    """尝试获取 maven-metadata.xml，失败返回 None。"""
    try:
        req = urllib.request.Request(url)
        req.add_header("User-Agent", "peknight-build-update-deps/1.0")
        with urllib.request.urlopen(req, timeout=15) as resp:
            xml_data = resp.read().decode()
            root = ET.fromstring(xml_data)
            return [v.text for v in root.findall(".//version")]
    except Exception:
        return None


def is_prerelease(version: str) -> bool:
    """判断版本是否为预发布版本。"""
    markers = ["rc", "m", "snapshot", "snap", "alpha", "beta", "cr", "dev"]
    lower = version.lower()
    for m in markers:
        if f"-{m}" in lower or lower.startswith(m):
            return True
    return False


def _parse_version_tuple(version: str) -> tuple:
    """将版本号字符串解析为可比较的 tuple。"""
    parts = []
    for p in version.split("."):
        try:
            parts.append(int(p))
        except ValueError:
            num = ""
            for c in p:
                if c.isdigit():
                    num += c
                else:
                    break
            parts.append(int(num) if num else 0)
    return tuple(parts)


def _is_literal_version(value: str) -> bool:
    """判断版本号是否为字面量（如 "1.84"）而非引用（如 bouncyCastle.version）。"""
    if "." in value and not value[0].isdigit():
        return False
    return bool(re.match(r'^\d', value))


def _is_version_newer(current: str, candidate: str) -> bool:
    """判断 candidate 版本是否严格高于 current 版本。

    只比较共同的数字位数，忽略后缀差异（如 2.0.61.android5 vs 2.0.61 视为同等）。
    """
    cur = _parse_version_tuple(current)
    cand = _parse_version_tuple(candidate)
    min_len = min(len(cur), len(cand))
    # 只比较共同的数字部分
    return cand[:min_len] > cur[:min_len]


def get_latest_matching_version(group: str, artifact: str, current: str) -> str | None:
    """根据智能策略获取最新版本。

    - 当前是稳定版 → 只返回稳定版
    - 当前是预发布 → 返回最新版本（无论稳定或预发布）

    只有当最新版的数字 tuple 严格高于当前版时才返回。
    """
    versions = _fetch_maven_metadata(group, artifact)
    if versions is None:
        return None

    if is_prerelease(current):
        versions.sort(key=_parse_version_tuple, reverse=True)
        latest = versions[0]
        if _is_version_newer(current, latest):
            return latest
    else:
        stable = [v for v in versions if not is_prerelease(v)]
        if not stable:
            return None
        stable.sort(key=_parse_version_tuple, reverse=True)
        latest = stable[0]
        if _is_version_newer(current, latest):
            return latest
    return None


def filter_version_prefix(group: str, artifact: str, prefix: str, current: str) -> str | None:
    """只保留以 prefix 开头的稳定版本，如 2.12.。

    只有当最新版严格高于当前版时才返回。
    """
    versions = _fetch_maven_metadata(group, artifact)
    if versions is None:
        return None
    matching = [v for v in versions if v.startswith(prefix) and not is_prerelease(v)]
    if not matching:
        return None
    matching.sort(key=_parse_version_tuple, reverse=True)
    latest = matching[0]
    if _is_version_newer(current, latest):
        return latest
    return None


def parse_gav_from_url(url: str) -> tuple[str, str] | None:
    """从 Maven Central URL 解析 groupId 和 artifactId。"""
    m = MAVEN_URL_RE.search(url)
    if not m:
        return None
    path = m.group(1)
    parts = path.split("/")
    if len(parts) < 2:
        return None
    artifact = parts[-1]
    group = ".".join(parts[:-1])
    return group, artifact


def _find_object_name_before_comment(lines: list[str], comment_idx: int) -> str | None:
    """查找注释前最近的 object 名称（向上搜索）。"""
    for j in range(comment_idx - 1, max(0, comment_idx - 4), -1):
        m = re.search(r"object\s+(\w+)\s+extends", lines[j])
        if m:
            return m.group(1)
    return None


def _find_version_def_after_comment(lines: list[str], start: int) -> tuple[int, str, re.Match] | None:
    """从 start 行开始查找 def version 的字面量定义。

    只匹配 def version: String = "x.y.z" 形式，跳过 def version = xxx.version 引用。
    """
    version_re = re.compile(r'(def\s+version\s*(?::\s*\w+)?\s*=\s*")([^"]+)(")')
    for j in range(start, min(start + 5, len(lines))):
        m = version_re.search(lines[j])
        if m:
            value = m.group(2)
            if _is_literal_version(value):
                return j, value, m
    return None


def _is_excluded(object_name: str) -> bool:
    """检查是否在排除列表中。"""
    return object_name in EXCLUDE_NAMES


def update_package_scala(repo_root: Path, apply: bool) -> list[dict]:
    """更新 build-gav/package.scala 中的版本号。"""
    results = []
    filepath = repo_root / "build-gav" / "shared" / "src" / "main" / "scala" / "com" / "peknight" / "build" / "gav" / "package.scala"
    if not filepath.exists():
        return results

    content = filepath.read_text()
    lines = content.splitlines()
    modified = False

    i = 0
    while i < len(lines):
        url_match = BLOCK_RE.search(lines[i])
        if not url_match:
            i += 1
            continue

        url = url_match.group(1)
        gav = parse_gav_from_url(url)
        if not gav:
            i += 1
            continue

        group, artifact = gav

        # 向上查找注释前的 object 名称
        object_name = _find_object_name_before_comment(lines, i)
        if not object_name:
            i += 1
            continue

        if _is_excluded(object_name):
            results.append({"name": object_name, "status": "skipped", "reason": "排除列表"})
            i += 1
            continue

        # 在注释下方查找字面量版本定义
        version_info = _find_version_def_after_comment(lines, i + 1)
        if not version_info:
            i += 1
            continue

        version_line_idx, current_version, version_match = version_info
        latest = get_latest_matching_version(group, artifact, current_version)
        if latest is None:
            results.append({"name": object_name, "status": "skipped", "reason": f"已是最新 ({current_version})"})
            i += 1
            continue

        new_line = version_match.group(0).replace(current_version, latest, 1)
        lines[version_line_idx] = new_line
        modified = True
        results.append({"name": object_name, "status": "updated", "old": current_version, "new": latest})
        i += 1

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results


def update_build_sbt(repo_root: Path, apply: bool) -> list[dict]:
    """更新 build.sbt 中的版本号。"""
    results = []
    filepath = repo_root / "build.sbt"
    if not filepath.exists():
        return results

    content = filepath.read_text()
    lines = content.splitlines()
    modified = False

    val_version_re = re.compile(r'(val\s+(\w+Version)\s*=\s*")([^"]+)(")')

    i = 0
    while i < len(lines):
        url_match = BLOCK_RE.search(lines[i])
        if not url_match:
            i += 1
            continue

        if i + 1 >= len(lines):
            i += 1
            continue

        val_match = val_version_re.search(lines[i + 1])
        if not val_match:
            i += 1
            continue

        var_name = val_match.group(2)
        current_version = val_match.group(3)

        url = url_match.group(1)
        gav = parse_gav_from_url(url)
        if not gav:
            i += 1
            continue

        group, artifact = gav

        # scala212Version 特殊处理：只取 2.12.* 前缀
        if var_name == "scala212Version":
            latest = filter_version_prefix(group, artifact, "2.12.", current_version)
        else:
            latest = get_latest_matching_version(group, artifact, current_version)

        if latest is None:
            results.append({"name": var_name, "status": "skipped", "reason": f"已是最新 ({current_version})"})
            i += 2
            continue

        new_line = val_match.group(0).replace(current_version, latest, 1)
        lines[i + 1] = new_line
        modified = True
        results.append({"name": var_name, "status": "updated", "old": current_version, "new": latest})
        i += 2

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results


def update_build_properties(repo_root: Path, apply: bool) -> list[dict]:
    """更新 project/build.properties 中的 sbt.version。"""
    results = []
    filepath = repo_root / "project" / "build.properties"
    if not filepath.exists():
        return results

    content = filepath.read_text()
    lines = content.splitlines()
    modified = False

    for i, line in enumerate(lines):
        url_match = PROP_RE.search(line)
        if not url_match:
            continue
        if i + 1 >= len(lines):
            continue

        prop_match = re.search(r'(sbt\.version\s*=\s*)(.+)', lines[i + 1])
        if not prop_match:
            continue

        current_version = prop_match.group(2).strip()
        url = url_match.group(1)
        gav = parse_gav_from_url(url)
        if not gav:
            continue

        group, artifact = gav
        latest = get_latest_matching_version(group, artifact, current_version)
        if latest is None:
            results.append({"name": "sbt.version", "status": "skipped", "reason": f"已是最新 ({current_version})"})
            continue

        lines[i + 1] = f"sbt.version={latest}"
        modified = True
        results.append({"name": "sbt.version", "status": "updated", "old": current_version, "new": latest})
        break

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results


def update_plugins_sbt(repo_root: Path, apply: bool) -> list[dict]:
    """更新 project/plugins.sbt 中的插件版本号。"""
    results = []
    filepath = repo_root / "project" / "plugins.sbt"
    if not filepath.exists():
        return results

    content = filepath.read_text()
    lines = content.splitlines()
    modified = False

    plugin_re = re.compile(
        r'(addSbtPlugin\("([^"]+)"\s*%\s*"([^"]+)"\s*%\s*")([^"]+)(")'
    )

    for i, line in enumerate(lines):
        url_match = LINE_RE.search(line)
        if not url_match:
            continue
        if i + 1 >= len(lines):
            continue

        plugin_match = plugin_re.search(lines[i + 1])
        if not plugin_match:
            continue

        current_version = plugin_match.group(4)
        url = url_match.group(1)

        gav = parse_gav_from_url(url)
        if gav:
            group, artifact = gav
        else:
            group = plugin_match.group(2)
            artifact = plugin_match.group(3)

        latest = get_latest_matching_version(group, artifact, current_version)
        if latest is None:
            name_match = re.search(r'"([^"]+)"', lines[i + 1])
            name = name_match.group(1) if name_match else artifact
            results.append({"name": name, "status": "skipped", "reason": f"已是最新 ({current_version})"})
            continue

        new_line = plugin_match.group(0).replace(current_version, latest, 1)
        lines[i + 1] = new_line
        modified = True

        name_match = re.search(r'"([^"]+)"', lines[i + 1])
        name = name_match.group(1) if name_match else artifact
        results.append({"name": name, "status": "updated", "old": current_version, "new": latest})

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results


def query_docker_version() -> list | None:
    """查询 eclipse-temurin 最新 JDK 镜像版本。"""
    try:
        req = urllib.request.Request(DOCKER_API)
        req.add_header("User-Agent", "peknight-build-update-deps/1.0")
        with urllib.request.urlopen(req, timeout=30) as resp:
            data = json.loads(resp.read().decode())
            return data.get("results", [])
    except Exception:
        return None


def update_docker_image(repo_root: Path, apply: bool) -> list[dict]:
    """更新 build-sbt/package.scala 中的 dockerBaseImage 版本号。"""
    results = []
    filepath = repo_root / "build-sbt" / "src" / "main" / "scala" / "com" / "peknight" / "build" / "sbt" / "package.scala"
    if not filepath.exists():
        return results

    content = filepath.read_text()
    lines = content.splitlines()
    modified = False

    docker_re = re.compile(r'(dockerBaseImage\s*:=\s*"eclipse-temurin:)(\d+)_(\d+)(-jdk")')

    for i, line in enumerate(lines):
        # 支持块注释（/** @version-check */）和单行注释（// @version-check）
        url_match = BLOCK_RE.search(line) or LINE_RE.search(line)
        if not url_match:
            continue

        # 在同一行或之后 5 行内查找 dockerBaseImage
        docker_idx = None
        for j in range(i, min(i + 6, len(lines))):
            m = docker_re.search(lines[j])
            if m:
                docker_idx = j
                break

        if docker_idx is None:
            continue

        m = docker_re.search(lines[docker_idx])
        current_major = int(m.group(2))
        current_patch = m.group(3)

        tags = query_docker_version()
        if tags is None:
            results.append({"name": "eclipse-temurin", "status": "error", "reason": "查询失败"})
            break

        next_major = current_major + 1
        current_best = current_patch
        next_best = None

        for tag_info in tags:
            tag_name = tag_info.get("name", "")
            tag_m = DOCKER_TAG_RE.match(tag_name)
            if not tag_m:
                continue
            major = int(tag_m.group(1))
            patch = int(tag_m.group(2))
            if major == next_major and (next_best is None or patch > next_best):
                next_best = patch
            elif major == current_major and patch > int(current_best):
                current_best = str(patch)

        if next_best is not None:
            best_major = next_major
            best_patch = str(next_best)
        else:
            best_major = current_major
            best_patch = str(current_best)

        new_version = f"{best_major}_{best_patch}"
        old_version = f"{current_major}_{current_patch}"

        if new_version != old_version:
            new_line = line.replace(old_version, new_version)
            lines[i] = new_line
            modified = True
            results.append({
                "name": "eclipse-temurin",
                "status": "updated",
                "old": old_version,
                "new": new_version,
            })
        else:
            results.append({
                "name": "eclipse-temurin",
                "status": "skipped",
                "reason": f"已是最新 ({old_version})",
            })
        break

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results


def print_results(results):
    """打印更新结果汇总。"""
    print()
    print("=" * 60)
    for r in results:
        if r["status"] == "updated":
            print(f"[已更新] {r['name']}: {r['old']} → {r['new']}")
        elif r["status"] == "skipped":
            print(f"[跳过]   {r['name']} ({r['reason']})")
        elif r["status"] == "error":
            print(f"[错误]   {r['name']} ({r['reason']})")
    print("=" * 60)
    updated = sum(1 for r in results if r["status"] == "updated")
    skipped = sum(1 for r in results if r["status"] == "skipped")
    errors = sum(1 for r in results if r["status"] == "error")
    print(f"已更新: {updated}  跳过: {skipped}  错误: {errors}")
    print("=" * 60)


def main():
    parser = argparse.ArgumentParser(description="自动更新 build 模块依赖版本号")
    parser.add_argument("--apply", action="store_true", help="实际写入文件（默认仅 dry-run）")
    parser.add_argument("--skip", nargs="*", default=[], metavar="NAME",
                        help="临时跳过指定依赖的更新（object 名称）")
    args = parser.parse_args()

    EXCLUDE_NAMES.update(args.skip)

    repo_root = Path(__file__).resolve().parent.parent

    print("=" * 60)
    if not args.apply:
        print("DRY-RUN 模式 - 不会修改任何文件")
    print("=" * 60)
    print()

    results = []
    results += update_package_scala(repo_root, args.apply)
    results += update_build_sbt(repo_root, args.apply)
    results += update_build_properties(repo_root, args.apply)
    results += update_plugins_sbt(repo_root, args.apply)
    results += update_docker_image(repo_root, args.apply)

    print_results(results)


if __name__ == "__main__":
    main()
