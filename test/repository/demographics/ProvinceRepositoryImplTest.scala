//package repository.demographics
//
//import domain.demographics.Province
//import org.scalatest.FunSuite
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//class ProvinceRepositoryImplTest extends FunSuite {
//
//  val entity = Province("PR23","Western Cape")
//  val repository = ProvinceRepository
//
//  test("createEntity"){
//    val result = Await.result(repository.roach.saveEntity(entity), 2 minutes)
//    assert(result.nonEmpty)
//
//  }
//
//  test("readEntity"){
//    val result = Await.result(repository.roach.getEntity(entity.provinceCode), 2 minutes)
//    assert(result.head.provinceCode==entity.provinceCode)
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
//    val result = Await.result(repository.roach.getEntity(entity.provinceCode), 2 minutes)
//    assert(result.isEmpty)
//  }
//
//}
