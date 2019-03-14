name := "Routing"

version := "1.0"

scalaVersion := "2.11.7"

sbtVersion := "0.13.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.0",
  
  "com.typesafe.akka"           %% "akka-persistence" % "2.4.0",
  "org.iq80.leveldb"            % "leveldb"           % "0.7",

  //cluster dependencies
  "com.typesafe.akka" %% "akka-cluster" % "2.4.0",

  //test dependencies
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.0" % "test")