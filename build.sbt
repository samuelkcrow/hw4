// See README.md for license details.

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.2.0"
ThisBuild / organization     := "UCSC-AHD"

val chiselVersion = "3.5.6"

lazy val root = (project in file("."))
  .settings(
    name := "hw4",
    libraryDependencies ++= Seq(
      "edu.berkeley.cs" %% "chisel3" % chiselVersion,
      "edu.berkeley.cs" %% "chiseltest" % "0.5.6" % "test"
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
    ),
    addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % chiselVersion cross CrossVersion.full),
  )

libraryDependencies += "org.scalatestplus" %% "junit-4-13" % "3.2.10.0" % "test"
