package model

final case class Trade(
                        Timestamp: String,
                        Open: Option[String],
                        High: Option[String],
                        Low: Option[String],
                        Close: Option[String],
                        `Volume_(BTC)`: Option[String],
                        `Volume_(Currency)`: Option[String],
                        Weighted_Price: Option[String])


final case class Price( timestamp: Int,
                        open: Option[String],
                        high: Option[String],
                        low: Option[String],
                        close: Option[String],
                        error_code: Option[String] = None)

final case class ErrorHandling(Timestamp: String, Error_Code: String)