package form

import model.Account
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

case class AccountForm(
                        name: String,
                        email: String,
                        password: String) {
  def toUser: Account = {
    Account(
      None,
      name,
      email,
      password
    )
  }
}

object AccountForm {
  implicit val reads: Reads[AccountForm] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "password").read[String]
    ) (AccountForm.apply _)
}
