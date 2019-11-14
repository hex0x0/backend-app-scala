package util

import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

object ConfigLoader2 {
  val config: Config = ConfigFactory.load()

  def main(args: Array[String]): Unit = {
    //println(loadTemplateConfigs("yunpian", config))
    println(loadTemplateConfigs("yunpian", config)("register"))
    //println(config.getConfig("sms.yunpian").entrySet())
  }

  def loadTemplateConfigs(channel: String, config: Config): Map[String, ChannelConfig] = {
    val subConfig = config.getConfig(s"sms.$channel")
    subConfig.entrySet().asScala.groupBy(_.getKey.split('.').head).keys.map { key ⇒
      val templateConfig = subConfig.getConfig(s"$key.template")
      val templates = templateConfig.entrySet().asScala.map { entry ⇒
        val templateKey = entry.getKey
        templateKey -> templateConfig.getString(templateKey)
      }.toMap
      val parameters = getString(subConfig, s"$key.parameters")
      key -> ChannelConfig(key, templates, parameters)
    }.toMap
  }

  def getString(config: Config, path: String): Option[Seq[String]] = {
    Try(config.getStringList(path).asScala) match {
      case Success(value) => Some(value)
      case Failure(_) => None
    }
  }
}

final case class ChannelConfig(name: String, templates: Map[String, String], parameters: Option[Seq[String]])

/*
Map(
    register -> ChannelConfig(
        register,
        Map(
            zh -> 【GrowingIO】验证码 %s，有效时间5分钟。您正注册GrowingIO，如非本人操作，请忽略。,
            en -> 【GrowingIO】 %s is your verification code
            ),
        Some(Buffer(code))),
    temp_password -> ChannelConfig(temp_password,Map(zh -> 【GrowingIO】尊敬的 %s，您好！感谢注册试用 GrowingIO。请在 PC 端访问：http://www.growingio.com 密码是 %s。有任何问题，请拨打400-666-1910。立刻开启数据驱动之旅！),Some(Buffer(name, pass))),
    reset_password -> ChannelConfig(reset_password,Map(zh -> 【GrowingIO】验证码 %s，有效时间5分钟。您正在重置密码，切勿将验证码泄露于他人。, en -> 【GrowingIO】 %s is your verification code),Some(Buffer(code))),
    verification -> ChannelConfig(verification,Map(zh -> 【GrowingIO】验证码%s，您正在进行GrowingIO身份验证，打死不要告诉别人哦！),Some(Buffer(code, product)))
    )

ChannelConfig(
register,
Map(zh -> 【GrowingIO】验证码 %s，有效时间5分钟。您正注册GrowingIO，如非本人操作，请忽略。,
en -> 【GrowingIO】 %s is your verification code),Some(Buffer(code)))

[register.parameters=SimpleConfigList(["code"]),
verification.template.zh=Quoted("【GrowingIO】验证码%s，您正在进行GrowingIO身份验证，打死不要告诉别人哦！"),
reset_password.template.zh=Quoted("【GrowingIO】验证码 %s，有效时间5分钟。您正在重置密码，切勿将验证码泄露于他人。"),
register.template.zh=Quoted("【GrowingIO】验证码 %s，有效时间5分钟。您正注册GrowingIO，如非本人操作，请忽略。"),
register.template.en=Quoted("【GrowingIO】 %s is your verification code"),
temp_password.template.zh=Quoted("【GrowingIO】尊敬的 %s，您好！感谢注册试用 GrowingIO。请在 PC 端访问：http://www.growingio.com 密码是 %s。有任何问题，请拨打400-666-1910。立刻开启数据驱动之旅！"),
reset_password.template.en=Quoted("【GrowingIO】 %s is your verification code"),
temp_password.parameters=SimpleConfigList(["name","pass"]),
reset_password.parameters=SimpleConfigList(["code"]),
verification.parameters=SimpleConfigList(["code","product"])]
 */