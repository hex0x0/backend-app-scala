package controllers

import form.UserForm
import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserController @Inject()(cc: ControllerComponents, userService: UserService) extends AbstractController(cc) {
  def findAllUsers: Action[AnyContent] = Action.async {
    userService.findAllUsers.map(u => Ok(Json.toJson(u)))
  }

  def findUserById(id: Int): Action[AnyContent] = Action.async {
    userService.findUser(id).map {
      case Left(error) => BadRequest(Json.obj("msg" -> error))
      case Right(dto) => Ok(Json.toJson(dto))
    }
  }

  def create: Action[JsValue] = Action(parse.json).async { request =>
    request.body.validate[UserForm].fold(
      error => Future(BadRequest(Json.obj("msg" -> error.toString))),
      form => userService.createUser(form).map(dto => Ok(Json.toJson(dto)))
    )
  }

  def update(id: Int): Action[JsValue] = Action(parse.json).async { request =>
    request.body.validate[UserForm].fold(
      error => Future(BadRequest(Json.obj("msg" -> error.toString))),
      form => userService.updateUser(id, form).map(result => Ok(Json.toJson(result)))
    )
  }
}
