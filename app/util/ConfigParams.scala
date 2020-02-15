package util

import com.typesafe.config.ConfigFactory
import javax.inject.Inject
import play.api.Configuration

class ConfigParams @Inject()(config: Configuration) {
  def getDbUrl: String = {
    config.get[String]("slick.url")
  }
}

object ConfigParams {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    val app = config.getConfig("slick")
    println(app)
    val url = config.getString("slick.url")
    println(url)
  }
}
