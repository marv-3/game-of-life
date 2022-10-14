package domain

case class EmptyPosition(x1: Int, y1: Int) extends Position(x1, y1):
  override def isAlive: Boolean = false
  override def isEmpty: Boolean = true
