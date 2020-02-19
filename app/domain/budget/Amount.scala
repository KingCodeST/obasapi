package domain.budget

import play.api.libs.json.Json

case class Amount(amount: BigDecimal)

object Amount {
  implicit val amountFmt = Json.format[Amount]
}
