package repository.address.Impl.cockcroachdb.tables

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import domain.address.ContactType
import repository.address.Impl.cassandra.ContactTypeDatabase.ContactTypeTable

class ContactTypeTable(tag: Tag) extends Table[ContactType](tag, _tableName = "CONTACT_TYPE") {

  def ContactType: Rep[String] = column[String]("MAIL_ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("MAIL_KEY")

  def * : ProvenShape[ContactType] = (ContactType, name) <> ((ContactType.apply _).tupled, ContactType.unapply)
}

object ContactTypeTable extends TableQuery(new ContactTypeTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(ContactType: String): Future[Option[ContactType]] = {
    db.run(this.filter(_.ContactType === ContactType).result).map(_.headOption)
  }

  def saveEntity(mailApi: ContactType): Future[ContactType] = {
    db.run(this returning this.map(_.ContactType) into ((acc, ContactType) => acc.copy(ContactType = ContactType)) += mailApi)
  }

  def getEntities: Future[Seq[ContactType]] = {
    db.run(ContactTypeTable.result)
  }

  def deleteEntity(ContactType: String): Future[Int] = {
    db.run(this.filter(_.ContactType === ContactType).delete)
  }

  def createTable = {
    db.run(
      ContactTypeTable.schema.createIfNotExists
    ).isCompleted
  }

}