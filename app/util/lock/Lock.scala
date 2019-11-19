package util.lock

final case class Lock(key: String, uuid: String)

object Lock {
  def empty(): Lock = {
    Lock("", "")
  }
}
