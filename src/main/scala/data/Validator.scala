package data

import model.Price

object Validator {

  def getErrorCodeA(record: Price) : Option[String] = {
    if (!record.close.isDefined)
      Some("FailedOnClose")
    else None
  }

  def getErrorCodeB(record: Price) : Option[String] = {
    if (!record.high.isDefined)
      Some("FailedHigh")
    else
      None
  }



}
