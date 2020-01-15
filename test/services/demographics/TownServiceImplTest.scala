//package services.demographics
//
//import domain.demographics.Town
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//
//class TownServiceImplTest extends FunSuite {
//
//  val entity = Town("TW98","Cape Town")
//  val roachService = TownService
//  test("createEntity"){
//    val result = Await.result(roachService.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(roachService.roach.getEntity(entity.townCode), 2 minutes)
//    assert(result.head.townCode==entity.townCode)
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
//    val result = Await.result(roachService.roach.getEntity(entity.townCode), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//}
