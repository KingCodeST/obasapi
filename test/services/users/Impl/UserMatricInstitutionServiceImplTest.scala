package services.users.Impl

import domain.users.UserMatricInstitution
import org.scalatest.FunSuite
import services.users.UserMatricInstitutionService

import scala.concurrent.Await
import scala.concurrent.duration._

class UserMatricInstitutionServiceImplTest extends FunSuite {


  val entity =UserMatricInstitution("1", "genderTest")
  val roachService = UserMatricInstitutionService

  test("createEntity") {
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity") {
    val result = Await.result(roachService.apply.getEntity(entity.userId), 2 minutes)
    assert(result.head.userId == entity.userId)
  }

  test("getEntities") {
    val result = Await.result(roachService.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(roachService.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)
  }

  test("deleteEntities") {
    Await.result(roachService.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.apply.getEntity(entity.userId), 2 minutes)
    assert(result.isEmpty)
  }
}