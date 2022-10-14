package data_source.position.command

import akka.actor.typed.ActorRef
import domain.Position
import domain.AlivePosition
import domain.EmptyPosition

trait PositionCommand

object PositionCommand:
  def of(position: Position) =
    position match
      case position @ AlivePosition(_, _) => AlivePositionCommand.of(position)
      case position @ EmptyPosition(_, _) => EmptyPositionCommand.of(position)
