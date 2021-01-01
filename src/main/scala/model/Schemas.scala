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


final case class Price( Timestamp: String,
                        Open: Option[String],
                        High: Option[String],
                        Low: Option[String],
                        Close: Option[String],
                        Error_Code: Option[String] = None)

final case class ErrorHandling(Timestamp: String, Error_Code: String)