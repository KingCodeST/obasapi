package repository.users.impl.cockroachdb

import domain.users.UserMatricSubject
import org.scalatest.FunSuite
import repository.users.UserMatricSubjectRepository

import scala.concurrent.Await
import scala.concurrent.duration._

class UserMatricSubjectRepositoryImplTest extends FunSuite {

  val entity = UserMatricSubject("1", "13",12.00)
  val entity2 = UserMatricSubject("1", "154",12.00)
  val repository = UserMatricSubjectRepository

  test("createEntity") {
    println(entity)
    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity") {
    val result = Await.result(repository.apply.getEntity(entity.userId, entity.subjectId), 2 minutes)
    println(result.head)
    assert(result.head.userId == entity.userId)
  }

  test("readEntityForUser") {
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
    val result = Await.result(repository.apply.getEntity(entity.userId, entity.subjectId), 2 minutes)
    println(result)
    assert(result.head.subjectId == updatedEntity.subjectId)
  }

  test("deleteEntity") {
    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity2.userId, entity2.subjectId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }
}
