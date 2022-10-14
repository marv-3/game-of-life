package main

import akka.actor.typed.ActorSystem
import akka.cluster.sharding.typed.scaladsl.{ClusterSharding, Entity, EntityRef}
import data_source.position.command.PositionCommand
import data_source.position.PositionRepositoryActor
import data_source.position.PositionEntityFactory
import data_source.position.command.PositionCommandHandler
import data_source.position.event.PositionEventHandler
import akka.actor.typed.scaladsl.Behaviors

object ActorSupporter:

  def actor: PositionRepositoryActor =
    val actorSystem = ActorSystem(Behaviors.empty, "lg")
    PositionRepositoryActor(
      actorSystem,
      ClusterSharding(actorSystem),
      PositionEntityFactory(PositionCommandHandler(), PositionEventHandler()),
    )
