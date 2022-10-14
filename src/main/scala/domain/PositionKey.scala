package domain

class PositionKey(val value: String) derives CanEqual:
  def toPoint: (Int, Int) =
    val a = value.split('-')
    (a.apply(0).toInt, a.apply(1).toInt)
