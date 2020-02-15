package dao

import javax.inject.Inject
import model.Account
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}
import slick.relational.RelationalProfile

import scala.concurrent.Future

class AccountTable(tag: Tag) extends Table[Account](tag, "accounts") {
  def id: Rep[Option[Int]] = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

  def name: Rep[String] = column[String]("name")

  def email: Rep[String] = column[String]("email")

  def password: Rep[String] = column[String]("password")

  def * : ProvenShape[Account] = (id, name, email, password) <> (Account.tupled, Account.unapply)
}

class AccountDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[RelationalProfile] {
  val accounts = TableQuery[AccountTable]

  def findById(id: Int): Future[Option[Account]] = {
    db.run(accounts.filter(_.id === id).result.headOption)
  }

  def all: Future[Seq[Account]] = {
    db.run(accounts.result)
  }

  def create(user: Account): Future[Account] = {
    db.run(accounts returning accounts += user)
  }

  def update(id: Int, a: Account): Future[Int] = {
    db.run(
      accounts.filter(_.id === id)
        .map(a => (a.name, a.email, a.password))
        .update((a.name, a.email, a.password))
    )
  }

  def delete(id: Int): Future[Int] = {
    db.run(accounts.filter(_.id === id).delete)
  }
}
