val pekVersion = "0.1.0-SNAPSHOT"
/** @versionCheck https://repo.maven.apache.org/maven2/org/scala-lang/scala3-library_3/ */
val scala3Version = "3.8.3"
/** @versionCheck https://repo.maven.apache.org/maven2/org/scala-lang/scala-library/ */
val scala212Version = "2.12.21"
/** @versionCheck https://repo.maven.apache.org/maven2/org/scala-sbt/sbt/ */
val sbtVersion = "1.12.10"
/** @versionCheck https://repo.maven.apache.org/maven2/org/portable-scala/sbt-platform-deps_2.12_1.0/ */
val sbtPlatformDepsVersion = "1.0.2"
/** @versionCheck https://repo.maven.apache.org/maven2/com/github/sbt/sbt-native-packager_2.12_1.0/ */
val sbtNativePackagerVersion = "1.11.7"

ThisBuild / organization := "com.peknight"
ThisBuild / version := pekVersion
ThisBuild / scalaVersion := scala212Version
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
  .settings(name := "build")
  .aggregate(
    buildGav.jvm,
    buildGav.js,
    buildGav.native,
    buildSbt
  )

lazy val buildGav = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("build-gav"))
  .settings(
    name := "build-gav",
    crossScalaVersions := Seq(scala212Version, scala3Version),
  )

lazy val buildSbt = (project in file("build-sbt"))
  .dependsOn(buildGav.jvm)
  .settings(
    name := "build-sbt",
    libraryDependencies ++= Seq(
      "org.scala-sbt" % "sbt" % sbtVersion % Optional,
      "org.portable-scala" % "sbt-platform-deps_2.12_1.0" % sbtPlatformDepsVersion % Optional,
      "com.github.sbt" % "sbt-native-packager_2.12_1.0" % sbtNativePackagerVersion % Optional,
    ),
  )
