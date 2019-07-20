package controllers

import javax.inject.Inject
import play._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}

class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def hello: api.mvc.Action[AnyContent] = Action {
    Ok("Hello, world!")
  }
}
