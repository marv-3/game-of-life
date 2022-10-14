package data_source.position

import domain.{AlivePosition, EmptyPosition, Position}

trait PositionState(val x: Int, val y: Int):
  def toDomain: Position

class AlivePositionState(x: Int, y: Int) extends PositionState(x, y):
  override def toDomain: Position = AlivePosition(x, y)

class EmptyPositionState(x: Int, y: Int) extends PositionState(x, y):
  override def toDomain: Position = EmptyPosition(x, y)
