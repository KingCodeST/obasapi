package repository.users.impl.cockroachdb

import domain.users.UserApplication
import java.time.LocalDateTime
import org.scalatest.FunSuite
import repository.users.UserApplicationRepository

import scala.concurrent.Await
import scala.concurrent.duration._

class UserApplicationRepositoryImplTest extends FunSuite {

  val entity = UserApplication("1", "Test To be Deleted", LocalDateTime.now())
  val entity2 = UserApplication("1", "to be deleted",LocalDateTime.now())
  val repository = UserApplicationRepository

  test("createEntity") {
    println(entity)
    val result = Await.result(repository.roach.saveEntity(entity2), 2 minutes)
    assert(result.nonEmpty)
  }
// giving me problems

  test("readEntity") {
    val result = Await.result(repository.roach.getEntity(entity.userId, entity.applicationId), 2 minutes)
    println(result.head)
    assert(result.head.userId == entity.userId)
  }

  test("readEntityForUser") {
    val result = Await.result(repository.roach.getEntitiesForUser(entity.userId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("readEntityForApplication") {
    val result = Await.result(repository.roach.getEntityForApplication(entity.applicationId), 2 minutes)
    println(result)
    assert(result.head.applicationId == entity.applicationId)
  }

  test("readLatestEntityForUser") {
    val result = Await.result(repository.roach.getLatestEntityForUser(entity.userId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  // up until here


  test("getEntities"){
    val result = Await.result(repository.roach.getEntities, 2 minutes)
    println(result)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(applicationId = "updated")
    Await.result(repository.roach.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity.userId, entity.applicationId), 2 minutes)
    println(result)
    assert(result.head.applicationId == updatedEntity.applicationId)
  }

  test("deleteEntity") {
    Await.result(repository.roach.deleteEntity(entity2), 2 minutes)
    val result = Await.result(repository.roach.getEntity(entity2.userId, entity2.applicationId), 2 minutes)
    println(result)
    assert(result.isEmpty)
  }
}