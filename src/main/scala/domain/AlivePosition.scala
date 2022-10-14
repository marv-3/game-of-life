package domain

case class AlivePosition(x1: Int, y1: Int) extends Position(x1, y1):
  override def isAlive: Boolean = true
  override def isEmpty: Boolean = false

