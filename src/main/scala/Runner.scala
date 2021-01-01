import model.{ErrorHandling, Price, Trade}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import scala.util.Random

object Runner extends App {

  import org.apache.log4j.Logger
  import org.apache.log4j.Level
  Logger.getLogger("org").setLevel(Level.DEBUG)
  Logger.getLogger("akka").setLevel(Level.DEBUG)

  val conf = new SparkConf()
    .setMaster("local[*]")
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
    .cache()

  import data.Validator._

  val prices = trades
    .map(t =>
      Price(t.Timestamp, t.Open, t.High, t.Low, t.Close)
    )

  val ds = prices
    .map(rec => rec.copy(Error_Code = getErrorCodeA(rec)))

  val guid = Random.alphanumeric.take(10).toList.mkString("")
  ds.
    write
    .json(s"hdfs://zeta.avel.local:9000/data/usd-temp/$guid-USD.csv")

  println( ds.rdd.toDebugString)

  val filtered = ds
    .filter(_.Error_Code.isDefined)
    .map(p => ErrorHandling(p.Timestamp, p.Error_Code.get))

  println( filtered.rdd.toDebugString)

  filtered
    .write
    .json(s"hdfs://zeta.avel.local:9000/data/$guid-error-USD.csv")

  readLine()


  spark.close()

}