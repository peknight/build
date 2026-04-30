---
name: update-deps
description: |
  自动更新 build 模块中所有外部依赖的最新版本号。
  触发词："更新依赖版本"、"update deps"、"update dependencies"。
  流程：直接 --apply 执行 → 展示结果 → 自动 git commit。
---

# 依赖版本自动更新

## 触发条件

用户说 "更新一下依赖版本"、"update deps"、"update dependencies" 等类似表述。

## 执行流程

### Step 1: 执行更新

```bash
python3 scripts/update-deps.py --apply
```

### Step 2: 展示结果

向用户展示更新结果，列出已更新/跳过/错误的依赖。

### Step 3: 提交变更

如果有更新的依赖：

```bash
git add build-gav/shared/src/main/scala/com/peknight/build/gav/package.scala build.sbt project/build.properties project/plugins.sbt build-sbt/src/main/scala/com/peknight/build/sbt/package.scala
git commit -m "$(cat <<'EOF'
chore: bump dependency versions
EOF
)"
```

commit message 中的描述根据实际更新的依赖列表自动生成。

## 注意事项

- 脚本位于 `scripts/update-deps.py`，使用纯 Python 标准库，零外部依赖
- 更新范围：`build-gav/package.scala`、`build.sbt`、`project/build.properties`、`project/plugins.sbt`、`build-sbt/package.scala`
- 不要手动修改版本号，统一通过脚本执行
- 更新后验证缩进是否正确保留（特别是 `package.scala` 中的 4 空格缩进）