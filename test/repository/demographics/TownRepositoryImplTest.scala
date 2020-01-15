//package repository.demographics
//
//import domain.demographics.Town
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//
//class TownRepositoryImplTest extends FunSuite {
//
//  val entity = Town("TW04","Salt River")
//  val repository =TownRepository
//
//  test("createEntity"){
//    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.townCode), 2 minutes)
//    assert(result.head.townCode==entity.townCode)
//  }
//
//  test("getEntities"){
//    val result = Await.result(repository.roach.getEntities, 2 minutes)
//    assert(result.nonEmpty)
//  }
//
//  test("updateEntity") {
//    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
//    assert(result.isEmpty)
//
//  }
//
//
//  test("deleteEntities"){
//    Await.result(repository.roach.deleteEntity(entity), 2 minutes)
//    val result = Await.result(repository.roach.getEntity(entity.townCode), 2 minutes)
//    assert(result.isEmpty)
//  }
//
//}
