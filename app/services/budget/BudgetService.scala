package services.budget

import domain.budget.{Amount, Awards, Budget}
import zio.IO
import zio.stm.TRef
import zio.stm.STM._

import scala.concurrent.Future

trait BudgetService {

  def awardBusary(awards: Awards):Future[Boolean]
  def addBudget(budget:Budget):Future[Boolean]
  def totalAwards: Future[List[Awards]]
  def totalSumAwards:Future[BigDecimal]
  def totalBudget:Future[BigDecimal]
  def awardBursar(from: TRef[Budget], to: TRef[Awards], value:Amount): IO[Nothing, (Budget, Awards)] =
    atomically {
      for{
        balance <- from.get
        _ <- check(balance.amount >=value.amount)
        updatedBalance <-from.update(_.copy(amount = balance.amount-value.amount))
        award <-to.update(_.copy(amount = value.amount))
      } yield(updatedBalance,award)
    }
}

