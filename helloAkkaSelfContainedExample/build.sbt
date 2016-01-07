lazy val root = (project in file(".")).
  settings(
    name := "helloAkka",
    version := "1.0",
    scalaVersion := "2.10.0",
    mainClass in Compile := Some("myPackage.Main")        
  )

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.10" % "2.2-M1"
)

// META-INF discarding
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
   {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
   }
}

