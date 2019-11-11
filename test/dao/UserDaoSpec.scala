package dao

import model.User
import org.scalatest.FlatSpec
import org.scalatestplus.mockito.MockitoSugar._

class UserDaoSpec extends FlatSpec {
  val fakeUser = User(Some(1), "fake_user", Some(23), Some("Ningbo"), "123")
  val mockDao: UserDao = mock[UserDao]
  "create a user" should "return the user" in {
    assert(1 == 1)
    assert(mockDao.findById(2).equals(fakeUser))
    //assert(mockDao.create(fakeUser).equals(fakeUser))
  }
}
