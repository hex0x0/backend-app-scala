package dao

import model.User
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

import scala.concurrent.Future

class UserTable(tag: Tag) extends Table[User](tag, "user") {
  def id: Rep[Option[Int]] = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

  def name: Rep[String] = column[String]("name")

  def age: Rep[Option[Int]] = column[Option[Int]]("age")

  def city: Rep[Option[String]] = column[Option[String]]("city")

  def password: Rep[String] = column[String]("password")

  def * : ProvenShape[User] = (id, name, age, city, password) <> (User.tupled, User.unapply)
}

class UserDao {
  val users = TableQuery[UserTable]
  val db = Database.forConfig("slick")

  def findById(id: Int): Future[Option[User]] = {
    db.run(users.filter(_.id === id).result.headOption)
  }

  def findAll: Future[Seq[User]] = {
    db.run(users.result)
  }

  def create(user: User): Future[User] = {
    db.run(users returning users += user)
  }

  def update(id: Int, user: User): Future[Int] = {
    db.run(
      users.filter(_.id === id)
        .map(u => (u.name, u.age, u.city, u.password))
        .update((user.name, user.age, user.city, user.password))
    )
  }

  def delete(id: Int): Future[Int] = {
    db.run(users.filter(_.id === id).delete)
  }
}
