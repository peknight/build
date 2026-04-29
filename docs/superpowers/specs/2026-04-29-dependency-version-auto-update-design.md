# 依赖版本自动更新脚本设计

## 项目概览

创建一个 Python 脚本，自动检索并更新 build 模块中所有非 Peknight 外部依赖的最新版本号，包括 `build-gav/package.scala`、`build.sbt`、`project/build.properties`、`project/plugins.sbt`、`build-sbt/package.scala` 五个文件中的版本定义。

## 版本获取源

### Maven Central 依赖

统一使用 Maven Central Search API：
```
https://search.maven.org/solrsearch/select?q=g:{groupId}+AND+a:{artifactId}&rows=1&core=gav&wt=json
```
从返回 JSON 的 `latestVersion` 字段获取最新版本。

### Docker Hub 镜像

使用 Docker Hub API：
```
https://hub.docker.com/v2/repositories/library/eclipse-temurin/tags?page_size=50
```

## 版本智能匹配

1. 判断当前版本类型：预发布（含 RC/M/SNAPSHOT/alpha/beta）或稳定版
2. 当前是稳定版 → 只更新到最新稳定版
3. 当前是预发布 → 更新到最新版本（无论稳定或预发布）

### 特殊约束

- `scala212Version`：只取 `2.12.*` 前缀的版本，不升级到 2.13
- `http4s` 下所有依赖：不更新
- `cats-parse`（typelevel.catsParse）：不更新

## 解析策略

通过查找 `/** URL */` 或 `// URL` 注释行，从注释 URL 中解析 groupId 和 artifactId：

| 文件 | 匹配模式 |
|------|----------|
| `build-gav/package.scala` | 注释后紧跟 `object xxx extends` 中的 `def version: String = "x.y.z"` |
| `build.sbt` | 注释后紧跟 `val xxxVersion = "x.y.z"` |
| `project/build.properties` | 注释后紧跟 `sbt.version=x.y.z` |
| `project/plugins.sbt` | 注释后紧跟 `addSbtPlugin(... % "x.y.z")` |
| `build-sbt/package.scala` | Docker Hub URL 注释 + `dockerBaseImage := "eclipse-temurin:XX_XX-jdk"` |

## Docker 镜像更新逻辑

1. 解析当前 tag 的大版本号（如 `26_35-jdk` → 26）
2. 检查 Docker Hub 是否存在下一大版本号的 tag（如 `27_*-jdk`）
3. 存在 → 更新到最新大版本的最新 tag
4. 不存在 → 更新到当前大版本的最新 tag

## 命令行接口

```
python update-deps.py          # dry-run 模式，打印变更
python update-deps.py --apply  # 实际写入文件
python update-deps.py --skip http4s  # 临时额外排除
```

## 输出格式

```
[已更新] typelevel.cats: 2.13.0 → 2.14.0
[已更新] circe: 0.14.15 → 0.15.0
[跳过]   http4s (排除列表)
[跳过]   catsParse (排除列表)
[跳过]   bouncyCastle: 1.84 (已是最新)
[错误]   xxx (查询失败: HTTP 500)
```

## 技术实现

- 纯 Python 标准库（`urllib`、`re`、`json`），零外部依赖
- 使用正则表达式解析 Scala 源码中的版本定义
- 脚本置于 `build/update-deps.py`
