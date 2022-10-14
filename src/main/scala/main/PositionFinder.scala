package main

import scala.concurrent.{ExecutionContext, Future}
import akka.util.Timeout
import akka.cluster.sharding.typed.scaladsl.{ClusterSharding, Entity, EntityRef}
import data_source.position.command.PositionCommand
import domain.{AlivePosition, Position}

case class PositionFinder(entityRef: EntityRef[PositionCommand])(using askTimeout: Timeout, ec: ExecutionContext):
  def update(p: Position): Future[Position] =
    for result <- entityRef ? PositionCommand.of(p)
    yield result.to()
