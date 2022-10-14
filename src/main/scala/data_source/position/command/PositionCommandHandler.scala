package data_source.position.command

import akka.persistence.typed.scaladsl.{Effect, ReplyEffect}
import data_source.position.PositionState
import data_source.position.event.{AlivePositionEvent, EmptyPositionEvent, PositionEvent}
import domain.{AliveCommandResult, AlivePosition, EmptyCommandResult, EmptyPosition}

class PositionCommandHandler:

  private[position] def handle(
      state: PositionState,
      command: PositionCommand,
  ): ReplyEffect[PositionEvent, PositionState] =
    command match
      case command @ AlivePositionCommand(x, y) =>
//        println(s"state:$state")
        Effect
          .persist(AlivePositionEvent)
          .thenReply(command.replyTo)(_ => AliveCommandResult(x, y))
      case command @ EmptyPositionCommand(x, y) =>
//        println(s"state:$state")
        Effect
          .persist(EmptyPositionEvent)
          .thenReply(command.replyTo)(_ => EmptyCommandResult(x, y))
