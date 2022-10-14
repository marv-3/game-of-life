package data_source.position.command

import akka.actor.typed.ActorRef
import domain.{AliveCommandResult, AlivePosition, CommandResult, Position}

case class AlivePositionCommand(val x: Int, val y: Int)(val replyTo: ActorRef[AliveCommandResult])
    extends PositionCommand

object AlivePositionCommand:
  def of(position: Position)(
      replyTo: ActorRef[AliveCommandResult],
  ): AlivePositionCommand =
    AlivePositionCommand(position.x, position.y)(replyTo)
