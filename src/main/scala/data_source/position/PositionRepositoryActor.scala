package data_source.position

import domain.PositionKey
import akka.actor.typed.ActorSystem
import akka.cluster.sharding.typed.scaladsl.{ClusterSharding, Entity, EntityRef}
import data_source.position.command.PositionCommand
import akka.util.Timeout
import main.PositionFinder

import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext

class PositionRepositoryActor(
    val actorSystem: ActorSystem[?],
    val clusterSharding: ClusterSharding,
    val factory: PositionEntityFactory,
):

  clusterSharding.init(Entity(typeKey = PositionEntity.typeKey) { entityContext =>
    factory(PositionKey(entityContext.entityId))
  })

  given askTimeout: Timeout = Timeout(5.seconds)
  given ec: ExecutionContext = actorSystem.executionContext

  def find(
      key: PositionKey,
  ): PositionFinder =
    PositionFinder(
      clusterSharding.entityRefFor(
        PositionEntity.typeKey,
        key.value,
      ),
    )
