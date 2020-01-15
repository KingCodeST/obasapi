//package services.demographics
//
//import domain.demographics.DistrictTown
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//
//class DistrictTownServiceImplTest extends FunSuite {
//
//  val entity = DistrictTown("WS", "TW")
//  val roachService = DistrictTownService.apply
//  test("createEntity"){
//    val result = Await.result(roachService.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(roachService.getEntity(entity.districtCode, entity.townCode), 2 minutes)
//
//    assert(result.head.districtCode==entity.districtCode)
//  }
//
//  test("readEntityForUser") {
//    val result = Await.result(roachService.getEntity(entity.districtCode), 2 minutes)
//    println(result)
//    assert(result.head.districtCode == entity.districtCode)
//  }
//
//  test("getEntities") {
//    val result = Await.result(roachService.getEntities, 2 minutes)
//
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntity") {
//    val updatedEntity = entity.copy(townCode = "Z323")
//    val result = Await.result(roachService.saveEntity(updatedEntity), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//
//  test("deleteEntities"){
//    Await.result(roachService.deleteEntity(entity), 2 minutes)
//    val result = Await.result(roachService.getEntity(entity.districtCode), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//}
