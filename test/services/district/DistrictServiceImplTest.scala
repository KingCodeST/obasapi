//package services.district
//
//import java.time.LocalDateTime
//
//import domain.demographics.District
//import org.scalatest.FunSuite
//import services.demographics.DistrictService
//
//import scala.concurrent.Await
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//class DistrictServiceImplTest extends FunSuite {
//
//  val entity = District("167","Nelson Mandela Bay Metopolitan Municipality ")
//  val service = DistrictService
//  test("createEntity"){
//    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(service.roach.getEntity(entity.districtCode), 2 minutes)
//    assert(result.head.districtCode==entity.districtCode)
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
//
//  }
//
//
//  test("deleteEntities"){
//    Await.result(service.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(service.roach.getEntity(entity.districtCode), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//}
