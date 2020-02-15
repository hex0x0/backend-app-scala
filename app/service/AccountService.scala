package service

import dao.AccountDao
import dto.AccountDto
import form.AccountForm
import javax.inject.Inject
import play.api.Logging
import util.ConfigParams

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AccountService @Inject()(accountDao: AccountDao, conf: ConfigParams) extends Logging {

  def all: Future[Seq[AccountDto]] = {
    accountDao.all.map(users => users.map(u => AccountDto(u)))
  }

  def findById(id: Int): Future[Either[String, AccountDto]] = {
    accountDao.findById(id).map {
      case Some(u) =>
        println(conf.getDbUrl)
        Right(AccountDto(u))
      case _ =>
        logger.warn(s"Account $id not found!")
        Left("Not found!")
    }
  }

  def create(form: AccountForm): Future[AccountDto] = {
    val user = form.toUser
    accountDao.create(user).map(u => AccountDto(u))
  }

  def update(id: Int, form: AccountForm): Future[Int] = {
    val user = form.toUser
    accountDao.update(id, user)
  }

  def delete(id: Int): Future[Int] = {
    accountDao.delete(id)
  }
}
