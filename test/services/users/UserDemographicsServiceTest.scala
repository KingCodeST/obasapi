package services.users

import domain.users.UserDemographics
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserDemographicsServiceTest extends FunSuite{
  val entity = UserDemographics("1","genderTest", "raceTest","Mr")
  val roachService = UserDemographicsService
  test("createEntity"){
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(roachService.roach.getEntity(entity.userId), 2 minutes)
    assert(result.head.userId==entity.userId)
  }

  test("getEntities") {
    val result = Await.result(roachService.roach.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.roach.getEntity(entity.userId), 2 minutes)
    assert(result.isEmpty)
  }

}