package services.users.Impl

import domain.users.UserApplicationInstitution
import org.scalatest.FunSuite
import services.users.UserApplicationInstitutionService

import scala.concurrent.Await
import scala.concurrent.duration._


class UserApplicationInstitutionServiceImplTest extends FunSuite {

  val entity =UserApplicationInstitution("1", "genderTest", "raceTest")
  val roachService = UserApplicationInstitutionService
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