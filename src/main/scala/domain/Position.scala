package domain

trait Position(val x: Int, val y: Int):
  def isAlive: Boolean
  def isEmpty: Boolean
  def samePosition(pos: Position): Boolean = pos.x == x && pos.y == y
  def samePosition(pos: (Int, Int)): Boolean = pos._1 == x && pos._2 == y
  def birth(): AlivePosition = AlivePosition(x, y)
  def empty(): EmptyPosition = EmptyPosition(x, y)
  def key: PositionKey = PositionKey(x.toString + "-" + y.toString)

  def neighbors(): List[(Int, Int)] =
    List((x - 1, y - 1), (x - 1, y), (x - 1, y + 1), (x, y - 1), (x, y + 1), (x + 1, y - 1), (x + 1, y), (x + 1, y + 1))
      .filter(t => t._1 >= 0 && t._1 < 50 && t._2 >= 0 && t._2 < 50)
