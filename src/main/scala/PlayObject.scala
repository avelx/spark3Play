trait DataAccess {
  def getData(id: String): Seq[String]
}

object PlayObject extends DataAccess {
  override def getData(id: String): Seq[String] = ???
}
