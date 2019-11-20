package util.lock

import scala.concurrent.Future

/**
 * Distributed lock.
 */
trait DistributedLock {
  def acquire(key: String, expireTime: Long): Future[Option[Lock]]

  def release(lock: Lock): Future[Boolean]
}
