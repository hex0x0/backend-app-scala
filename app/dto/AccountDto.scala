package dto

import model.Account
import play.api.libs.json.{Json, Writes}

case class AccountDto(a: Account) {
  def toUserDto(user: Account): AccountDto = {
    AccountDto(user)
  }
}

object AccountDto {
  implicit val writes: Writes[AccountDto] = (dto: AccountDto) => {
    Json.obj(
      "name" -> dto.a.name,
      "email" -> dto.a.email,
      "password" -> dto.a.password
    )
  }
}
