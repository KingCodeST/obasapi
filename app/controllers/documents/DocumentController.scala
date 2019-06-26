package controllers.documents


import controllers.ApiResponse
import domain.documents.Document
import javax.inject.Inject
import io.circe.generic.auto._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}
import services.address.AddressTypeService
import services.application.ApplicantTypeService
import services.application.Impl.ApplicantTypeServiceImpl
import services.demographics.Impl.RaceServiceImpl
import services.demographics.{RaceService, RoleService}
import services.documents.DocumentService
import services.documents.Impl.DocumentServiceImpl

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future



class DocumentController @Inject()
(cc: ControllerComponents, api: ApiResponse) extends AbstractController(cc) {
  type DomainObject = Document

  def className: String = "DocumentController"

  def domainService: DocumentServiceImpl = DocumentService.apply

  def create: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            results: Boolean <- domainService.saveEntity(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }


  def getDocumentById(email: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Option[DomainObject]] = for {
        results <- domainService.getEntity(email)
      } yield results
      api.requestResponse[Option[DomainObject]](response, className)
  }

  def getAllDocument: Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[Seq[DomainObject]] = for {
        results <- domainService.getEntities
      } yield results
      api.requestResponse[Seq[DomainObject]](response, className)
  }

  def deleteDocument: Action[JsValue] = Action.async(parse.json) {
    implicit request: Request[JsValue] =>
      val entity = Json.fromJson[DomainObject](request.body).asEither
      entity match {
        case Right(value) =>
          val response: Future[Boolean] = for {
            results: Boolean <- domainService.deleteEntity(value)
          } yield results
          api.requestResponse[Boolean](response, className)
        case Left(error) => api.errorResponse(error, className)
      }
  }
}