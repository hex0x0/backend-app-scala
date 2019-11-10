package util

import com.typesafe.config.{Config, ConfigFactory}
import javax.inject.Inject

class ConfigLoader @Inject()(config: Config) {
  def getDbUrl: String = {
    config.getString("slick.url")
  }
}

object ConfigLoader {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    val app = config.getConfig("slick")
    println(app)
    val url = config.getString("slick.url")
    println(url)
  }
}
