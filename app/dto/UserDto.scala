package dto

import model.User
import play.api.libs.json.{Json, Writes}

case class UserDto(user: User) {
  def toUserDto(user: User): UserDto = {
    UserDto(user)
  }
}

object UserDto {
  implicit val writes: Writes[UserDto] = (u: UserDto) => {
    val user = u.user
    var base = Json.obj(
      "name" -> user.name,
      "city" -> user.city
    )
    base ++= user.age.fold(Json.obj())(age => Json.obj("age" -> age))
    base ++= user.city.fold(Json.obj())(city => Json.obj("city" -> city))
    base
  }
}
