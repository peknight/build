package com.peknight.build

package object gav {
  object peknight extends GroupID with Version {
    def groupId: String = "com.peknight"
    def version: String = "0.1.0-SNAPSHOT"
    object build {
      object gav extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "build-gav"
        def version: String = peknight.version
      }
      object sbt extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "build-sbt"
        def version: String = peknight.version
      }
    }
  }

  object scala extends GroupID {
    def groupId: String = "org.scala-lang"
    object scala3 extends Version {
      def version: String = "3.7.2"
    }
  }

  object sbt extends Module {
    def groupId: String = "org.scala-sbt"
    def artifactId: String = "sbt"
    def version: String = "1.11.4"
  }

  object sbtGithub extends GroupID {
    def groupId: String = "com.github.sbt"
    /** https://github.com/sbt/sbt-native-packager */
    object nativePackager extends Module {
      def groupId: String = sbtGithub.groupId
      def artifactId: String = "sbt-native-packager"
      def version: String = "1.11.1"
    }
  }

  object portableScala extends GroupID {
    def groupId: String = "org.portable-scala"
    /** https://github.com/portable-scala/sbt-crossproject */
    object crossProject extends Version {
      def version: String = "1.3.2"
      object scalaJs extends Module {
        def groupId: String = portableScala.groupId
        def artifactId: String = "sbt-scalajs-crossproject"
        def version: String = crossProject.version
      }
      object scalaNative extends Module {
        def groupId: String = portableScala.groupId
        def artifactId: String = "sbt-scala-native-crossproject"
        def version: String = crossProject.version
      }
    }
  }

  /** https://www.scala-js.org/ */
  object scalaJs extends GroupID with Version {
    def groupId: String = "org.scala-js"
    def version: String = "1.19.0"
    object sbt extends Module {
      def groupId: String = scalaJs.groupId
      def artifactId: String = "sbt-scalajs"
      def version: String = scalaJs.version
    }
  }

  /** https://scala-native.org/en/stable/ */
  object scalaNative extends GroupID with Version {
    def groupId: String = "org.scala-native"
    def version: String = "0.5.8"
    object sbt extends Module {
      def groupId: String = scalaNative.groupId
      def artifactId: String = "sbt-scala-native"
      def version: String = scalaNative.version
    }
  }

  object typelevel extends GroupID {
    def groupId: String = "org.typelevel"
    /** https://github.com/typelevel/cats */
    object cats extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-core"
      def version: String = "2.13.0"
    }
  }
}
