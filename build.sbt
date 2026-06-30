val pekVersion = "0.1.0-SNAPSHOT"
/** @versionCheck https://repo.maven.apache.org/maven2/org/scala-lang/scala3-library_3/ */
val scala3Version = "3.8.4"
/** @versionCheck https://repo.maven.apache.org/maven2/org/scala-sbt/sbt/ */
val sbtVersion = "2.0.1"
/** @versionCheck https://repo.maven.apache.org/maven2/com/github/sbt/sbt-native-packager_sbt2_3/ */
val sbtNativePackagerVersion = "1.11.7"

organization := "com.peknight"
version := pekVersion
scalaVersion := scala3Version
versionScheme := Some("early-semver")

val nexus = "https://nexus.peknight.com/repository"
resolvers += "Pek Nexus" at s"$nexus/maven-public/"
publishTo := {
  if (isSnapshot.value)
    Some("snapshot" at s"$nexus/maven-snapshots/")
  else
    Some("releases" at s"$nexus/maven-releases/")
}
credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

lazy val build = rootProject
  .settings(name := "build")
  .settings(publish / skip := true)
  .aggregate(buildGav.projectRefs *)
  .aggregate(buildSbt)

lazy val buildGav = (projectMatrix in file("build-gav"))
  .settings(
    name := "build-gav",
  )
  .jvmPlatform(scalaVersions = Seq(scala3Version))
  .jsPlatform(scalaVersions = Seq(scala3Version))
  .nativePlatform(scalaVersions = Seq(scala3Version))

lazy val buildSbt = (project in file("build-sbt"))
  .dependsOn(buildGav.jvm(scala3Version))
  .settings(
    name := "build-sbt",
    libraryDependencies ++= Seq(
      "org.scala-sbt" % "sbt" % sbtVersion % Optional,
      "com.github.sbt" % "sbt-native-packager_sbt2_3" % sbtNativePackagerVersion % Optional,
    ),
  )
