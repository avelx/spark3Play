import model.{Price, Trade}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

import scala.util.Random

object Runner extends App {

  import org.apache.log4j.Logger
  import org.apache.log4j.Level

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val conf = new SparkConf()
    .setMaster("local[*]")
    .set("spark.cassandra.connection.host", "192.168.0.8")
    .set("spark.cassandra.auth.username", "cassandra")
    .set("spark.cassandra.auth.password", "cassandra")
    .setAppName("DataSetResearchApp")

  val spark = SparkSession
    .builder()
    .config(conf)
    .getOrCreate()

  import spark.implicits._

  val trades = spark
    .read
    .option("header", "true")
    .csv("hdfs://zeta.avel.local:9000/data/bitstampUSD.csv")
    .as[Trade]
//    .cache()

  import data.Validator._

  def asPrices(input: Dataset[Trade]) : Dataset[Price] = {
    input.map(t =>
      Price(t.Timestamp.toInt, t.Open, t.High, t.Low, t.Close)
    )
  }

  def withErrorCode(input: Dataset[Price]) : Dataset[Price] = {
    input
      .map(rec => rec.copy(error_code = getErrorCodeA(rec)))
  }

  val pricesNoErrors = asPrices(trades)
    .filter(r => r.open.isDefined || r.close.isDefined)

  val priceWithErrors = withErrorCode(pricesNoErrors)

  val guid = Random.alphanumeric.take(10).toList.mkString("")

  println( pricesNoErrors.rdd.toDebugString )

//  priceWithErrors.
//      write
//      .json(s"hdfs://zeta.avel.local:9000/data/usd-temp/$guid-USD.csv")

  val dfprev = pricesNoErrors
    .write.format("org.apache.spark.sql.cassandra")
    .options(Map("keyspace"->"spark_demo","table"->"price"))
    .mode(SaveMode.Append)
    .save()

  readLine()

  spark.close()
}