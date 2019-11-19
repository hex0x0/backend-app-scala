package util.lock

import scala.concurrent.Future

/**
 * Distributed lock.
 */
trait DistributedLock {
  def acquire(key: String, expireTimeMills: Long): Future[Option[Lock]]

  def release(key: String): Future[Boolean]
}
