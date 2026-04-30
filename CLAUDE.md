# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概览

`build` 模块，属于 peknight 单体仓库的 **层级 0 — 构建基础**，是其它所有模块的基石。为整个 monorepo 提供统一的依赖管理和 SBT 构建配置。

## 子项目结构

| 子项目 | 平台 | 依赖 | 用途 |
|--------|------|------|------|
| `build-gav` | JVM/JS/Native 跨平台 | 无内部依赖 | 类型安全依赖目录，GAV 坐标定义为 `GroupID`/`ArtifactID`/`Version`/`Module` trait |
| `build-sbt` | JVM | `build-gav.jvm` | SBT 插件，提供跨平台依赖 DSL 和通用构建设置 |

## 外部依赖

- `build-gav`：无外部依赖，纯坐标定义
- `build-sbt`：`sbt`（Optional）、`sbt-platform-deps`（Optional）、`sbt-native-packager`（Optional）

DSL 中管理的外部库包括：cats、cats-effect、circe、http4s、fs2、doobie、scodec、log4cats、bouncycastle 等（详见 `build-gav/shared/src/main/scala/com/peknight/build/gav/package.scala`）

## 源码结构

- `build-gav/shared/src/main/scala/com/peknight/build/gav/`：`com.peknight.build.gav` — 5 个源文件，定义 `Version`、`Module`、`GroupID`、`ArtifactID` trait 及完整依赖目录
- `build-sbt/src/main/scala/com/peknight/build/sbt/`：`com.peknight.build.sbt` — 1 个源文件（`package.scala`），提供依赖 DSL、commonSettings、dockerSettings、commonSbtPlugins

## 常用命令

```bash
# 清理并编译
sbt clean compile

# 运行测试
sbt test

# 交叉编译并发布到本地 Ivy 缓存
sbt +publishLocal

# 交叉编译并发布到 Nexus
sbt +publish
```

## 模块依赖层级

本模块处于 **层级 0 — 构建基础**，是整个 monorepo 的最底层。**禁止引入任何其它依赖**。

完整 monorepo 的模块依赖层级见 `/Users/pek/Projects/peknight/claude/CLAUDE.md`。

**重要规则**：后面的模块只能依赖前面的模块，前面的绝不依赖后面的。
