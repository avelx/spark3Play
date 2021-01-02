name := "spark3Play"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.1"

// https://mvnrepository.com/artifact/com.datastax.dse/dse-spark-dependencies
val cassandraVersion = "3.2"
val scalaTestVersion = "3.0.0"
val connectorVersion = "2.0.10"

// https://mvnrepository.com/artifact/com.datastax.spark/spark-cassandra-connector
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.5.1"
libraryDependencies +=  "com.datastax.cassandra" % "cassandra-driver-core" % "3.3.0"
libraryDependencies +=  "joda-time" % "joda-time" % "2.9.4"

//resolvers += Resolver.mavenLocal
//resolvers += "DataStax Repo" at "https://repo.datastax.com/public-repos/"
//val dseVersion = "6.8.1"
//
//libraryDependencies += "com.datastax.dse" % "dse-spark-dependencies" % dseVersion % "provided" exclude(
//  "org.slf4j", "log4j-over-slf4j")
//
//libraryDependencies ++= Seq(
//  "com.datastax.spark" %% "spark-cassandra-connector-embedded" % connectorVersion % "test" ,
//  "org.apache.cassandra" % "cassandra-all" % cassandraVersion % "test",
//  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
//  "junit" % "junit" % "4.12" % "test"
//)