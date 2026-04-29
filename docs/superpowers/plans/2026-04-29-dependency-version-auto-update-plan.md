# 依赖版本自动更新脚本 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 创建一个 Python 脚本，自动从 Maven Central 和 Docker Hub 检索最新版本号并更新 build 模块中所有外部依赖的版本定义。

**Architecture:** 单文件 Python 脚本，纯标准库实现。通过解析源码中的 URL 注释提取 groupId/artifactId，调用 Maven Central Search API 获取最新版本，根据智能策略（稳定/预发布）决定是否更新，最后替换源码中的版本号。

**Tech Stack:** Python 3, urllib, re, json, argparse

---

### Task 1: 创建脚本骨架与 CLI 接口

**Files:**
- Create: `update-deps.py`

- [ ] **Step 1: 创建脚本骨架，包含 shebang、imports、CLI 参数解析**

```python
#!/usr/bin/env python3
"""自动检索并更新 build 模块外部依赖的最新版本号。

默认 dry-run 模式（仅打印变更），使用 --apply 参数实际写入。
"""

import argparse
import json
import re
import sys
import urllib.request
from pathlib import Path

# Maven Central Search API
MAVEN_API = "https://search.maven.org/solrsearch/select?q=g:{group}+AND+a:{artifact}&rows=1&core=gav&wt=json"

# Docker Hub API for eclipse-temurin
DOCKER_API = "https://hub.docker.com/v2/repositories/library/eclipse-temurin/tags?page_size=200"

# 排除列表：不更新的依赖名（object 名称）
EXCLUDE_NAMES = {
    "http4s",          # http4s 下所有
    "catsParse",       # cats-parse
}


def main():
    parser = argparse.ArgumentParser(description="自动更新 build 模块依赖版本号")
    parser.add_argument("--apply", action="store_true", help="实际写入文件（默认仅 dry-run）")
    args = parser.parse_args()

    repo_root = Path(__file__).parent

    print("=" * 60)
    if not args.apply:
        print("DRY-RUN 模式 - 不会修改任何文件")
    print("=" * 60)
    print()

    results = []

    # TODO: 调用各模块的解析+更新函数

    print_results(results)


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


if __name__ == "__main__":
    main()
```

- [ ] **Step 2: 验证脚本可执行**

Run: `python3 update-deps.py`
Expected: 输出 dry-run 模式的 header 和 `已更新: 0  跳过: 0  错误: 0`

- [ ] **Step 3: 提交**

```bash
git add update-deps.py
git commit -m "feat: add dependency version auto-update script skeleton"
```

---

### Task 2: 实现 Maven Central 版本查询 + 智能匹配

- [ ] **Step 1: 添加版本查询函数**

添加到脚本的 `EXCLUDE_NAMES` 之后、`main` 函数之前：

```python
def query_maven_version(group: str, artifact: str) -> str | None:
    """查询 Maven Central 最新版本，返回 latestVersion 或 None。"""
    url = MAVEN_API.format(group=group, artifact=artifact)
    try:
        req = urllib.request.Request(url)
        req.add_header("User-Agent", "peknight-build-update-deps/1.0")
        with urllib.request.urlopen(req, timeout=15) as resp:
            data = json.loads(resp.read().decode())
            response = data.get("response", {})
            docs = response.get("docs", [])
            if docs:
                return docs[0].get("latestVersion")
    except Exception:
        pass
    return None


def is_prerelease(version: str) -> bool:
    """判断版本是否为预发布版本。"""
    markers = ["RC", "M", "SNAPSHOT", "alpha", "beta", "cr", "dev", "a", "b"]
    # 匹配如 1.0.0-M1, 1.0.0-RC2, 1.0.0-SNAPSHOT 等
    lower = version.lower()
    for m in markers:
        if f"-{m}" in lower or lower.startswith(m):
            return True
    return False


def get_latest_matching_version(group: str, artifact: str, current: str) -> str | None:
    """根据智能策略获取最新版本。

    - 当前是稳定版 → 只返回稳定版
    - 当前是预发布 → 返回最新版本（无论稳定或预发布）
    - 返回 None 表示无法查询或版本相同
    """
    latest = query_maven_version(group, artifact)
    if latest is None or latest == current:
        return None

    # 当前是稳定版，只取稳定版
    if not is_prerelease(current):
        # 如果最新版是预发布，尝试查询稳定版
        if is_prerelease(latest):
            # 再次查询，用 rows=5 取最新的稳定版
            url = MAVEN_API.format(group=group, artifact=artifact)
            url = url.replace("rows=1", "rows=5")
            try:
                req = urllib.request.Request(url)
                req.add_header("User-Agent", "peknight-build-update-deps/1.0")
                with urllib.request.urlopen(req, timeout=15) as resp:
                    data = json.loads(resp.read().decode())
                    for doc in data.get("response", {}).get("docs", []):
                        v = doc.get("latestVersion")
                        if v and not is_prerelease(v):
                            if v != current:
                                return v
            except Exception:
                pass
            # 没找到稳定版，如果最新版更新且是预发布，不更新
            return None
    return latest


def filter_version_prefix(group: str, artifact: str, prefix: str) -> str | None:
    """只保留以 prefix 开头的版本，如 2.12.。

    查询 artifact 的所有版本列表，过滤出指定前缀的版本，
    使用 Python 版本号排序取最新。
    """
    # Maven Central 不支持 version 范围过滤，用多行结果手动过滤
    url = f"https://search.maven.org/solrsearch/select?q=g:{urllib.parse.quote(group)}+AND+a:{urllib.parse.quote(artifact)}&rows=20&core=gav&wt=json"
    try:
        req = urllib.request.Request(url)
        req.add_header("User-Agent", "peknight-build-update-deps/1.0")
        with urllib.request.urlopen(req, timeout=15) as resp:
            data = json.loads(resp.read().decode())
            docs = data.get("response", {}).get("docs", [])
            matching = [
                d.get("latestVersion") for d in docs
                if d.get("latestVersion", "").startswith(prefix)
            ]
            if not matching:
                return None
            matching.sort(key=_parse_version_tuple, reverse=True)
            return matching[0]
    except Exception:
        pass
    return None


def _parse_version_tuple(version: str) -> tuple:
    """将版本号字符串解析为可比较的 tuple。

    '2.12.21' → (2, 12, 21)
    '33.6.0-jre' → (33, 6, 0)
    """
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
```

同时需要在文件顶部添加 `import urllib.parse`。

修改已有的 import 行：

```python
import urllib.parse
import urllib.request
```

- [ ] **Step 2: 测试查询功能**

Run:
```bash
python3 -c "
import json, urllib.request
url = 'https://search.maven.org/solrsearch/select?q=g:io.circe+AND+a:circe-core&rows=1&core=gav&wt=json'
req = urllib.request.Request(url)
req.add_header('User-Agent', 'test/1.0')
with urllib.request.urlopen(req, timeout=15) as resp:
    data = json.loads(resp.read().decode())
    print(data['response']['docs'][0]['latestVersion'])
"
```
Expected: 输出 circe-core 的最新版本号

- [ ] **Step 3: 提交**

```bash
git add update-deps.py
git commit -m "feat: add maven central version query with smart pre-release matching"
```

---

### Task 3: 实现 build-gav/package.scala 解析与更新

- [ ] **Step 1: 添加 URL 解析和 package.scala 更新函数**

添加到脚本中：

```python
# Maven Central URL 正则：从注释中提取 groupId 和 artifactId
# URL 格式如: https://repo.maven.apache.org/maven2/org/typelevel/cats-core_3/
MAVEN_URL_RE = re.compile(r"https://repo\.maven\.apache\.org/maven2/(.+)/")


def parse_gav_from_url(url: str) -> tuple[str, str] | None:
    """从 Maven Central URL 解析 groupId 和 artifactId。

    如 https://repo.maven.apache.org/maven2/org/typelevel/cats-core_3/
    返回 ('org.typelevel', 'cats-core')
    """
    m = MAVEN_URL_RE.search(url)
    if not m:
        return None
    path = m.group(1)
    parts = path.split("/")
    if len(parts) < 2:
        return None
    # 最后一部分是 artifactId（可能带平台后缀如 _3, _2.12_1.0）
    artifact = parts[-1]
    # 其余部分是 groupId，用点连接
    group = ".".join(parts[:-1])
    return group, artifact


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
        line = lines[i]

        # 查找 /** URL */ 注释
        url_match = re.search(r"/\*\*\s*(https?://[^\s]+)\s*\*/", line)
        if not url_match:
            i += 1
            continue

        url = url_match.group(1)
        gav = parse_gav_from_url(url)
        if not gav:
            i += 1
            continue

        group, artifact = gav
        object_name = _find_object_name_after_comment(lines, i)
        if not object_name:
            i += 1
            continue

        # 检查是否在排除列表中
        if _is_excluded(object_name):
            results.append({"name": object_name, "status": "skipped", "reason": "排除列表"})
            i += 1
            continue

        # 在后续行中查找 def version: String = "x.y.z" 或 def version = "x.y.z"
        version_info = _find_version_def(lines, i + 1)
        if not version_info:
            i += 1
            continue

        version_line_idx, current_version, version_match = version_info

        # 查询最新版本
        latest = get_latest_matching_version(group, artifact, current_version)
        if latest is None:
            results.append({"name": object_name, "status": "skipped", "reason": f"已是最新 ({current_version})"})
            i += 1
            continue

        # 替换版本号
        old_line = lines[version_line_idx]
        new_line = version_match.group(0).replace(current_version, latest, 1)
        lines[version_line_idx] = new_line
        modified = True

        results.append({"name": object_name, "status": "updated", "old": current_version, "new": latest})
        i += 1

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results


def _find_object_name_after_comment(lines: list[str], comment_idx: int) -> str | None:
    """查找注释后最近的 object 名称。"""
    # 检查同一行或下一行是否有 object 定义
    for j in range(comment_idx + 1, min(comment_idx + 4, len(lines))):
        m = re.search(r"object\s+(\w+)\s+extends", lines[j])
        if m:
            return m.group(1)
        # 也检查 GroupID with Version 模式
        m = re.search(r"object\s+(\w+)\s+extends\s+GroupID", lines[j])
        if m:
            return m.group(1)
    return None


def _find_version_def(lines: list[str], start: int) -> tuple[int, str, re.Match] | None:
    """从 start 行开始查找 def version 定义。"""
    # 匹配 def version: String = "x.y.z" 或 def version = "x.y.z"
    version_re = re.compile(r'(def\s+version\s*(?::\s*String)?\s*=\s*")([^"]+)(")')
    for j in range(start, min(start + 20, len(lines))):
        m = version_re.search(lines[j])
        if m:
            return j, m.group(2), m
    return None


def _is_excluded(object_name: str) -> bool:
    """检查是否在排除列表中。http4s 下所有 object 都排除。"""
    if object_name in EXCLUDE_NAMES:
        return True
    return False
```

- [ ] **Step 2: 集成到 main 函数**

将 `main()` 函数中的 `# TODO: 调用各模块的解析+更新函数` 替换为：

```python
    results += update_package_scala(repo_root, args.apply)
```

- [ ] **Step 3: dry-run 测试**

Run: `python3 update-deps.py`
Expected: 列出所有可更新的依赖及其新旧版本，或标记为跳过

- [ ] **Step 4: 提交**

```bash
git add update-deps.py
git commit -m "feat: parse and update build-gav/package.scala versions"
```

---

### Task 4: 实现 build.sbt 解析与更新

- [ ] **Step 1: 添加 build.sbt 更新函数**

```python
def update_build_sbt(repo_root: Path, apply: bool) -> list[dict]:
    """更新 build.sbt 中的版本号。"""
    results = []
    filepath = repo_root / "build.sbt"
    if not filepath.exists():
        return results

    content = filepath.read_text()
    lines = content.splitlines()
    modified = False

    # 匹配 val xxxVersion = "x.y.z"
    val_version_re = re.compile(r'(val\s+(\w+Version)\s*=\s*")([^"]+)(")')

    i = 0
    while i < len(lines):
        line = lines[i]

        # 查找 URL 注释
        url_match = re.search(r"/\*\*\s*(https?://[^\s]+)\s*\*/", line)
        if not url_match:
            i += 1
            continue

        url = url_match.group(1)
        val_match = val_version_re.search(lines[i + 1]) if i + 1 < len(lines) else None
        if not val_match:
            i += 1
            continue

        var_name = val_match.group(2)
        current_version = val_match.group(3)

        # 特殊处理 scala212Version：只取 2.12.* 前缀
        if var_name == "scala212Version":
            gav = parse_gav_from_url(url)
            if gav:
                group, artifact = gav
                latest = filter_version_prefix(group, artifact, "2.12.")
                if latest and latest != current_version:
                    version_line_idx = i + 1
                    old_line = lines[version_line_idx]
                    new_line = val_version_re.search(old_line).group(0).replace(current_version, latest, 1)
                    lines[version_line_idx] = new_line
                    modified = True
                    results.append({"name": var_name, "status": "updated", "old": current_version, "new": latest})
                else:
                    results.append({"name": var_name, "status": "skipped", "reason": f"已是最新 ({current_version})"})
            i += 2
            continue

        # 普通处理
        gav = parse_gav_from_url(url)
        if not gav:
            i += 1
            continue

        group, artifact = gav
        latest = get_latest_matching_version(group, artifact, current_version)
        if latest is None:
            results.append({"name": var_name, "status": "skipped", "reason": f"已是最新 ({current_version})"})
            i += 1
            continue

        version_line_idx = i + 1
        old_line = lines[version_line_idx]
        new_line = val_version_re.search(old_line).group(0).replace(current_version, latest, 1)
        lines[version_line_idx] = new_line
        modified = True

        results.append({"name": var_name, "status": "updated", "old": current_version, "new": latest})
        i += 1

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results
```

- [ ] **Step 2: 集成到 main 函数**

在 `results += update_package_scala(repo_root, args.apply)` 之后添加：

```python
    results += update_build_sbt(repo_root, args.apply)
```

- [ ] **Step 3: dry-run 测试**

Run: `python3 update-deps.py`
Expected: 包含 build.sbt 中变量的更新信息

- [ ] **Step 4: 提交**

```bash
git add update-deps.py
git commit -m "feat: parse and update build.sbt versions"
```

---

### Task 5: 实现 project/build.properties 和 project/plugins.sbt 更新

- [ ] **Step 1: 添加 build.properties 更新函数**

```python
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
        url_match = re.search(r"#\s*(https?://[^\s]+)", line)
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
```

- [ ] **Step 2: 添加 plugins.sbt 更新函数**

```python
def update_plugins_sbt(repo_root: Path, apply: bool) -> list[dict]:
    """更新 project/plugins.sbt 中的插件版本号。"""
    results = []
    filepath = repo_root / "project" / "plugins.sbt"
    if not filepath.exists():
        return results

    content = filepath.read_text()
    lines = content.splitlines()
    modified = False

    # 匹配 addSbtPlugin("groupId" % "artifactId" % "version")
    plugin_re = re.compile(
        r'(addSbtPlugin\("([^"]+)"\s*%\s*"([^"]+)"\s*%\s*")([^"]+)(")'
    )

    for i, line in enumerate(lines):
        url_match = re.search(r"//\s*(https?://[^\s]+)", line)
        if not url_match:
            continue
        if i + 1 >= len(lines):
            continue

        plugin_match = plugin_re.search(lines[i + 1])
        if not plugin_match:
            continue

        current_version = plugin_match.group(4)
        group = plugin_match.group(2)
        artifact = plugin_match.group(3)
        url = url_match.group(1)

        # 对于 sbt 插件，groupId 已经完整，从 URL 中再确认 artifactId
        gav = parse_gav_from_url(url)
        if gav:
            group, artifact = gav

        latest = get_latest_matching_version(group, artifact, current_version)
        if latest is None:
            # 提取插件名用于显示
            name_match = re.search(r'"([^"]+)"', lines[i + 1])
            name = name_match.group(1) if name_match else artifact
            results.append({"name": name, "status": "skipped", "reason": f"已是最新 ({current_version})"})
            continue

        old_line = lines[i + 1]
        new_line = plugin_match.group(0).replace(current_version, latest, 1)
        lines[i + 1] = new_line
        modified = True

        name_match = re.search(r'"([^"]+)"', old_line)
        name = name_match.group(1) if name_match else artifact
        results.append({"name": name, "status": "updated", "old": current_version, "new": latest})

    if modified and apply:
        filepath.write_text("\n".join(lines) + "\n")

    return results
```

- [ ] **Step 3: 集成到 main 函数**

在 `results += update_build_sbt(repo_root, args.apply)` 之后添加：

```python
    results += update_build_properties(repo_root, args.apply)
    results += update_plugins_sbt(repo_root, args.apply)
```

- [ ] **Step 4: dry-run 测试**

Run: `python3 update-deps.py`
Expected: 包含 build.properties 和 plugins.sbt 的更新信息

- [ ] **Step 5: 提交**

```bash
git add update-deps.py
git commit -m "feat: parse and update build.properties and plugins.sbt versions"
```

---

### Task 6: 实现 Docker Hub JDK 镜像版本更新

- [ ] **Step 1: 添加 Docker Hub 查询函数**

```python
DOCKER_TAG_RE = re.compile(r"(\d+)_(\d+)-jdk")


def query_docker_version() -> list | None:
    """查询 eclipse-temurin 最新 JDK 镜像版本。

    返回 (major, patch) 如 (27, 5) 表示 27_5-jdk
    如果没有下一个大版本则返回当前大版本的最新 patch。
    """
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

    # 查找当前 docker 镜像版本
    docker_re = re.compile(r'(dockerBaseImage\s*:=\s*"eclipse-temurin:)(\d+)_(\d+)(-jdk")')

    for i, line in enumerate(lines):
        m = docker_re.search(line)
        if not m:
            continue

        current_major = int(m.group(2))
        current_patch = m.group(3)
        prefix = m.group(1)
        suffix = m.group(4)

        tags = query_docker_version()
        if tags is None:
            results.append({"name": "eclipse-temurin", "status": "error", "reason": "查询失败"})
            break

        # 分别找出当前大版本和下一大版本的最佳 patch
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
```

- [ ] **Step 2: 集成到 main 函数**

在 `results += update_plugins_sbt(repo_root, args.apply)` 之后添加：

```python
    results += update_docker_image(repo_root, args.apply)
```

- [ ] **Step 3: dry-run 测试**

Run: `python3 update-deps.py`
Expected: 包含 eclipse-temurin 的更新信息

- [ ] **Step 4: 完整测试**

Run: `python3 update-deps.py --apply`
Expected: 实际更新所有可更新的依赖版本

- [ ] **Step 5: 提交**

```bash
git add update-deps.py
git commit -m "feat: parse and update docker base image version from Docker Hub"
```

---

### Task 7: 添加 `--skip` 参数和错误处理增强

- [ ] **Step 1: 添加 --skip 参数**

修改 `main()` 中的 argparse 部分：

```python
    parser.add_argument("--apply", action="store_true", help="实际写入文件（默认仅 dry-run）")
    parser.add_argument("--skip", nargs="*", default=[], metavar="NAME",
                        help="临时跳过指定依赖的更新（object 名称）")
    args = parser.parse_args()

    # 合并跳过列表到全局排除集合
    EXCLUDE_NAMES.update(args.skip)
```

由于 `EXCLUDE_NAMES` 是全局集合，所有使用 `_is_excluded` 的地方无需修改函数签名，天然生效。

- [ ] **Step 2: 增强错误处理**

在 `query_maven_version` 中添加重试逻辑，替换原有函数：

```python
def query_maven_version(group: str, artifact: str) -> str | None:
    """查询 Maven Central 最新版本，返回 latestVersion 或 None。"""
    url = MAVEN_API.format(group=group, artifact=artifact)
    import time
    for attempt in range(3):
        try:
            req = urllib.request.Request(url)
            req.add_header("User-Agent", "peknight-build-update-deps/1.0")
            with urllib.request.urlopen(req, timeout=15) as resp:
                data = json.loads(resp.read().decode())
                response = data.get("response", {})
                docs = response.get("docs", [])
                if docs:
                    return docs[0].get("latestVersion")
        except Exception:
            if attempt < 2:
                time.sleep(1)
    return None
```

- [ ] **Step 3: 测试 --skip 参数**

Run: `python3 update-deps.py --skip cats circe`
Expected: cats 和 circe 的更新被跳过，标记为 "临时跳过"

- [ ] **Step 4: 提交**

```bash
git add update-deps.py
git commit -m "feat: add --skip flag and retry on maven api failure"
```
