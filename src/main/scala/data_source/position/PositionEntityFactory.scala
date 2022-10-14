package data_source.position

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.Behavior
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior, ReplyEffect, RetentionCriteria}
import data_source.position.command.{PositionCommand, PositionCommandHandler}
import data_source.position.event.{PositionEvent, PositionEventHandler}
import domain.{Position, PositionKey}

class PositionEntityFactory(commandHandler: PositionCommandHandler, eventHandler: PositionEventHandler):

  def apply(key: PositionKey): Behavior[PositionCommand] =
    Behaviors.setup { context =>
      EventSourcedBehavior
        .withEnforcedReplies[
          PositionCommand,
          PositionEvent,
          PositionState,
        ](
          PersistenceId(PositionEntity.typeKey.name, key.value),
          EmptyPositionState(key.toPoint._1, key.toPoint._2),
          commandHandler.handle,
          eventHandler.handle,
        )
        .withRetention(
          RetentionCriteria.snapshotEvery(
            numberOfEvents = 1,
            keepNSnapshots = 3,
          ),
        )
    }
