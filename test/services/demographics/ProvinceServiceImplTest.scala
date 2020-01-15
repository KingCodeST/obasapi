//package services.demographics
//
//import domain.demographics.Province
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//
//class ProvinceServiceImplTest extends FunSuite {
//
//  val entity = Province("P200","Western Cape")
//  val roachService = ProvinceService
//  test("createEntity"){
//    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(roachService.roach.getEntity(entity.provinceCode), 2 minutes)
//    assert(result.head.provinceCode==entity.provinceCode)
//  }
//
//  test("getEntities") {
//    val result = Await.result(roachService.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntity") {
//    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//
//  test("deleteEntities"){
//    Await.result(roachService.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(roachService.roach.getEntity(entity.provinceCode), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//}
