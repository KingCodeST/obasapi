package repository.users.impl.cockroachdb

import domain.users.UserTertiarySubject
import org.scalatest.FunSuite
import repository.users.UserTertiarySubjectRepository

import scala.concurrent.Await
import scala.concurrent.duration._

class UserTertiarySubjectRepositoryImplTest extends FunSuite {

  val entity = UserTertiarySubject("1", "13", "to be deleted",12.45)
  val entity2 = UserTertiarySubject("1", "13", "to be deleted",12.45)
  val repository = UserTertiarySubjectRepository

  test("createEntity") {
    println(entity)
    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.apply.getEntity(entity.userId), 2 minutes)
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
    val updatedEntity = entity.copy(subjectId = "updated")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userId), 2 minutes)
    println(result)
    assert(result.head.subjectId == updatedEntity.subjectId)
  }

  test("deleteEntity") {
    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity2.userId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }
}
