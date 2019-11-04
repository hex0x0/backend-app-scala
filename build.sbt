import Dependencies._

name := "MyApp"
version := "0.1"
scalaVersion := "2.12.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(guice, ws, play, slick, postgresql, guava, gson)
