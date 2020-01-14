package services.application.Impl.cockroachdb

import domain.application.ApplicationType
import org.scalatest.FunSuite
import services.application.ApplicationTypeService

import scala.concurrent.Await
import scala.concurrent.duration._

class ApplicationTypeServiceImplTest extends FunSuite {

  val entity = ApplicationType("2", "81258",Some("to delete"))
  val service = ApplicationTypeService

  test("createEntity"){
    val result = Await.result(service.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(service.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }


  test("getEntities") {
    val result = Await.result(service.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(service.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(service.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(service.apply.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)

  }

}
