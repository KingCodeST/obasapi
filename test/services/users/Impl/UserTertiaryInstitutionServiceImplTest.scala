package services.users.Impl

import domain.users.UserTertiaryInstitution
import org.scalatest.FunSuite
import services.users.UserTertiaryInstitutionService

import scala.concurrent.Await
import scala.concurrent.duration._


class UserTertiaryInstitutionServiceImplTest extends FunSuite {

  val entity =UserTertiaryInstitution("1", "genderTest","to delete",70.34)
  val roachService = UserTertiaryInstitutionService
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