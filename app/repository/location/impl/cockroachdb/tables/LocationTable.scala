package repository.location.impl.cockroachdb.tables

import domain.location.Location
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LocationTable(tag: Tag) extends Table[Location](tag, "location") {
  def locationId: Rep[String] = column[String]("location_id", O.PrimaryKey)

  def locationTypeId: Rep[String] = column[String]("location_type_id")

  def name: Rep[String] = column[String]("name")

  def latitude: Rep[String] = column[String]("latitude")

  def longitude: Rep[String] = column[String]("longitude")

  def locationParentId:Rep[String] = column[String]("location_parent_id")

  def * : ProvenShape[Location] = (locationId, locationTypeId, name, latitude, longitude, locationParentId) <> ((Location.apply _).tupled, Location.unapply)
}

object LocationTable extends TableQuery(new LocationTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(locationId: String): Future[Option[Location]] = {
    db.run(this.filter(_.locationId === locationId).result).map(_.headOption)
  }

  def saveEntity(location: Location): Future[Option[Location]] = {
    db.run(
      (this returning this).insertOrUpdate(location)
    )
  }

  def getParentEntities: Future[Seq[Location]] = {
    db.run(this.filter(_.locationParentId === "").result)
  }

  def getEntitiesForParent(locationParentId: String): Future[Seq[Location]] = {
    db.run(this.filter(_.locationParentId === locationParentId).result)
  }

  def getEntities: Future[Seq[Location]] = {
    db.run(LocationTable.result)
  }

  def deleteEntity(locationId: String): Future[Int] = {
    db.run(this.filter(_.locationId === locationId).delete)
  }

  def createTable: Boolean = {
    db.run(
      LocationTable.schema.createIfNotExists
    ).isCompleted
  }

}