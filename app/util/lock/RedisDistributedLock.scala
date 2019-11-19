package util.lock

import java.util.UUID

import akka.actor.ActorSystem
import javax.inject.{Inject, Singleton}
import play.api.Configuration
import redis.{RedisClientPool, RedisServer}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class RedisDistributedLock @Inject()(config: Configuration) extends DistributedLock {
  private val REDIS_KEY_PREFIX = "redis_key_prefix"
  private val EXPIRE_TIME_MILLS = 60 * 1000L
  implicit val actorSystem: ActorSystem = akka.actor.ActorSystem()
  private val redisClient = RedisClientPool(Seq(RedisServer(config.get[String]("redis.host"), config.get[Int]("redis.port"))))

  override def acquire(key: String, expireTimeMills: Long = EXPIRE_TIME_MILLS): Future[Option[Lock]] = {
    val lock = Lock(enrichKey(key), UUID.randomUUID().toString)
    redisClient.set(lock.key, lock.uuid, pxMilliseconds = Some(expireTimeMills), NX = true).map {
      case true =>
        Some(lock)
      case false =>
        None
    }
  }

  override def release(key: String): Future[Boolean] = {
    ???}

  private def enrichKey(key: String): String = {
    REDIS_KEY_PREFIX + key
  }
}
