package services.users.Impl

import domain.users.UserMatricSubject
import org.scalatest.FunSuite
import services.users.UserMatricSubjectService

import scala.concurrent.Await
import scala.concurrent.duration._

class UserMatricSubjectServiceImplTest extends FunSuite {


  val entity =UserMatricSubject("1", "genderTest", 68.5)
  val roachService = UserMatricSubjectService
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