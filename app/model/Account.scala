package model

import form.AccountForm

case class Account(
                 id: Option[Int],
                 name: String,
                 email: String,
                 password: String) {

  def getId: Int = id.get

  def buildAccount(form: AccountForm): Account = Account(
    id = None,
    name = form.name,
    email = form.email,
    password = form.password
  )
}
