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
    object commons {
      object text extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "commons-text"
        def version: String = peknight.version
      }
      object time extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "commons-time"
        def version: String = peknight.version
      }
    }
    object cats extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "cats-core"
      def version: String = peknight.version
      object scodec {
        object bits extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "cats-scodec-bits"
          def version: String = peknight.version
        }
      }
      object scalaCheck extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "cats-scalacheck"
        def version: String = peknight.version
      }
    }
    object catsEffect extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "cats-effect-core"
      def version: String = peknight.version
    }
    object catsParse extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "cats-parse-core"
      def version: String = peknight.version
    }
    object scodec {
      object bits extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "scodec-bits-core"
        def version: String = peknight.version
      }
    }
    object ip4s extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "ip4s-core"
      def version: String = peknight.version
    }
    object fs2 extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "fs2-core"
      def version: String = peknight.version
      object io extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "fs2-io"
        def version: String = peknight.version
      }
    }
    object circe extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "circe-core"
      def version: String = peknight.version
      object parser extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "circe-parser"
        def version: String = peknight.version
      }
    }
    object http4s extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "http4s-core"
      def version: String = peknight.version
    }
    object spire extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "spire-core"
      def version: String = peknight.version
    }
    object log4Cats extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "log4cats-core"
      def version: String = peknight.version
    }
    object scalaCheck extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "scalacheck-core"
      def version: String = peknight.version
    }
    object generic extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "generic-core"
      def version: String = peknight.version
      object migration extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "generic-migration"
        def version: String = peknight.version
      }
    }
    object error extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "error-core"
      def version: String = peknight.version
      object spire extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "error-spire"
        def version: String = peknight.version
      }
    }
    object random extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "random-core"
      def version: String = peknight.version
    }
    object validation extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "validation-core"
      def version: String = peknight.version
      object spire extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "validation-spire"
        def version: String = peknight.version
      }
    }
    object method extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "method-core"
      def version: String = peknight.version
    }
    object logging extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "logging-core"
      def version: String = peknight.version
      object logback {
        object config extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "logback-config"
          def version: String = peknight.version
        }
      }
    }
    object codec extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "codec-core"
      def version: String = peknight.version
      object effect extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-effect"
        def version: String = peknight.version
      }
      object fs2 {
        object io extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "codec-fs2-io"
          def version: String = peknight.version
        }
      }
      object base extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-base"
        def version: String = peknight.version
      }
      object circe extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-circe"
        def version: String = peknight.version
        object parser extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "codec-circe-parser"
          def version: String = peknight.version
        }
      }
      object ip4s extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-ip4s"
        def version: String = peknight.version
      }
      object http4s extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-http4s"
        def version: String = peknight.version
        object circe extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "codec-http4s-circe"
          def version: String = peknight.version
        }
      }
    }
    object query extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "query-core"
      def version: String = peknight.version
      object http4s extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "query-http4s"
        def version: String = peknight.version
      }
    }
    object security extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "security-core"
      def version: String = peknight.version
      object bouncyCastle {
        object provider extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "security-bcprov"
          def version: String = peknight.version
        }
        object pkix extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "security-bcpkix"
          def version: String = peknight.version
        }
      }
      object http4s extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "security-http4s"
        def version: String = peknight.version
      }
      object instances {
        object codec extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "security-codec-instances"
          def version: String = peknight.version
        }
      }
    }
    object jose extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "jose-core"
      def version: String = peknight.version
    }
    object api extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "api-core"
      def version: String = peknight.version
      object instances {
        object codec extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "api-codec-instances"
          def version: String = peknight.version
        }
      }
    }
    object app extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "app-core"
      def version: String = peknight.version
    }
    object auth extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "auth-core"
      def version: String = peknight.version
      object http4s extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "auth-http4s"
        def version: String = peknight.version
      }
    }
    object os extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "os-core"
      def version: String = peknight.version
    }
    object docker extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "docker-core"
      def version: String = peknight.version
      object client extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "docker-client"
        def version: String = peknight.version
      }
      object service extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "docker-service"
        def version: String = peknight.version
      }
      object custom extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "docker-custom"
        def version: String = peknight.version
      }
    }
    object network {
      object proxy {
        object reverse {
          object http4s extends Module {
            def groupId: String = peknight.groupId
            def artifactId: String = "reverse-proxy-http4s"
            def version: String = peknight.version
          }
        }
      }
    }
    object http extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "http-core"
      def version: String = peknight.version
    }
    object cloudflare extends Module {
      def groupId: String = s"${peknight.groupId}.cloudflare"
      def artifactId: String = "core"
      def version: String = peknight.version
      object zone extends Module {
        def groupId: String = cloudflare.groupId
        def artifactId: String = "zone-core"
        def version: String = peknight.version
        object config extends Module {
          def groupId: String = cloudflare.groupId
          def artifactId: String = "zone-config"
          def version: String = peknight.version
        }
      }
      object dns {
        object record extends Module {
          def groupId: String = cloudflare.groupId
          def artifactId: String = "dns-record-core"
          def version: String = peknight.version
          object api extends Module {
            def groupId: String = cloudflare.groupId
            def artifactId: String = "dns-record-api"
            def version: String = peknight.version
          }
          object http4s extends Module {
            def groupId: String = cloudflare.groupId
            def artifactId: String = "dns-record-http4s"
            def version: String = peknight.version
          }
        }
      }
    }
    object acme extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "acme-core"
      def version: String = peknight.version
      object client extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "acme-client-core"
        def version: String = peknight.version
        object app extends Module {
          def groupId: String = peknight.groupId
          def artifactId: String = "acme-client-app"
          def version: String = peknight.version
        }
      }
    }
  }

  /** https://www.scala-lang.org/ */
  object scala extends GroupID {
    def groupId: String = "org.scala-lang"
    object scala3 extends Version {
      def version: String = "3.7.4"
    }
  }

  /** https://www.scala-sbt.org/ */
  object sbtScala extends Module {
    def groupId: String = "org.scala-sbt"
    def artifactId: String = "sbt"
    def version: String = "1.11.7"
  }

  object sbtGithub extends GroupID {
    def groupId: String = "com.github.sbt"
    /** https://github.com/sbt/sbt-native-packager */
    object nativePackager extends Module {
      def groupId: String = sbtGithub.groupId
      def artifactId: String = "sbt-native-packager"
      def version: String = "1.11.4"
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
    def version: String = "1.20.1"
    object sbt extends Module {
      def groupId: String = scalaJs.groupId
      def artifactId: String = "sbt-scalajs"
      def version: String = scalaJs.version
    }
  }

  /** https://scala-native.org/en/stable/ */
  object scalaNative extends GroupID with Version {
    def groupId: String = "org.scala-native"
    def version: String = "0.5.9"
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
      /** https://mvnrepository.com/artifact/org.typelevel/cats-effect-testing-scalatest */
      object testingScalaTest extends Module {
        def groupId: String = typelevel.groupId
        def artifactId: String = "cats-effect-testing-scalatest"
        def version: String = "1.7.0"
      }
    }
    /** https://mvnrepository.com/artifact/org.typelevel/cats-parse */
    object catsParse extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-parse"
      def version: String = "0.3.10"
    }
    /** https://mvnrepository.com/artifact/org.typelevel/spire */
    object spire extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "spire"
      def version: String = "0.18.0"
    }
    /** https://mvnrepository.com/artifact/org.typelevel/squants */
    object squants extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "squants"
      def version: String = "1.8.3"
    }
    /** https://mvnrepository.com/artifact/org.typelevel/log4cats-core */
    object log4Cats extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "log4cats-core"
      def version: String = "2.7.1"
      /** https://mvnrepository.com/artifact/org.typelevel/log4cats-slf4j */
      object slf4j extends Module {
        def groupId: String = typelevel.groupId
        def artifactId: String = "log4cats-slf4j"
        def version: String = log4Cats.version
      }
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

  /** https://mvnrepository.com/artifact/co.fs2/fs2-core */
  object fs2 extends Module {
    def groupId: String = "co.fs2"
    def artifactId: String = "fs2-core"
    def version: String = "3.12.2"
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
    def version: String = "0.14.15"
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

  object gnieh extends GroupID {
    def groupId: String = "org.gnieh"
    object fs2 {
      object data {
        object json {
          // https://mvnrepository.com/artifact/org.gnieh/fs2-data-json-circe
          object circe extends Module {
            def groupId: String = gnieh.groupId
            def artifactId: String = "fs2-data-json-circe"
            def version: String = "1.12.0"
          }
        }
      }
    }
  }

  object tpolecat extends GroupID {
    def groupId: String = "org.tpolecat"
    /** https://mvnrepository.com/artifact/org.tpolecat/doobie-core */
    object doobie extends Module {
      def groupId: String = tpolecat.groupId
      def artifactId: String = "doobie-core"
      def version: String = "1.0.0-RC11"
    }
    /** https://mvnrepository.com/artifact/org.tpolecat/natchez-core */
    object natchez extends Module {
      def groupId: String = tpolecat.groupId
      def artifactId: String = "natchez-core"
      def version: String = "0.3.8"
    }
  }

  object cir extends GroupID {
    def groupId: String = "is.cir"
    /** https://mvnrepository.com/artifact/is.cir/ciris */
    object ciris extends Module {
      def groupId: String = cir.groupId
      def artifactId: String = "ciris"
      def version = "3.12.0"
    }
  }

  object comcast extends GroupID {
    def groupId: String = "com.comcast"
    /** https://mvnrepository.com/artifact/com.comcast/ip4s-core */
    object ip4s extends Module {
      def groupId: String = comcast.groupId
      def artifactId: String = "ip4s-core"
      def version = "3.7.0"
    }
  }

  /** https://mvnrepository.com/artifact/org.http4s/http4s-core */
  object http4s extends Module {
    def groupId: String = "org.http4s"
    def artifactId: String = "http4s-core"
    def version: String = "1.0.0-M34"
    /** https://mvnrepository.com/artifact/org.http4s/http4s-dsl */
    object dsl extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-dsl"
      def version: String = http4s.version
    }
    /** https://mvnrepository.com/artifact/org.http4s/http4s-server */
    object server extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-server"
      def version: String = http4s.version
    }
    /** https://mvnrepository.com/artifact/org.http4s/http4s-client */
    object client extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-client"
      def version: String = http4s.version
    }
    /** https://mvnrepository.com/artifact/org.http4s/http4s-circe */
    object circe extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-circe"
      def version: String = http4s.version
    }
    object ember {
      /** https://mvnrepository.com/artifact/org.http4s/http4s-ember-server */
      object server extends Module {
        def groupId: String = http4s.groupId
        def artifactId: String = "http4s-ember-server"
        def version: String = http4s.version
      }
      /** https://mvnrepository.com/artifact/org.http4s/http4s-ember-client */
      object client extends Module {
        def groupId: String = http4s.groupId
        def artifactId: String = "http4s-ember-client"
        def version: String = http4s.version
      }
    }
    object jdk {
      object http {
        /** https://mvnrepository.com/artifact/org.http4s/http4s-jdk-http-client */
        object client extends Module {
          def groupId: String = http4s.groupId
          def artifactId: String = "http4s-jdk-http-client"
          def version: String = "1.0.0-M3"
        }
      }
    }
  }

  object optics extends GroupID {
    def groupId: String = "dev.optics"
    /** https://mvnrepository.com/artifact/dev.optics/monocle-core */
    object monocle extends Module {
      def groupId: String = optics.groupId
      def artifactId: String = "monocle-core"
      def version: String = "3.3.0"
    }
  }

  object bouncyCastle extends GroupID with Version {
    def groupId: String = "org.bouncycastle"
    def version: String = "1.83"
    /** https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on */
    object provider extends Module {
      def groupId: String = bouncyCastle.groupId
      def artifactId: String = "bcprov-jdk18on"
      def version: String = bouncyCastle.version
    }
    /** https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk18on */
    object pkix extends Module {
      def groupId: String = bouncyCastle.groupId
      def artifactId: String = "bcpkix-jdk18on"
      def version: String = bouncyCastle.version
    }
  }
  /** https://mvnrepository.com/artifact/ch.qos.logback/logback-core */
  object logback extends Module {
    def groupId: String = "ch.qos.logback"
    def artifactId: String = "logback-core"
    def version: String = "1.5.22"
    /** https://mvnrepository.com/artifact/ch.qos.logback/logback-classic */
    object classic extends Module {
      def groupId: String = logback.groupId
      def artifactId: String = "logback-classic"
      def version: String = logback.version
    }
  }
  /** https://mvnrepository.com/artifact/org.scalacheck/scalacheck */
  object scalaCheck extends Module {
    def groupId: String = "org.scalacheck"
    def artifactId: String = "scalacheck"
    def version: String = "1.19.0"
  }

  /** https://mvnrepository.com/artifact/org.scalatest/scalatest */
  object scalaTest extends Module {
    def groupId: String = "org.scalatest"
    def artifactId: String = "scalatest"
    def version: String = "3.2.19"
    /** https://mvnrepository.com/artifact/org.scalatest/scalatest-flatspec */
    object flatSpec extends Module {
      def groupId: String = scalaTest.groupId
      def artifactId: String = "scalatest-flatspec"
      def version: String = scalaTest.version
    }
  }

  /** https://mvnrepository.com/artifact/io.vavr/vavr */
  object vavr extends Module {
    def groupId: String = "io.vavr"
    def artifactId: String = "vavr"
    def version: String = "0.11.0"
  }

  /** https://mvnrepository.com/artifact/org.projectlombok/lombok */
  object lombok extends Module {
    def groupId: String = "org.projectlombok"
    def artifactId: String = "lombok"
    def version: String = "1.18.42"
    def processorOptions: Seq[String] = Seq("-processor", "lombok.launch.AnnotationProcessorHider$AnnotationProcessor")
  }

  object spring extends GroupID with Version {
    def groupId: String = "org.springframework"
    def version: String = "6.2.15"
    /** https://mvnrepository.com/artifact/org.springframework/spring-context */
    object context extends Module {
      def groupId: String = spring.groupId
      def artifactId: String = "spring-context"
      def version: String = spring.version
    }
  }

  object apache {
    object commons extends GroupID {
      def groupId: String = "org.apache.commons"
      /** https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 */
      object lang3 extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-lang3"
        def version: String = "3.20.0"
      }
      /** https://mvnrepository.com/artifact/org.apache.commons/commons-text */
      object text extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-text"
        def version: String = "1.15.0"
      }
    }
  }

  object google {
    /** https://mvnrepository.com/artifact/com.google.guava/guava */
    object guava extends Module {
      def groupId: String = "com.google.guava"
      def artifactId: String = "guava"
      def version: String = "33.5.0-jre"
    }
  }

  object alibaba extends GroupID {
    def groupId: String = "com.alibaba"
    /** https://mvnrepository.com/artifact/com.alibaba.fastjson2/fastjson2 */
    object fastjson2 extends Module {
      def groupId: String = s"${alibaba.groupId}.fastjson2"
      def artifactId: String = "fastjson2"
      def version: String = "2.0.60"
    }
    /** https://mvnrepository.com/artifact/com.alibaba/QLExpress */
    object qlExpress extends Module {
      def groupId: String = alibaba.groupId
      def artifactId: String = "QLExpress"
      def version: String = "3.3.4"
    }
    /** https://mvnrepository.com/artifact/com.alibaba/qlexpress4 */
    object qlExpress4 extends Module {
      def groupId: String = alibaba.groupId
      def artifactId: String = "qlexpress4"
      def version: String = "4.0.7"
    }
  }

  object yomahub extends GroupID {
    def groupId: String = "com.yomahub"
    /** https://mvnrepository.com/artifact/com.yomahub/liteflow-core */
    object liteflow extends Module {
      def groupId: String = yomahub.groupId
      def artifactId: String = "liteflow-core"
      def version: String = "2.15.2"
    }
  }
}
