import Dependencies._

name := "my-app"
version := "0.1"
scalaVersion := "2.12.8"

enablePlugins(DockerPlugin)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(guice, ws, play, slick, postgresql,
  guava, gson, scalatest, mockito, redis)

javaOptions in Universal ++= Seq(
  "-Dpidfile.path=/dev/null"
)

maintainer in Docker := "sqh <sqh1107@gmail.com>"
packageSummary in Docker := "A demo of application. scala + play + slick + postgresql + docker"
dockerExposedPorts += 9000
dockerUsername := Some("17hao")