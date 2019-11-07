package service

import dao.UserDao
import dto.UserDto
import form.UserForm
import javax.inject.Inject
import util.ConfigLoader

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserService @Inject()(userDao: UserDao,
                            configLoader: ConfigLoader) {
  def findAllUsers: Future[Seq[UserDto]] = {
    userDao.findAll.map(users => users.map(u => UserDto(u)))
  }

  def findUser(id: Int): Future[Either[String, UserDto]] = {
    userDao.findById(id).map {
      case Some(u) =>
        println(configLoader.getDbUrl)
        Right(UserDto(u))
      case _ => Left("Not found!")
    }
  }

  def createUser(form: UserForm): Future[UserDto] = {
    val user = form.toUser
    userDao.create(user).map(u => UserDto(u))
  }

  def updateUser(id: Int, form: UserForm): Future[Int] = {
    val user = form.toUser
    userDao.update(id, user)
  }

  def delete(id: Int): Future[Int] = {
    userDao.delete(id)
  }

  def foo(id: Int) = {
    val u1 = User(Some(1), "name1", None, None, "pswd")
    val u2 = u1.copy(name = "name2")

    for {
      a <- userDao.findById(id)
      _ = println(a)
      _ <- userDao.update(id, u1)
    } yield userDao.update(id, u2)
    //userDao.findById(id).flatMap {r =>
    //  println(r)
    //  userDao.update(id, u1).flatMap {r =>
    //    userDao.update(id, u2)
    //  }
    //}
  }
}
