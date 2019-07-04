package repositories.users

import domain.users.UserCommunication
import org.scalatest.FunSuite
import repository.users.UserCommunicationRepository

import scala.concurrent.Await
import scala.concurrent.duration._


class UserCommunicationRepositoryTest extends FunSuite{
  val entity = UserCommunication("1","test")
  val repository = UserCommunicationRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.communicationId), 2 minutes)
    assert(result.head.communicationId==entity.communicationId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(description = "testing")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.communicationId), 2 minutes)
    assert(result.head.description==updatedEntity.description)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.communicationId), 2 minutes)
    assert(result.isEmpty)

  }
}