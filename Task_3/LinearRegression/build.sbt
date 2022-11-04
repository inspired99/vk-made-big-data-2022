name := "LinearRegression"

version := "0.1"

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "2.1.0",

  // The visualization library is distributed separately as well.
  "org.scalanlp" %% "breeze-viz" % "2.1.0"
)