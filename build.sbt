import Dependencies._

name := "my-app"
version := "0.1"
scalaVersion := "2.12.8"

enablePlugins(DockerPlugin)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(guice, ws, play, slick, postgresql,
  guava, gson, scalatest, mockito, redis)

// disable the ability of creating pid file by ProdServerStarter
javaOptions in Universal ++= Seq(
  "-Dpidfile.path=/dev/null"
)

maintainer in Docker := "sqh <sqh1107@gmail.com>"
packageSummary in Docker := "A demo of application. scala + play + slick + postgresql + docker"
dockerExposedPorts := Seq(9000)
dockerRepository := Some("registry.cn-hangzhou.aliyuncs.com")
dockerUsername := Some("15968912980@163.com")
dockerAlias := DockerAlias(None, None, "my-app", Some("0.1"))