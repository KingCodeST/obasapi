package repository.users.impl.cockroachdb

import domain.users.UserTertiaryCourse
import org.scalatest.FunSuite
import repository.users.UserTertiaryCourseRepository

import scala.concurrent.Await
import scala.concurrent.duration._

class UserTertiaryCourseRepositoryImplTest extends FunSuite {


  val entity = UserTertiaryCourse("1", "13", "13 asd Me Way")
  val entity2 = UserTertiaryCourse("1", "154", "13 asd Me Way")
  val repository = UserTertiaryCourseRepository

  test("createEntity") {
    println(entity)
    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.apply.getEntity(entity.userId, entity.applicationId), 2 minutes)
    println(result.head)
    assert(result.head.userId == entity.userId)
  }

  test("readEntitiesForUser") {
    val result = Await.result(repository.apply.getEntitiesForUser(entity.userId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("getEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(applicationId = "14 Loop Street")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userId, entity.applicationId), 2 minutes)
    println(result)
    assert(result.head.applicationId == updatedEntity.applicationId)
  }

  test("deleteEntity") {
    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity2.userId, entity2.applicationId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }
}
