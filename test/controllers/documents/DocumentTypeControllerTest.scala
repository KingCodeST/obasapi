package controllers.documents

import domain.documents.DocumentType
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest,Injecting}

class DocumentTypeControllerTest extends PlaySpec with GuiceOneAppPerTest  with Injecting{

  val entity =DocumentType("1","Acceptance Letter 1")
  val token ="eyJraWQiOiJURVNUX1BIUkFTRSIsImFsZyI6IkVTMjU2In0.eyJpc3MiOiJIQVNIQ09ERS5aTSIsImF1ZCI6IlNJVEVVU0VSUyIsImV4cCI6MTU2NjQ3NDYzNiwianRpIjoiX0dxSG9Dc3dFU1J1R2pGaXBsRzhHZyIsImlhdCI6MTU2NjM4ODIzNiwibmJmIjoxNTY2Mzg4MTE2LCJzdWIiOiJTaXRlIEFjY2VzcyIsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJyb2xlIjoiU1RSMDAxIn0.JH-vlwm0PSRSoBE9D3ZrgMhf_Li3gARBLCf6NUZNdHifvbYo3_iQaaBf8baI2H5DgO87oN6Jrb1RGSxVXdIcDg"



  "EntityController" should {

    "Create Entity" in {

      val request = route(app, FakeRequest(POST, "/documents/type/create")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }


    "Read Entity " in {

      val request = route(app, FakeRequest(GET, "/documents/type/get/$documentTypeId" + entity.documentTypeId)
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Get Entities" in {
      val request = route(app, FakeRequest(GET, "/documents/type/all")
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }

    "Update Entity" in {
      val updatedEntity = entity.copy(documentTypename = "updated")
      val request = route(app, FakeRequest(POST, "/documents/type/update")
        .withJsonBody(Json.toJson(updatedEntity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))
    }

    "Delete Entities" in {
      val request = route(app, FakeRequest(POST, "/documents/type/delete")
        .withJsonBody(Json.toJson(entity))
        .withHeaders(AUTHORIZATION -> token)
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println("The Content is: ", contentAsString(request))

    }
  }
}
