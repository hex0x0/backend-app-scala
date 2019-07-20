name := "MyApp"

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice

libraryDependencies += "com.typesafe.play" %% "play" % "2.7.0"

libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.0"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1209"
