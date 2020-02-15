package dao

import model.Account
import org.scalatest.FlatSpec
import org.scalatestplus.mockito.MockitoSugar._

class AccountDaoSpec extends FlatSpec {
  val fakeUser = Account(Some(1), "fake_user", Some(23), Some("Ningbo"), "123")
  val mockDao: AccountDao = mock[AccountDao]
  "create a user" should "return the user" in {
    assert(1 == 1)
    assert(mockDao.findById(2).equals(fakeUser))
    //assert(mockDao.create(fakeUser).equals(fakeUser))
  }
}
