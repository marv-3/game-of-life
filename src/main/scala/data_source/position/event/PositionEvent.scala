package data_source.position.event

// alive と　empty があるけど省略
sealed trait PositionEvent
object AlivePositionEvent extends PositionEvent
object EmptyPositionEvent extends PositionEvent
