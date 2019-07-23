package dto

import play.api.libs.json.{Json, Writes}

case class UserDto(id: Int, name: String, age: Int, city: String)

object UserDto {
  implicit val writes: Writes[UserDto] =
    (u: UserDto) => Json.obj(
      "id" -> u.id,
      "name" -> u.name,
      "age" -> u.age,
      "city" -> u.city
    )
}
