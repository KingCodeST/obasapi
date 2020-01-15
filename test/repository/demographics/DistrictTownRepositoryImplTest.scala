//package repository.demographics
//
//import domain.demographics.DistrictTown
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//
//
//class DistrictTownRepositoryImplTest extends FunSuite {
//
//  val entity = DistrictTown("DS01", "TW01")
//  val entity2 = DistrictTown("DS01", "TW02")
//  val repository = DistrictTownRepository
//
//  test("createEntity") {
//    println(entity)
//    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("readEntity") {
//    val result = Await.result(repository.apply.getEntity(entity.districtCode, entity.townCode), 2 minutes)
//    println(result.head)
//    assert(result.head.districtCode == entity.districtCode)
//  }
//
//  test("readEntityForDistrict") {
//    val result = Await.result(repository.apply.getEntitiesForDistrict(entity.districtCode), 2 minutes)
//    println(result)
//    assert(result.head.districtCode == entity.districtCode)
//  }
//
//  test("getEntities"){
//    val result = Await.result(repository.apply.getEntities, 2 minutes)
//    println(result)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntity") {
//    val updatedEntity = entity.copy(districtCode = "DS023")
//    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
//    val result = Await.result(repository.apply.getEntity(entity.districtCode, entity.townCode), 2 minutes)
//    println(result)
//    assert(result.head.districtCode == updatedEntity.districtCode)
//  }
//
//  test("deleteEntity") {
//    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
//    val result = Await.result(repository.apply.getEntity(entity2.districtCode, entity2.townCode), 2 minutes)
//    println(result)
//    assert(result.isEmpty)
//  }
//
//}
