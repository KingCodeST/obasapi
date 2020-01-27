package services.application.Impl.cockroachdb

import domain.application.ApplicantType
import repository.application.ApplicantTypeRepository
import services.application.ApplicantTypeService
import util.{APPKeys, GenericLookupData}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicantTypeServiceImpl extends ApplicantTypeService{

  override def saveEntity(entity: ApplicantType): Future[Option[ApplicantType]] =
    ApplicantTypeRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicantType]] = {
    ApplicantTypeRepository.roach.getEntities
  }

  override def getEntity(applicantTypeId: String): Future[Option[ApplicantType]] = {
    ApplicantTypeRepository.roach.getEntity(applicantTypeId)
  }

  override def deleteEntity(entity: ApplicantType): Future[Boolean] = {
    ApplicantTypeRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicantTypeRepository.roach.createTable
  }

  def checkIfSaved(result: Option[ApplicantType], applicantType: String): Future[Boolean] = {
    if (result.isDefined) Future.successful(result.isDefined)
    else {
      val a = ApplicantType.build(applicantType)
      saveEntity(a).map(s => s.isDefined)
    }
  }

  override def createInitialData: Future[Seq[Boolean]] = Future.sequence(GenericLookupData.GENERIC_APPLICANT.map(applicantType => {
    for {
      result <- ApplicantTypeRepository.roach.getEntityByName(applicantType)
      isSaved <- checkIfSaved(result, applicantType)
    } yield isSaved
  }))

  override def getMatricApplicantType: Future[Option[ApplicantType]] = ApplicantTypeRepository.roach.getEntityByName(APPKeys.MATRIC_APPLICANT_TYPE)
}

