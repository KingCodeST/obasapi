package services.users

import domain.users.UserAddress
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class UserAddressServiceTest extends FunSuite{

  val entity = UserAddress("1", "1", "13 asd Me Way","605")
  val roachService = UserAddressService.apply

  test("createEntity"){
    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)
  }

  test("readEntity"){
    val result = Await.result(roachService.getEntity(entity.userId, entity.addressTypeId), 2 minutes)
    assert(result.head.userId==entity.userId)
  }

  test("readEntityForUser") {
    val result = Await.result(roachService.getEntity(entity.userId), 2 minutes)
    println(result)
    assert(result.head.userId == entity.userId)
  }

  test("getEntities") {
    val result = Await.result(roachService.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val updatedEntity = entity.copy(address = "14 Loop Street")
    val result = Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
    assert(result.isEmpty)
  }

  test("deleteEntities"){
    Await.result(roachService.deleteEntity(entity), 2 minutes)
    val result = Await.result(roachService.getEntity(entity.userId), 2 minutes)
    assert(result.isEmpty)
  }

}
