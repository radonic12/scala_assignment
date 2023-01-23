scalaVersion := "2.12.17"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1"
libraryDependencies += "com.softwaremill.sttp.client" %% "core" % "2.3.0"
libraryDependencies += "com.lihaoyi" %% "upickle" % "3.0.0-M1"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.32.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

scalacOptions += "-target:jvm-1.8"

envVars := Map("SCALA_ENV" -> "test")