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
    object ext {
      object scalaCheck extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "scalacheck-ext"
        def version: String = peknight.version
      }
    }
  }

  /** https://www.scala-lang.org/ */
  object scala extends GroupID {
    def groupId: String = "org.scala-lang"
    object scala3 extends Version {
      def version: String = "3.7.2"
    }
  }

  /** https://www.scala-sbt.org/ */
  object sbtScala extends Module {
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

    /** https://github.com/portable-scala/sbt-platform-deps */
    object sbtPlatformDeps extends Module {
      def groupId: String = portableScala.groupId
      def artifactId: String = "sbt-platform-deps_2.12_1.0"
      def version: String = "1.0.2"
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
    /** https://mvnrepository.com/artifact/org.typelevel/cats-core */
    object cats extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-core"
      def version: String = "2.13.0"
      object laws extends Module {
        def groupId: String = typelevel.groupId
        def artifactId: String = "cats-laws"
        def version: String = cats.version
      }
    }
    /** https://mvnrepository.com/artifact/org.typelevel/cats-effect */
    object catsEffect extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-effect"
      def version: String = "3.6.3"
    }
    /** https://mvnrepository.com/artifact/org.typelevel/cats-parse */
    object catsParse extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-parse"
      def version: String = "0.3.10"
    }
    /** https://mvnrepository.com/artifact/org.typelevel/log4cats-core */
    object log4Cats extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "log4cats-core"
      def version: String = "2.7.1"
    }
    /** https://mvnrepository.com/artifact/org.typelevel/spire */
    object spire extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "spire"
      def version: String = "0.18.0"
    }
  }

  /** https://mvnrepository.com/artifact/co.fs2/fs2-core */
  object fs2 extends Module {
    def groupId: String = "co.fs2"
    def artifactId: String = "fs2-core"
    def version: String = "3.12.0"
    /** https://mvnrepository.com/artifact/co.fs2/fs2-io */
    object io extends Module {
      def groupId: String = fs2.groupId
      def artifactId: String = "fs2-io"
      def version: String = fs2.version
    }
  }

  /** https://mvnrepository.com/artifact/io.circe/circe-core */
  object circe extends Module {
    def groupId: String = "io.circe"
    def artifactId: String = "circe-core"
    def version: String = "0.14.14"
    /** https://mvnrepository.com/artifact/io.circe/circe-parser */
    object parser extends Module {
      def groupId: String = circe.groupId
      def artifactId: String = "circe-parser"
      def version: String = circe.version
    }
    /** https://mvnrepository.com/artifact/io.circe/circe-jawn */
    object jawn extends Module {
      def groupId: String = circe.groupId
      def artifactId: String = "circe-jawn"
      def version: String = circe.version
    }
  }
  object scodec extends GroupID with Version {
    def groupId: String = "org.scodec"
    def version: String = "1.2.4"
    /** https://mvnrepository.com/artifact/org.scodec/scodec-bits */
    object bits extends Module {
      def groupId: String = scodec.groupId
      def artifactId: String = "scodec-bits"
      def version: String = scodec.version
    }
  }

  /** https://mvnrepository.com/artifact/org.http4s/http4s-core */
  object http4s extends Module {
    def groupId: String = "org.http4s"
    def artifactId: String = "http4s-core"
    def version: String = "1.0.0-M34"
  }

  /** https://mvnrepository.com/artifact/org.scalacheck/scalacheck */
  object scalaCheck extends Module {
    def groupId: String = "org.scalacheck"
    def artifactId: String = "scalacheck"
    def version: String = "1.18.1"
  }
}
