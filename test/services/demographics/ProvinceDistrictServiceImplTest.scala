//package services.demographics
//
//import domain.demographics.ProvinceDistrict
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//
//class ProvinceDistrictServiceImplTest extends FunSuite {
//
//  val entity = ProvinceDistrict("P200", "WS")
//  val roachService = ProvinceDistrictService.apply
//  test("createEntity"){
//    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(roachService.getEntity(entity.provinceCode, entity.districtCode), 2 minutes)
//
//    assert(result.head.provinceCode==entity.provinceCode)
//  }
//
//  test("readEntityForUser") {
//    val result = Await.result(roachService.getEntity(entity.provinceCode), 2 minutes)
//    println(result)
//    assert(result.head.provinceCode == entity.provinceCode)
//  }
//
//  test("getEntities") {
//    val result = Await.result(roachService.getEntities, 2 minutes)
//
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntity") {
//    val updatedEntity = entity.copy(districtCode = "14 Loop Street")
//    val result = Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//
//  test("deleteEntities"){
//    Await.result(roachService.deleteEntity(entity), 2 minutes)
//    val result = Await.result(roachService.getEntity(entity.provinceCode), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//}
