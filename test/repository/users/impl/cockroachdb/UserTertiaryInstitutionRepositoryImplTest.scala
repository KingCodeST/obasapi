package repository.users.impl.cockroachdb

import domain.users.UserTertiaryInstitution
import org.scalatest.FunSuite
import repository.users.UserTertiaryInstitutionRepository

import scala.concurrent.Await
import scala.concurrent.duration._

class UserTertiaryInstitutionRepositoryImplTest extends FunSuite {

  val entity = UserTertiaryInstitution("1", "13", "to be deleted",12.45)
  val entity2 = UserTertiaryInstitution("1", "13", "to be deleted",12.45)
  val repository = UserTertiaryInstitutionRepository

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
    val updatedEntity = entity.copy(institutionId = "updated")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.userId, entity.applicationId), 2 minutes)
    println(result)
    assert(result.head.institutionId == updatedEntity.institutionId)
  }

  test("deleteEntity") {
    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity2.userId, entity2.applicationId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }
}
