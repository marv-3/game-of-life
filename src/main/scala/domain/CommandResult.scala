package domain

trait CommandResult:
  def to(): Position
