package services.application.Impl.cockroachdb

import domain.application.ApplicationStatus
import domain.util.generic.GenericStatus
import repository.application.ApplicationStatusRepository
import services.application.ApplicationStatusService
import services.util.generic.GenericStatusService
import util.APPKeys

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationStatusServiceImpl extends ApplicationStatusService {

  override def getEntitiesForApplication(applicationId: String): Future[Seq[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getEntitiesForApplication(applicationId)

  override def saveEntity(entity: ApplicationStatus): Future[Option[ApplicationStatus]] =
    ApplicationStatusRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getEntities

  override def getEntity(id: String): Future[Option[ApplicationStatus]] = ???

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ApplicationStatusRepository.roach.createTable

  override def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getEntitiesForAppnStatus(applicationId, statusId)

  override def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getLatestForAppnStatus(applicationId, statusId)

  override def getLatestForApplication(applicationId: String): Future[Option[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getLatestForApplication(applicationId)

  def getCompleteStatus(statuses: Seq[GenericStatus]): Future[Seq[GenericStatus]] = {
    Future.successful(
      statuses.filter(status => status.name.trim.equalsIgnoreCase(APPKeys.REJECTED) || status.name.trim.equalsIgnoreCase(APPKeys.APPROVED) || status.name.trim.equalsIgnoreCase(APPKeys.COMPLETE))
    )
  }

  def checkApplicationStatus(applicationStatus: Option[ApplicationStatus], completeStatuses: Seq[GenericStatus]): Future[Boolean] = {
    applicationStatus match {
      case Some(value) => Future.successful(completeStatuses.filter(s => s.id == value.statusId).length > 0)
      case None => Future.successful(true)
    }
  }

  override def checkIfCompleted(applicationId: String): Future[Boolean] = {
    for {
      applicationStatus <- getLatestForApplication(applicationId) if applicationStatus.isDefined
      statuses <- GenericStatusService.roach.getEntities
      completeStatuses <- getCompleteStatus(statuses)
      incomplete <- checkApplicationStatus(applicationStatus, completeStatuses)
    } yield {
      incomplete
    }
  }
}
