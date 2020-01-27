package services.util.generic

import domain.util.generic.GenericStatus
import services.CrudService
import services.util.generic.impl.cockroach.GenericStatusServiceImpl

import scala.concurrent.Future

trait GenericStatusService extends CrudService[GenericStatus]{
  def createInitialData: Future[Seq[Boolean]]
  def getIncompleteStatus: Future[Option[GenericStatus]]
  def getCompleteStatus: Future[Option[GenericStatus]]
}

object GenericStatusService{
  def roach: GenericStatusService = new GenericStatusServiceImpl()
}
