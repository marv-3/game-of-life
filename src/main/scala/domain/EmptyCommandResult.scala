package domain

case class EmptyCommandResult(x: Int, y: Int) extends CommandResult:
  override def to(): Position = EmptyPosition(x, y)
