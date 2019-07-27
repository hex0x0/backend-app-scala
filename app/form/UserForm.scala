package form

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

case class UserForm(
                     name: String,
                     age: Option[Int],
                     city: Option[String],
                     password: String)

object UserForm {
  implicit val reads: Reads[UserForm] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "age").readNullable[Int] and
      (JsPath \ "city").readNullable[String] and
      (JsPath \ "password").read[String]
    ) (UserForm.apply _)
}
