package domain

case class AliveCommandResult(x: Int, y: Int) extends CommandResult:
  override def to(): Position = AlivePosition(x, y)
