name := "akka-remoting-poc"

version := "0.1"

scalaVersion := "2.13.0"

lazy val commonDependencies  = Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.25",
  "com.typesafe.akka" %% "akka-remote" % "2.5.25"
)

lazy val settings = Seq(
  scalacOptions ++=  Seq(
    "-unchecked",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-encoding",
    "utf8"
  )
)

lazy val local = project
  .settings(settings,
    libraryDependencies ++= commonDependencies
  )

lazy val remote = project
  .settings(settings,
    libraryDependencies ++= commonDependencies
  )

lazy val global = project
.in(file("."))
.aggregate(
  local,
  remote
)