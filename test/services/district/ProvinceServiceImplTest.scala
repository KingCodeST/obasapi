//package services.district
//
//import domain.demographics.Province
//import org.scalatest.FunSuite
//import services.demographics.ProvinceService
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//class ProvinceServiceImplTest extends FunSuite {
//
//  val entity = Province("163","Johannesburg")
//  val service = ProvinceService
//  test("createEntity"){
//    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(service.roach.getEntity(entity.provinceCode), 2 minutes)
//    assert(result.head.provinceCode==entity.provinceCode)
//  }
//
//
//  test("getEntities") {
//    val result = Await.result(service.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntity") {
//    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
//    assert(result.isEmpty)
//  }
//  test("deleteEntities"){
//    Await.result(service.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(service.roach.getEntity(entity.provinceCode), 2 minutes)
//    assert(result.isEmpty)
//  }
//}
