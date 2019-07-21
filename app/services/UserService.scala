package services

import dao.UserDao
import dto.UserDto
import form.UserForm
import javax.inject.Inject
import model.User

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserService @Inject()(userDao: UserDao) {
  def findAllUsers: Future[Seq[UserDto]] = {
    userDao.findAll.map(users => users.map(u => buildUserDto(u)))
  }

  def findUser(id: Int): Future[Either[String, UserDto]] = {
    userDao.findById(id).map {
      case Some(u) => Right(buildUserDto(u))
      case _ => Left("Not found!")
    }
  }

  def createUser(form: UserForm): Future[UserDto] = {
    val user = buildUser(form)
      userDao.create(user).map(u => buildUserDto(u))
  }

  def updateUser(id: Int, form: UserForm): Future[Int] = {
    val user = buildUser(form)
    userDao.update(id, user)
  }

  def delete(id: Int): Future[Int] = {
    userDao.delete(id)
  }

  private def buildUserDto(user: User) = {
    UserDto(
      user.id.get,
      user.name,
      user.age.get,
      user.city.get
    )
  }

  private def buildUser(form: UserForm) = {
    User(
      None,
      form.name,
      form.age,
      form.city
    )
  }
}
