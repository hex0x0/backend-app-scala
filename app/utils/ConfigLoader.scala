package utils

import com.typesafe.config.ConfigFactory

object ConfigLoader {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    val app = config.getConfig("app")
    println(app)
    val url = config.getString("app.url")
    println(url)
  }
}
