# 依赖版本自动更新脚本设计

## 项目概览

创建一个 Python 脚本，自动检索并更新 build 模块中所有非 Peknight 外部依赖的最新版本号，包括 `build-gav/package.scala`、`build.sbt`、`project/build.properties`、`project/plugins.sbt`、`build-sbt/package.scala` 五个文件中的版本定义。

## 注释锚点格式

所有需要更新版本号的位置，在版本号定义的上方添加统一格式的注释：

```
/** @version-check <Maven Central URL> */
```

或 properties/SBT 插件文件：

```
// @version-check <URL>
# @version-check <URL>
```

### 示例

**build-gav/package.scala**：
```scala
/** @version-check https://repo.maven.apache.org/maven2/org/bouncycastle/bcprov-jdk18on/ */
def version: String = "1.84"
```

**build.sbt**：
```scala
/** @version-check https://repo.maven.apache.org/maven2/org/scala-sbt/sbt/ */
val sbtVersion = "1.12.9"
```

**project/build.properties**：
```properties
# @version-check https://repo.maven.apache.org/maven2/org/scala-sbt/sbt/
sbt.version=1.12.9
```

**project/plugins.sbt**：
```scala
// @version-check https://repo.maven.apache.org/maven2/com/github/sbt/sbt-native-packager_2.12_1.0/
addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.11.7")
```

**build-sbt/package.scala（Docker）**：
```scala
// @version-check https://hub.docker.com/_/eclipse-temurin/tags
dockerBaseImage := "eclipse-temurin:26_35-jdk"
```

## 版本获取源

### Maven Central 依赖

使用 Maven metadata XML 端点：
```
https://repo.maven.apache.org/maven2/{group_path}/{artifact}/maven-metadata.xml
```
从 `<versions>` 标签中获取所有版本列表，用 Python 排序取最新。

### Docker Hub 镜像

使用 Docker Hub API：
```
https://hub.docker.com/v2/repositories/library/eclipse-temurin/tags?page_size=200
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

通过查找 `@version-check` 注释锚点 + 下方紧跟的版本号行进行解析：

| 文件 | 版本号行格式 |
|------|----------|
| `build-gav/package.scala` | `def version: String = "x.y.z"` |
| `build.sbt` | `val xxxVersion = "x.y.z"` |
| `project/build.properties` | `sbt.version=x.y.z` |
| `project/plugins.sbt` | `addSbtPlugin(... % "version")` |
| `build-sbt/package.scala` | `dockerBaseImage := "eclipse-temurin:XX_XX-jdk"` |

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

- 纯 Python 标准库（`urllib`、`re`、`json`、`xml.etree.ElementTree`），零外部依赖
- 通过 `@version-check` 注释锚点直接定位版本号，无需作用域分析
- 脚本置于 `build/update-deps.py`
