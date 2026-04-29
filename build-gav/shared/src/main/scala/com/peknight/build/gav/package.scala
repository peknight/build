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

  /** https://repo.maven.apache.org/maven2/org/scala-lang/scala3-library_3/ */
  object scala extends GroupID {
    def groupId: String = "org.scala-lang"
    object scala3 extends Version {
      def version: String = "3.8.3"
    }
  }

  /** https://repo.maven.apache.org/maven2/org/scala-sbt/sbt/ */
  object sbtScala extends Module {
    def groupId: String = "org.scala-sbt"
    def artifactId: String = "sbt"
    def version: String = "1.12.9"
  }

  object sbtGithub extends GroupID {
    def groupId: String = "com.github.sbt"
    /** https://repo.maven.apache.org/maven2/com/github/sbt/sbt-native-packager_2.12_1.0/ */
    object nativePackager extends Module {
      def groupId: String = sbtGithub.groupId
      def artifactId: String = "sbt-native-packager"
      def version: String = "1.11.7"
    }
  }

  object portableScala extends GroupID {
    def groupId: String = "org.portable-scala"
    /** https://repo.maven.apache.org/maven2/org/portable-scala/sbt-crossproject_2.12_1.0/ */
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
    /** https://repo.maven.apache.org/maven2/org/portable-scala/sbt-platform-deps_2.12_1.0/ */
    object sbtPlatformDeps extends Module {
      def groupId: String = portableScala.groupId
      def artifactId: String = "sbt-platform-deps_2.12_1.0"
      def version: String = "1.0.2"
    }
  }

  /** https://repo.maven.apache.org/maven2/org/scala-js/sbt-scalajs_2.12_1.0/ */
  object scalaJs extends GroupID with Version {
    def groupId: String = "org.scala-js"
    def version: String = "1.21.0"
    object sbt extends Module {
      def groupId: String = scalaJs.groupId
      def artifactId: String = "sbt-scalajs"
      def version: String = scalaJs.version
    }
  }

  /** https://repo.maven.apache.org/maven2/org/scala-native/sbt-scala-native_2.12_1.0/ */
  object scalaNative extends GroupID with Version {
    def groupId: String = "org.scala-native"
    def version: String = "0.5.11"
    object sbt extends Module {
      def groupId: String = scalaNative.groupId
      def artifactId: String = "sbt-scala-native"
      def version: String = scalaNative.version
    }
  }

  object typelevel extends GroupID {
    def groupId: String = "org.typelevel"
    /** https://repo.maven.apache.org/maven2/org/typelevel/cats-core_3/ */
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
    /** https://repo.maven.apache.org/maven2/org/typelevel/cats-effect_3/ */
    object catsEffect extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-effect"
      def version: String = "3.7.0"
      /** https://repo.maven.apache.org/maven2/org/typelevel/cats-effect-testing-scalatest_3/ */
      object testingScalaTest extends Module {
        def groupId: String = typelevel.groupId
        def artifactId: String = "cats-effect-testing-scalatest"
        def version: String = "1.8.0"
      }
    }
    /** https://repo.maven.apache.org/maven2/org/typelevel/cats-parse_3/ */
    object catsParse extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "cats-parse"
      def version: String = "0.3.10"
    }
    /** https://repo.maven.apache.org/maven2/org/typelevel/case-insensitive_3/ */
    object caseInsensitive extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "case-insensitive"
      def version: String = "1.5.0"
    }
    /** https://repo.maven.apache.org/maven2/org/typelevel/spire_3/ */
    object spire extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "spire"
      def version: String = "0.18.0"
    }
    /** https://repo.maven.apache.org/maven2/org/typelevel/squants_3/ */
    object squants extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "squants"
      def version: String = "1.8.3"
    }
    /** https://repo.maven.apache.org/maven2/org/typelevel/log4cats-core_3/ */
    object log4Cats extends Module {
      def groupId: String = typelevel.groupId
      def artifactId: String = "log4cats-core"
      def version: String = "2.8.0"
      /** https://repo.maven.apache.org/maven2/org/typelevel/log4cats-slf4j_3/ */
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
    /** https://repo.maven.apache.org/maven2/org/scodec/scodec-bits_3/ */
    object bits extends Module {
      def groupId: String = scodec.groupId
      def artifactId: String = "scodec-bits"
      def version: String = scodec.version
    }
  }
  /** https://repo.maven.apache.org/maven2/co/fs2/fs2-core_3/ */
  object fs2 extends Module {
    def groupId: String = "co.fs2"
    def artifactId: String = "fs2-core"
    def version: String = "3.13.0"
    /** https://repo.maven.apache.org/maven2/co/fs2/fs2-io_3/ */
    object io extends Module {
      def groupId: String = fs2.groupId
      def artifactId: String = "fs2-io"
      def version: String = fs2.version
    }
  }

  /** https://repo.maven.apache.org/maven2/io/circe/circe-core_3/ */
  object circe extends Module {
    def groupId: String = "io.circe"
    def artifactId: String = "circe-core"
    def version: String = "0.14.15"
    /** https://repo.maven.apache.org/maven2/io/circe/circe-parser_3/ */
    object parser extends Module {
      def groupId: String = circe.groupId
      def artifactId: String = "circe-parser"
      def version: String = circe.version
    }
    /** https://repo.maven.apache.org/maven2/io/circe/circe-jawn_3/ */
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
          /** https://repo.maven.apache.org/maven2/org/gnieh/fs2-data-json-circe_3/ */
          object circe extends Module {
            def groupId: String = gnieh.groupId
            def artifactId: String = "fs2-data-json-circe"
            def version: String = "1.13.0"
          }
        }
      }
    }
  }

  object tpolecat extends GroupID {
    def groupId: String = "org.tpolecat"
    /** https://repo.maven.apache.org/maven2/org/tpolecat/doobie-core_3/ */
    object doobie extends Module {
      def groupId: String = tpolecat.groupId
      def artifactId: String = "doobie-core"
      def version: String = "1.0.0-RC12"
      /** https://repo.maven.apache.org/maven2/org/tpolecat/doobie-postgres_3/ */
      object postgres extends Module {
        def groupId: String = tpolecat.groupId
        def artifactId: String = "doobie-postgres"
        def version: String = doobie.version
      }
      /** https://repo.maven.apache.org/maven2/org/tpolecat/doobie-hikari_3/ */
      object hikari extends Module {
        def groupId: String = tpolecat.groupId
        def artifactId: String = "doobie-hikari"
        def version: String = doobie.version
      }
    }
    /** https://repo.maven.apache.org/maven2/org/tpolecat/natchez-core_3/ */
    object natchez extends Module {
      def groupId: String = tpolecat.groupId
      def artifactId: String = "natchez-core"
      def version: String = "0.3.9"
    }
  }

  object cir extends GroupID {
    def groupId: String = "is.cir"
    /** https://repo.maven.apache.org/maven2/is/cir/ciris_3/ */
    object ciris extends Module {
      def groupId: String = cir.groupId
      def artifactId: String = "ciris"
      def version = "3.14.1"
    }
  }

  object comcast extends GroupID {
    def groupId: String = "com.comcast"
    /** https://repo.maven.apache.org/maven2/com/comcast/ip4s-core_3/ */
    object ip4s extends Module {
      def groupId: String = comcast.groupId
      def artifactId: String = "ip4s-core"
      def version = "3.8.0"
    }
  }

  /** https://repo.maven.apache.org/maven2/org/http4s/http4s-core_3/ */
  object http4s extends Module {
    def groupId: String = "org.http4s"
    def artifactId: String = "http4s-core"
    def version: String = "1.0.0-M34"
    /** https://repo.maven.apache.org/maven2/org/http4s/http4s-dsl_3/ */
    object dsl extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-dsl"
      def version: String = http4s.version
    }
    /** https://repo.maven.apache.org/maven2/org/http4s/http4s-server_3/ */
    object server extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-server"
      def version: String = http4s.version
    }
    /** https://repo.maven.apache.org/maven2/org/http4s/http4s-client_3/ */
    object client extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-client"
      def version: String = http4s.version
    }
    /** https://repo.maven.apache.org/maven2/org/http4s/http4s-circe_3/ */
    object circe extends Module {
      def groupId: String = http4s.groupId
      def artifactId: String = "http4s-circe"
      def version: String = http4s.version
    }
    object ember {
      /** https://repo.maven.apache.org/maven2/org/http4s/http4s-ember-server_3/ */
      object server extends Module {
        def groupId: String = http4s.groupId
        def artifactId: String = "http4s-ember-server"
        def version: String = http4s.version
      }
      /** https://repo.maven.apache.org/maven2/org/http4s/http4s-ember-client_3/ */
      object client extends Module {
        def groupId: String = http4s.groupId
        def artifactId: String = "http4s-ember-client"
        def version: String = http4s.version
      }
    }
    object jdk {
      object http {
        /** https://repo.maven.apache.org/maven2/org/http4s/http4s-jdk-http-client_3/ */
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
    /** https://repo.maven.apache.org/maven2/dev/optics/monocle-core_3/ */
    object monocle extends Module {
      def groupId: String = optics.groupId
      def artifactId: String = "monocle-core"
      def version: String = "3.3.0"
    }
  }

  object bouncyCastle extends GroupID with Version {
    def groupId: String = "org.bouncycastle"
    def version: String = "1.84"
    /** https://repo.maven.apache.org/maven2/org/bouncycastle/bcprov-jdk18on/ */
    object provider extends Module {
      def groupId: String = bouncyCastle.groupId
      def artifactId: String = "bcprov-jdk18on"
      def version: String = bouncyCastle.version
    }
    /** https://repo.maven.apache.org/maven2/org/bouncycastle/bcpkix-jdk18on/ */
    object pkix extends Module {
      def groupId: String = bouncyCastle.groupId
      def artifactId: String = "bcpkix-jdk18on"
      def version: String = bouncyCastle.version
    }
  }
  /** https://repo.maven.apache.org/maven2/ch/qos/logback/logback-core/ */
  object logback extends Module {
    def groupId: String = "ch.qos.logback"
    def artifactId: String = "logback-core"
    def version: String = "1.5.32"
    /** https://repo.maven.apache.org/maven2/ch/qos/logback/logback-classic/ */
    object classic extends Module {
      def groupId: String = logback.groupId
      def artifactId: String = "logback-classic"
      def version: String = logback.version
    }
  }
  /** https://repo.maven.apache.org/maven2/org/scalacheck/scalacheck_3/ */
  object scalaCheck extends Module {
    def groupId: String = "org.scalacheck"
    def artifactId: String = "scalacheck"
    def version: String = "1.19.0"
  }

  /** https://repo.maven.apache.org/maven2/org/scalatest/scalatest_3/ */
  object scalaTest extends Module {
    def groupId: String = "org.scalatest"
    def artifactId: String = "scalatest"
    def version: String = "3.2.20"
    /** https://repo.maven.apache.org/maven2/org/scalatest/scalatest-flatspec_3/ */
    object flatSpec extends Module {
      def groupId: String = scalaTest.groupId
      def artifactId: String = "scalatest-flatspec"
      def version: String = scalaTest.version
    }
  }

  /** https://repo.maven.apache.org/maven2/io/vavr/vavr/ */
  object vavr extends Module {
    def groupId: String = "io.vavr"
    def artifactId: String = "vavr"
    def version: String = "1.0.1"
  }

  /** https://repo.maven.apache.org/maven2/org/projectlombok/lombok/ */
  object lombok extends Module {
    def groupId: String = "org.projectlombok"
    def artifactId: String = "lombok"
    def version: String = "1.18.44"
    def processorOptions: Seq[String] = Seq("-processor", "lombok.launch.AnnotationProcessorHider$AnnotationProcessor")
  }

  object spring extends GroupID with Version {
    def groupId: String = "org.springframework"
    def version: String = "7.0.7"
    /** https://repo.maven.apache.org/maven2/org/springframework/spring-context/ */
    object context extends Module {
      def groupId: String = spring.groupId
      def artifactId: String = "spring-context"
      def version: String = spring.version
    }
  }

  object apache {
    object commons extends GroupID {
      def groupId: String = "org.apache.commons"
      /** https://repo.maven.apache.org/maven2/org/apache/commons/commons-lang3/ */
      object lang3 extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-lang3"
        def version: String = "3.20.0"
      }
      /** https://repo.maven.apache.org/maven2/org/apache/commons/commons-text/ */
      object text extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-text"
        def version: String = "1.15.0"
      }
      /** https://repo.maven.apache.org/maven2/org/apache/commons/commons-compress/ */
      object compress extends Module {
        def groupId: String = commons.groupId
        def artifactId: String = "commons-compress"
        def version: String = "1.28.0"
      }
    }
  }

  object google {
    /** https://repo.maven.apache.org/maven2/com/google/guava/guava/ */
    object guava extends Module {
      def groupId: String = "com.google.guava"
      def artifactId: String = "guava"
      def version: String = "33.6.0-jre"
    }
  }

  object alibaba extends GroupID {
    def groupId: String = "com.alibaba"
    /** https://repo.maven.apache.org/maven2/com/alibaba/fastjson2/fastjson2/ */
    object fastjson2 extends Module {
      def groupId: String = s"${alibaba.groupId}.fastjson2"
      def artifactId: String = "fastjson2"
      def version: String = "2.0.61"
    }
    /** https://repo.maven.apache.org/maven2/com/alibaba/QLExpress/ */
    object qlExpress extends Module {
      def groupId: String = alibaba.groupId
      def artifactId: String = "QLExpress"
      def version: String = "3.3.4"
    }
    /** https://repo.maven.apache.org/maven2/com/alibaba/qlexpress4/ */
    object qlExpress4 extends Module {
      def groupId: String = alibaba.groupId
      def artifactId: String = "qlexpress4"
      def version: String = "4.1.0"
    }
  }

  /** https://repo.maven.apache.org/maven2/net/lingala/zip4j/zip4j/ */
  object zip4j extends Module {
    def groupId: String = "net.lingala.zip4j"
    def artifactId: String = "zip4j"
    def version: String = "2.11.6"
  }

  object tukaani {
    /** https://repo.maven.apache.org/maven2/org/tukaani/xz/ */
    object xz extends Module {
      def groupId: String = "org.tukaani"
      def artifactId: String = "xz"
      def version: String = "1.12"
    }
  }

  object yomahub extends GroupID {
    def groupId: String = "com.yomahub"
    /** https://repo.maven.apache.org/maven2/com/yomahub/liteflow-core/ */
    object liteflow extends Module {
      def groupId: String = yomahub.groupId
      def artifactId: String = "liteflow-core"
      def version: String = "2.15.3.2"
    }
  }
}
