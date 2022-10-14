ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "game-of-life"
  )

Compile/mainClass := Some("main.Main")

val AkkaVersion = "2.6.18"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding-typed" % AkkaVersion,
  "org.iq80.leveldb"  % "leveldb"          % "0.12",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",

  //  "org.slf4j" % "slf4j-log4j12" % "1.7.7",
  //  "org.slf4j" % "slf4j-jcl" % "1.7.7",
  //  "org.slf4j" % "slf4j-jdk14" % "1.7.7",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.0"
//  "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
)