package repository.institutions

import domain.institutions.InstitutionType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


class InstitutionTypeRepositoryImplTest extends FunSuite {

  val entity = InstitutionType("ID548","CPUT",Option("University"))
  val repository = InstitutionTypeRepository

  test("createEntity"){
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result.nonEmpty)

  }

  test("readEntity"){
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.head.id==entity.id)
  }

  test("getEntities"){
    val result = Await.result(repository.apply.getEntities, 2 minutes)
    assert(result.nonEmpty)
  }

  test("updateEntity") {
    val result = Await.result(repository.apply.saveEntity(entity), 2 minutes)
    assert(result.isEmpty)

  }


  test("deleteEntities"){
    Await.result(repository.apply.deleteEntity(entity), 2 minutes)
    val result = Await.result(repository.apply.getEntity(entity.id), 2 minutes)
    assert(result.isEmpty)
  }

}
