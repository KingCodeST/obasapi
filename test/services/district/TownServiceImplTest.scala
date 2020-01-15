//package services.district
//
//import domain.demographics.Town
//import org.scalatest.FunSuite
//import services.demographics.TownService
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//class TownServiceImplTest extends FunSuite {
//
//  val entity = Town("124","Welkom")
//  val service = TownService
//  test("createEntity"){
//    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("readEntity"){
//    val result = Await.result(service.roach.getEntity(entity.townCode), 2 minutes)
//    assert(result.head.townCode==entity.townCode)
//  }
//
//  test("getEntities") {
//    val result = Await.result(service.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//  test("updateEntity") {
//    val result = Await.result(service.roach.saveEntity(entity), 2 minutes)
//    assert(result.isEmpty)
//  }
//  test("deleteEntities"){
//    Await.result(service.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(service.roach.getEntity(entity.townCode), 2 minutes)
//    assert(result.isEmpty)
//  }
//}
