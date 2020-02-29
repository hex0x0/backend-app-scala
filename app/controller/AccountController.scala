package controller

import form.AccountForm
import javax.inject.Inject
import org.apache.logging.log4j.LogManager
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import service.AccountService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AccountController @Inject()(cc: ControllerComponents, userService: AccountService) extends AbstractController(cc) {

  private val logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME)

  def all: Action[AnyContent] = Action.async {
    logger.info("Try to find all accounts.")
    userService.all.map(u => Ok(Json.toJson(u)).withHeaders(("Access-Control-Allow-Origin", "*")))
  }

  def findById(id: Int): Action[AnyContent] = Action.async {
    userService.findById(id).map {
      case Left(error) => BadRequest(Json.obj("message" -> error))
      case Right(dto) => Ok(Json.toJson(dto)).withHeaders(("Access-Control-Allow-Origin", "*"))
    }
  }

  def create: Action[JsValue] = Action(parse.json).async { request =>
    request.body.validate[AccountForm].fold(
      error => Future(BadRequest(Json.obj("message" -> error.toString))),
      form => userService.create(form).map(dto => Ok(Json.toJson(dto)).withHeaders(("Access-Control-Allow-Origin", "*")))
    )
  }

  def update(id: Int): Action[JsValue] = Action(parse.json).async { request =>
    request.body.validate[AccountForm].fold(
      error => Future(BadRequest(Json.obj("message" -> error.toString))),
      form => userService.update(id, form).map(result => Ok(Json.toJson(result)).withHeaders(("Access-Control-Allow-Origin", "*")))
    )
  }

}
