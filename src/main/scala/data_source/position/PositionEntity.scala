package data_source.position

import akka.cluster.sharding.typed.scaladsl.EntityTypeKey
import data_source.position.command.PositionCommand

object PositionEntity {

  private[position] val typeKey: EntityTypeKey[PositionCommand] =
    EntityTypeKey[PositionCommand]("PositionEntity")

}
