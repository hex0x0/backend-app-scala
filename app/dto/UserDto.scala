package dto

import play.api.libs.json.{Json, Writes}

case class UserDto(name: String, age: Int, city: String)

object UserDto {
  implicit val writes: Writes[UserDto] =
    (u: UserDto) => Json.obj(
      "name" -> u.name,
      "age" -> u.age,
      "city" -> u.city
    )
}
