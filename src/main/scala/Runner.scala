import model.SimpleTrade
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Runner extends App {

  import org.apache.log4j.Logger
  import org.apache.log4j.Level

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val conf = new SparkConf()
    .setMaster("spark://zeta.avel.local:7077")
    .set("spark.executor.memory", "4G")
    .setAppName("DataSetResearchApp")

  val spark = SparkSession
    .builder()
    .config(conf)
    .getOrCreate()

  import spark.implicits._

  val trades = spark
    .read
    .json("hdfs://192.168.0.8:9000/data/comrade.csv")

  trades.show(10)

  spark.close()
}