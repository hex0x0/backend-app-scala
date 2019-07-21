package controllers

import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.UserService

import scala.concurrent.ExecutionContext.Implicits.global

class UserController @Inject()(cc: ControllerComponents, userService: UserService) extends AbstractController(cc) {
  def findAllUsers: Action[AnyContent] = Action.async {
    userService.findAllUsers.map(u => Ok(Json.toJson(u)))
  }

  def findUserById(id: Int): Action[AnyContent] = Action.async {
    userService.findUser(id).map {
      case Left(error) => BadRequest(error)
      case Right(dto) => Ok(Json.toJson(dto))
    }
  }
}
