package data_source.position.command

import akka.actor.typed.ActorRef
import domain.{AliveCommandResult, AlivePosition, CommandResult, EmptyCommandResult, EmptyPosition, Position}

case class EmptyPositionCommand(val x: Int, val y: Int)(val replyTo: ActorRef[EmptyCommandResult])
    extends PositionCommand

object EmptyPositionCommand:
  def of(position: EmptyPosition)(
      replyTo: ActorRef[EmptyCommandResult],
  ): EmptyPositionCommand =
    EmptyPositionCommand(position.x, position.y)(replyTo)
