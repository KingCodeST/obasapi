package repository.util.generic

import domain.util.generic.GenericStatus
import repository.Repository
import repository.util.generic.impl.cockroach.GenericStatusRepositoryImpl

import scala.concurrent.Future

trait GenericStatusRepository extends Repository[GenericStatus]{
  def getEntityByName(name: String): Future[Option[GenericStatus]]
}
object GenericStatusRepository{
  def roach: GenericStatusRepository = new GenericStatusRepositoryImpl()
}