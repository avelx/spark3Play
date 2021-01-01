package data

import model.Price

object Validator {

  def getErrorCodeA(record: Price) : Option[String] = {
    if (!record.Close.isDefined)
      Some("FailedOnClose")
    else None
  }

  def getErrorCodeB(record: Price) : Option[String] = {
    if (!record.High.isDefined)
      Some("FailedHigh")
    else
      None
  }



}
