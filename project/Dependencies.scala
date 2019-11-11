import sbt._

object Dependencies {
  val play = "com.typesafe.play" %% "play" % "2.7.0"
  val slick = "com.typesafe.play" %% "play-slick" % "3.0.0"
  val postgresql = "org.postgresql" % "postgresql" % "9.4.1209"
  val guava = "com.google.guava" % "guava" % "27.0-jre"
  val gson = "com.google.code.gson" % "gson" % "2.8.5"
  val scalatest = "org.scalatest" %% "scalatest" % "3.0.8" % "test"
  val mockito = "org.mockito" % "mockito-core" % "2.23.4" % Test
}