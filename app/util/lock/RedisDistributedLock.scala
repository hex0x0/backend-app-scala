package util.lock

import java.util.UUID

import akka.actor.ActorSystem
import javax.inject.{Inject, Singleton}
import play.api.Configuration
import redis.protocol.Integer
import redis.{RedisClientPool, RedisServer}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class RedisDistributedLock @Inject()(config: Configuration) extends DistributedLock {
  private val releaseScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end"
  private val redisKeyPrefix = "redis_key_prefix"
  private val expireTimeMills = 60 * 1000L
  implicit val actorSystem: ActorSystem = akka.actor.ActorSystem()
  private val redisClient = RedisClientPool(Seq(RedisServer(config.get[String]("redis.host"), config.get[Int]("redis.port"))))

  override def acquire(key: String, expireTime: Long = expireTimeMills): Future[Option[Lock]] = {
    val lock = Lock(enrichKey(key), UUID.randomUUID().toString)
    redisClient.set(lock.key, lock.uuid, pxMilliseconds = Some(expireTime), NX = true).map {
      case true =>
        Some(lock)
      case false =>
        None
    }
  }

  override def release(lock: Lock): Future[Boolean] = {
    redisClient.eval(releaseScript, Seq(lock.key), Seq(lock.uuid)).map {
      case s: Integer if s.toBoolean => true
      case _ => false
    }
  }

  private def enrichKey(key: String): String = {
    redisKeyPrefix + key
  }
}
