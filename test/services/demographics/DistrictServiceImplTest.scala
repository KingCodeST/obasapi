//package services.demographics
//
//import domain.demographics.District
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//
//
//class DistrictServiceImplTest extends FunSuite {
//
//  val entity = District("WS","Western Cape")
//  val roachService = DistrictService
//  test("createEntity"){
//    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(roachService.roach.getEntity(entity.districtCode), 2 minutes)
//    assert(result.head.districtCode==entity.districtCode)
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
//    val result = Await.result(roachService.roach.getEntity(entity.districtCode), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//
//}
