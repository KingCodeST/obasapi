package repository.demographics

import domain.demographics.Gender
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class GenderRepositoryTest extends FunSuite {

  val entity = Gender("1","Male")
  val repository = GenderRepository
  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.genderId), 2 minutes)
    assert(result.head.genderId==entity.genderId)
  }

  test("createEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntities"){
    val updatedEntity=entity.copy(genderName = "Female")
    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.genderId), 2 minutes)
    assert(result.head.genderName==updatedEntity.genderName)
  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.genderId), 2 minutes)
    assert(result.isEmpty)
  }
  
}