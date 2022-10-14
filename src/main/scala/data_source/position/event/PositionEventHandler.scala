package data_source.position.event

import akka.persistence.typed.scaladsl.{Effect, ReplyEffect}
import data_source.position.{AlivePositionState, PositionState, EmptyPositionState}

class PositionEventHandler:

  private[position] def handle(state: PositionState, event: PositionEvent): PositionState =
    event match
      case AlivePositionEvent => AlivePositionState(state.x, state.y)
      case EmptyPositionEvent => EmptyPositionState(state.x, state.y)
