package com.peknight.build

import _root_.sbt.Keys._
import _root_.sbt._
import _root_.sbt.io.syntax.fileToRichFile
import _root_.sbt.librarymanagement.syntax.{stringToOrganization, toRepositoryName}
import com.peknight.build.gav.sbtGithub.nativePackager
import com.peknight.build.gav.{Module, peknight, portableScala, scalaJs, scalaNative}
import com.typesafe.sbt.packager.Keys.maintainer
import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport.{Docker, dockerBaseImage, dockerBuildOptions, dockerRepository}
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport.toPlatformDepsGroupID

import scala.collection.Seq

package object sbt {

  def crossTestDependencies(dependencies: Module*): Def.Setting[Seq[ModuleID]] =
    libraryDependencies ++= dependencies.map(dependency => dependency.groupId %%% dependency.artifactId % dependency.version % Test)

  def crossDependencies(dependencies: Module*): Def.Setting[Seq[ModuleID]] =
    libraryDependencies ++= dependencies.map(dependency => dependency.groupId %%% dependency.artifactId % dependency.version)

  def dependency(dependency: Module): ModuleID = dependency.groupId %% dependency.artifactId % dependency.version
  def dependencies(dependencies: Module*): Def.Setting[Seq[ModuleID]] = libraryDependencies ++= dependencies.map(dependency)

  def jvmDependency(dependency: Module): ModuleID = dependency.groupId % dependency.artifactId % dependency.version
  def jvmDependencies(dependencies: Module*): Def.Setting[Seq[ModuleID]] = libraryDependencies ++= dependencies.map(jvmDependency)

  def addJvmSbtPlugin(dependency: Module): Def.Setting[Seq[ModuleID]] = addSbtPlugin(jvmDependency(dependency))

  lazy val commonSbtPlugins = Seq(
    addJvmSbtPlugin(nativePackager),
    addJvmSbtPlugin(portableScala.crossProject.scalaJs),
    addJvmSbtPlugin(portableScala.crossProject.scalaNative),
    addJvmSbtPlugin(scalaJs.sbt),
    addJvmSbtPlugin(scalaNative.sbt)
  )

  lazy val commonSettings: Seq[Def.Setting[?]] = buildSettings ++ nexusSettings

  lazy val buildSettings: Seq[Def.Setting[?]] = Seq(
    ThisBuild / organization := peknight.groupId,
    ThisBuild / version := peknight.version,
    ThisBuild / scalaVersion := com.peknight.build.gav.scala.scala3.version,
    ThisBuild / versionScheme := Some("early-semver"),
    ThisBuild / scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-Xfatal-warnings",
      "-language:strictEquality",
      "-Xmax-inlines:64"
    )
  )

  private val nexus = "https://nexus.peknight.com/repository"

  lazy val nexusSettings: Seq[Def.Setting[?]] = Seq(
    ThisBuild / resolvers += "Pek Nexus" at s"$nexus/maven-public/",
    ThisBuild / publishTo := {
      if (isSnapshot.value)
        Some("snapshot" at s"$nexus/maven-snapshots/")
      else
        Some("releases" at s"$nexus/maven-releases/")
    },
    ThisBuild / credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),
  )

  lazy val dockerSettings: Seq[Def.Setting[?]] = Seq(
    dockerBaseImage := "eclipse-temurin:21",
    Docker / maintainer := "peknight <JKpeknight@gmail.com>",
    dockerRepository := Some("docker.peknight.com"),
    dockerBuildOptions ++= Seq(
      "--platform", "linux/amd64"
    ),
  )
}
