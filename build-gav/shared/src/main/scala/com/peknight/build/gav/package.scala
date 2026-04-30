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
      object tar extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "fs2-tar"
        def version: String = peknight.version
      }
      object zip extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "fs2-zip"
        def version: String = peknight.version
      }
      object xz extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "fs2-xz"
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
      object caseInsensitive extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-case-insensitive"
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
      object doobie extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-doobie"
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
      object squants extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "codec-squants"
        def version: String = peknight.version
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
    object data extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "data-core"
      def version: String = peknight.version
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
      object build extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "app-build"
        def version: String = peknight.version
      }
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
      object build extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "docker-build"
        def version: String = peknight.version
      }
    }
    object network extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "network-core"
      def version: String = peknight.version
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
      object client extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "http-client"
        def version: String = peknight.version
      }
    }
    object database extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "database-core"
      def version: String = peknight.version
      object config extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "database-config"
        def version: String = peknight.version
      }
    }
    object financial extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "financial-core"
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
    object frp extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "frp-core"
      def version: String = peknight.version
      object custom extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "frp-custom"
        def version: String = peknight.version
      }
    }
    object v2ray extends Module {
      def groupId: String = peknight.groupId
      def artifactId: String = "v2ray-core"
      def version: String = peknight.version
      object custom extends Module {
        def groupId: String = peknight.groupId
        def artifactId: String = "v2ray-custom"
        def version: String = peknight.version
      }
    }
  }

  object scala extends GroupID {
    def groupId: String = "org.scala-lang"
    object scala3 extends Version {
      /** @version-check https://repo.maven.apache.org/maven2/org/scala-lang/scala3-library_3/ */
      def version: String = "3.8.3"
    }
  }

  object sbtScala extends Module {
    def groupId: String = "org.scala-sbt"
    def artifactId: String = "sbt"
    /** @version-check https://repo.maven.apache.org/maven2/org/scala-sbt/sbt/ */
    def version: String = "1.12.10"
  }

  object sbtGithub extends GroupID {
    def groupId: String = "com.github.sbt"
    object nativePackager extends Module {
      def groupId: String = sbtGithub.groupId
      def artifactId: String = "sbt-native-packager"
      /** @version-check https://repo.maven.apache.org/maven2/com/github/sbt/sbt-native-packager_2.12_1.0/ */
      def version: String = "1.11.7"
    }
  }

  object portableScala extends GroupID {
    def groupId: String = "org.portable-scala"
    object crossProject extends Version {
      /** @version-check https://repo.maven.apache.org/maven2/org/portable-scala/sbt-crossproject_2.12_1.0/ */
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
    object sbtPlatformDeps extends Module {
      def groupId: String = portableScala.groupId
      def artifactId: String = "sbt-platform-deps_2.12_1.0"
      /** @version-check https://repo.maven.apache.org/maven2/org/portable-scala/sbt-platform-deps_2.12_1.0/ */
      def version: String = "1.0.2"
    }
  }

  object scalaJs extends GroupID with Version {
    def groupId: String = "org.scala-js"
    /** @version-check https://repo.maven.apache.org/maven2/org/scala-js/sbt-scalajs_2.12_1.0/ */
    def version: String = "1.21.0"
    object sbt extends Module {
      def groupId: String = scalaJs.groupId
      def artifactId: String = "sbt-scalajs"
      def version: String = scalaJs.version
    }
  }

  object scalaNative extends GroupID with Version {
    def groupId: String = "org.scala-native"
    /** @version-check https://repo.maven.apache.org/maven2/org/scala-native/sbt-scala-native_2.12_1.0/ */
    def version: String = "0.5.11"
    object sbt extends Module {
      def groupId: String = scalaNative.groupId
      def artifactId: String = "sbt-scala-native"
      def version: String = scalaNative.version
    }
  }

  object typelevel extends GroupID {
    def groupId: String = "org.typelevel"
    object cats extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-core"
      /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/cats-core_3/ */
      def version: String = "2.13.0"
      object laws extends Module {
        def groupId: String = typelevel.groupId
        def artifactId: String = "cats-laws"
        def version: String = cats.version
      }
    }
    object catsEffect extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-effect"
      /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/cats-effect_3/ */
      def version: String = "3.7.0"
      object testingScalaTest extends Module {
        def groupId: String = typelevel.groupId
        def artifactId: String = "cats-effect-testing-scalatest"
        /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/cats-effect-testing-scalatest_3/ */
        def version: String = "1.8.0"
      }
    }
    object catsParse extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-parse"
      /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/cats-parse_3/ */
      def version: String = "0.3.10"
    }
    object caseInsensitive extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "case-insensitive"
      /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/case-insensitive_3/ */
      def version: String = "1.5.0"
    }
    object spire extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "spire"
      /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/spire_3/ */
      def version: String = "0.18.0"
    }
    object squants extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "squants"
      /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/squants_3/ */
      def version: String = "1.8.3"
    }
    object log4Cats extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "log4cats-core"
      /** @version-check https://repo.maven.apache.org/maven2/org/typelevel/log4cats-core_3/ */
      def version: String = "2.8.0"
      object slf4j extends Module {
        def groupId: String = typelevel.groupId
        def artifactId: String = "log4cats-slf4j"
        def version: String = log4Cats.version
      }
    }
  }

  object scodec extends GroupID {
    def groupId: String = "org.scodec"
    object bits extends Module {
      def groupId: String = scodec.groupId
      def artifactId: String = "scodec-bits"
      /** @version-check https://repo.maven.apache.org/maven2/org/scodec/scodec-bits_3/ */
      def version: String = "1.2.4"
    }
  }
  object fs2 extends Module {
    def groupId: String = "co.fs2"
    def artifactId: String = "fs2-core"
    /** @version-check https://repo.maven.apache.org/maven2/co/fs2/fs2-core_3/ */
    def version: String = "3.13.0"
    object io extends Module {
      def groupId: String = fs2.groupId
      def artifactId: String = "fs2-io"
      def version: String = fs2.version
    }
  }

  object circe extends Module {
    def groupId: String = "io.circe"
    def artifactId: String = "circe-core"
    /** @version-check https://repo.maven.apache.org/maven2/io/circe/circe-core_3/ */
    def version: String = "0.14.15"
    object parser extends Module {
      def groupId: String = circe.groupId
      def artifactId: String = "circe-parser"
      def version: String = circe.version
    }
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
          object circe extends Module {
            def groupId: String = gnieh.groupId
            def artifactId: String = "fs2-data-json-circe"
            /** @version-check https://repo.maven.apache.org/maven2/org/gnieh/fs2-data-json-circe_3/ */
            def version: String = "1.13.0"
          }
        }
      }
    }
  }

  object tpolecat extends GroupID {
    def groupId: String = "org.tpolecat"
    object doobie extends Module {
      def groupId: String = tpolecat.groupId
      def artifactId: String = "doobie-core"
      /** @version-check https://repo.maven.apache.org/maven2/org/tpolecat/doobie-core_3/ */
      def version: String = "1.0.0-RC12"
      object postgres extends Module {
        def groupId: String = tpolecat.groupId
        def artifactId: String = "doobie-postgres"
        def version: String = doobie.version
      }
      object hikari extends Module {
        def groupId: String = tpolecat.groupId
        def artifactId: String = "doobie-hikari"
        def version: String = doobie.version
      }
    }
    object natchez extends Module {
      def groupId: String = tpolecat.groupId
      def artifactId: String = "natchez-core"
      /** @version-check https://repo.maven.apache.org/maven2/org/tpolecat/natchez-core_3/ */
      def version: String = "0.3.10"
    }
  }

  object cir extends GroupID {
    def groupId: String = "is.cir"
    object ciris extends Module {
      def groupId: String = cir.groupId
      def artifactId: String = "ciris"
      /** @version-check https://repo.maven.apache.org/maven2/is/cir/ciris_3/ */
      def version = "3.14.1"
    }
  }

  object comcast extends GroupID {
    def groupId: String = "com.comcast"
    object ip4s extends Module {
      def groupId: String = comcast.groupId
      def artifactId: String = "ip4s-core"
      /** @version-check https://repo.maven.apache.org/maven2/com/comcast/ip4s-core_3/ */
      def version = "3.8.0"
    }
  }

  object http4s extends Module {
    def groupId: String = "org.http4s"
    def artifactId: String = "http4s-core"
    /** @version-check https://repo.maven.apache.org/maven2/org/http4s/http4s-core_3/ */
    def version: String = "1.0.0-M34"
    object dsl extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-dsl"
      def version: String = http4s.version
    }
    object server extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-server"
      def version: String = http4s.version
    }
    object client extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-client"
      def version: String = http4s.version
    }
    object circe extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-circe"
      def version: String = http4s.version
    }
    object ember {
      object server extends Module {
        def groupId: String = http4s.groupId
        def artifactId: String = "http4s-ember-server"
        def version: String = http4s.version
      }
      object client extends Module {
        def groupId: String = http4s.groupId
        def artifactId: String = "http4s-ember-client"
        def version: String = http4s.version
      }
    }
    object jdk {
      object http {
        object client extends Module {
          def groupId: String = http4s.groupId
          def artifactId: String = "http4s-jdk-http-client"
          /** @version-check https://repo.maven.apache.org/maven2/org/http4s/http4s-jdk-http-client_3/ */
          def version: String = "1.0.0-M3"
        }
      }
    }
  }

  object optics extends GroupID {
    def groupId: String = "dev.optics"
    object monocle extends Module {
      def groupId: String = optics.groupId
      def artifactId: String = "monocle-core"
      /** @version-check https://repo.maven.apache.org/maven2/dev/optics/monocle-core_3/ */
      def version: String = "3.3.0"
    }
  }

  object bouncyCastle extends GroupID with Version {
    def groupId: String = "org.bouncycastle"
    /** @version-check https://repo.maven.apache.org/maven2/org/bouncycastle/bcprov-jdk18on/ */
    def version: String = "1.84"
    object provider extends Module {
      def groupId: String = bouncyCastle.groupId
      def artifactId: String = "bcprov-jdk18on"
      def version: String = bouncyCastle.version
    }
    object pkix extends Module {
      def groupId: String = bouncyCastle.groupId
      def artifactId: String = "bcpkix-jdk18on"
      def version: String = bouncyCastle.version
    }
  }
  object logback extends Module {
    def groupId: String = "ch.qos.logback"
    def artifactId: String = "logback-core"
    /** @version-check https://repo.maven.apache.org/maven2/ch/qos/logback/logback-core/ */
    def version: String = "1.5.32"
    object classic extends Module {
      def groupId: String = logback.groupId
      def artifactId: String = "logback-classic"
      def version: String = logback.version
    }
  }
  object scalaCheck extends Module {
    def groupId: String = "org.scalacheck"
    def artifactId: String = "scalacheck"
    /** @version-check https://repo.maven.apache.org/maven2/org/scalacheck/scalacheck_3/ */
    def version: String = "1.19.0"
  }

  object scalaTest extends Module {
    def groupId: String = "org.scalatest"
    def artifactId: String = "scalatest"
    /** @version-check https://repo.maven.apache.org/maven2/org/scalatest/scalatest_3/ */
    def version: String = "3.2.20"
    object flatSpec extends Module {
      def groupId: String = scalaTest.groupId
      def artifactId: String = "scalatest-flatspec"
      def version: String = scalaTest.version
    }
  }

  object vavr extends Module {
    def groupId: String = "io.vavr"
    def artifactId: String = "vavr"
    /** @version-check https://repo.maven.apache.org/maven2/io/vavr/vavr/ */
    def version: String = "1.0.1"
  }

  object lombok extends Module {
    def groupId: String = "org.projectlombok"
    def artifactId: String = "lombok"
    /** @version-check https://repo.maven.apache.org/maven2/org/projectlombok/lombok/ */
    def version: String = "1.18.46"
    def processorOptions: Seq[String] = Seq("-processor", "lombok.launch.AnnotationProcessorHider$AnnotationProcessor")
  }

  object spring extends GroupID with Version {
    def groupId: String = "org.springframework"
    /** @version-check https://repo.maven.apache.org/maven2/org/springframework/spring-context/ */
    def version: String = "7.0.7"
    object context extends Module {
      def groupId: String = spring.groupId
      def artifactId: String = "spring-context"
      def version: String = spring.version
    }
  }

  object apache {
    object commons extends GroupID {
      def groupId: String = "org.apache.commons"
      object lang3 extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-lang3"
        /** @version-check https://repo.maven.apache.org/maven2/org/apache/commons/commons-lang3/ */
        def version: String = "3.20.0"
      }
      object text extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-text"
        /** @version-check https://repo.maven.apache.org/maven2/org/apache/commons/commons-text/ */
        def version: String = "1.15.0"
      }
      object compress extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-compress"
        /** @version-check https://repo.maven.apache.org/maven2/org/apache/commons/commons-compress/ */
        def version: String = "1.28.0"
      }
    }
  }

  object google {
    object guava extends Module {
      def groupId: String = "com.google.guava"
      def artifactId: String = "guava"
      /** @version-check https://repo.maven.apache.org/maven2/com/google/guava/guava/ */
      def version: String = "33.6.0-jre"
    }
  }

  object alibaba extends GroupID {
    def groupId: String = "com.alibaba"
    object fastjson2 extends Module {
      def groupId: String = s"${alibaba.groupId}.fastjson2"
      def artifactId: String = "fastjson2"
      /** @version-check https://repo.maven.apache.org/maven2/com/alibaba/fastjson2/fastjson2/ */
      def version: String = "2.0.61"
    }
    object qlExpress extends Module {
      def groupId: String = alibaba.groupId
      def artifactId: String = "QLExpress"
      /** @version-check https://repo.maven.apache.org/maven2/com/alibaba/QLExpress/ */
      def version: String = "3.3.4"
    }
    object qlExpress4 extends Module {
      def groupId: String = alibaba.groupId
      def artifactId: String = "qlexpress4"
      /** @version-check https://repo.maven.apache.org/maven2/com/alibaba/qlexpress4/ */
      def version: String = "4.1.0"
    }
  }

  object zip4j extends Module {
    def groupId: String = "net.lingala.zip4j"
    def artifactId: String = "zip4j"
    /** @version-check https://repo.maven.apache.org/maven2/net/lingala/zip4j/zip4j/ */
    def version: String = "2.11.6"
  }

  object tukaani {
    object xz extends Module {
      def groupId: String = "org.tukaani"
      def artifactId: String = "xz"
      /** @version-check https://repo.maven.apache.org/maven2/org/tukaani/xz/ */
      def version: String = "1.12"
    }
  }

  object yomahub extends GroupID {
    def groupId: String = "com.yomahub"
    object liteflow extends Module {
      def groupId: String = yomahub.groupId
      def artifactId: String = "liteflow-core"
      /** @version-check https://repo.maven.apache.org/maven2/com/yomahub/liteflow-core/ */
      def version: String = "2.15.3.2"
    }
  }
}
