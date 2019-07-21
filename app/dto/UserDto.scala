package dto

import play.api.libs.json.{JsValue, Json, Writes}

case class UserDto(id: Int, name: String, age: Int, city: String)

object UserDto {
  implicit val writes: Writes[UserDto] = new Writes[UserDto] {
    override def writes(o: UserDto): JsValue = Json.obj(
      "id" -> o.id,
      "name" -> o.name,
      "age" -> o.age,
      "city" -> o.city
    )
  }
}
