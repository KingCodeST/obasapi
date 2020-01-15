//package repository.demographics
//
//import domain.demographics.ProvinceDistrict
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//class ProvinceDistrictRepositoryImplTest extends FunSuite {
//
//  val entity = ProvinceDistrict("PR01", "DS02")
//  val entity2 = ProvinceDistrict("PR02", "DS01")
//  val repository = ProvinceDistrictRepository
//
//  test("createEntity") {
//    println(entity)
//    val result = Await.result(repository.apply.saveEntity(entity2), 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("readEntity") {
//    val result = Await.result(repository.apply.getEntity(entity.provinceCode, entity.districtCode), 2 minutes)
//    println(result.head)
//    assert(result.head.provinceCode == entity.provinceCode)
//  }
//
//  test("readEntityForUser") {
//    val result = Await.result(repository.apply.getEntitiesForProvince(entity.provinceCode), 2 minutes)
//    println(result)
//    assert(result.head.provinceCode == entity.provinceCode)
//  }
//
//  test("getEntities"){
//    val result = Await.result(repository.apply.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntity") {
//    val updatedEntity = entity.copy(provinceCode = "PR01")
//    Await.result(repository.apply.saveEntity(updatedEntity), 2 minutes)
//    val result = Await.result(repository.apply.getEntity(entity.provinceCode, entity.districtCode), 2 minutes)
//    assert(result.head.provinceCode == updatedEntity.provinceCode)
//  }
//
//  test("deleteEntity") {
//    Await.result(repository.apply.deleteEntity(entity2), 2 minutes)
//    val result = Await.result(repository.apply.getEntity(entity2.provinceCode, entity2.districtCode), 2 minutes)
//    assert(result.isEmpty)
//  }
//
//}
