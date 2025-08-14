ThisBuild / organization := "com.peknight"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.20"
ThisBuild / versionScheme := Some("early-semver")

val nexus = "https://nexus.peknight.com/repository"
ThisBuild / resolvers += "Pek Nexus" at s"$nexus/maven-public/"
ThisBuild / publishTo := {
  if (isSnapshot.value)
    Some("snapshot" at s"$nexus/maven-snapshots/")
  else
    Some("releases" at s"$nexus/maven-releases/")
}
ThisBuild / credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

lazy val build = (project in file("."))
  .aggregate(
    buildGav.jvm,
    buildGav.js,
    buildGav.native,
    buildSbt
  )
  .settings(
    name := "build",
  )

lazy val buildGav = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("build-gav"))
  .settings(
    name := "build-gav",
  )

lazy val buildSbt = (project in file("build-sbt"))
  .dependsOn(buildGav.jvm)
  .settings(
    name := "build-sbt",
    libraryDependencies ++= Seq(
      "org.scala-sbt" % "sbt" % "1.11.4" % Optional,
      "org.portable-scala" % "sbt-platform-deps_2.12_1.0" % "1.0.2" % Optional,
      "com.github.sbt" % "sbt-native-packager_2.12_1.0" % "1.11.1" % Optional,
    ),
  )
