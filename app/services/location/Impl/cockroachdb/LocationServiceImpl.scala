package services.location.Impl.cockroachdb

import domain.location.Location
import repository.location.LocationRepository
import services.location.LocationService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LocationServiceImpl extends LocationService {

  override def saveEntity(entity: Location): Future[Option[Location]] =
    LocationRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[Location]] =
    LocationRepository.roach.getEntities.map(_.sorted(Location.orderByName))

  override def getEntity(locationId: String): Future[Option[Location]] =
    LocationRepository.roach.getEntity(locationId)

  override def deleteEntity(entity: Location): Future[Boolean] =
    LocationRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    LocationRepository.roach.createTable

  override def getParentEntities: Future[Seq[Location]] = LocationRepository.roach.getParentEntities.map(_.sorted(Location.orderByName))

  override def getEntitiesForParent(locationParentId: String): Future[Seq[Location]] =
    LocationRepository.roach.getEntitiesForParent(locationParentId).map(_.sorted(Location.orderByName))
}
