package main

import domain.{AlivePosition, Board, EmptyPosition, Position}

import scala.sys.process.*
import scala.util.{Failure, Random, Success}
import data_source.position.PositionRepositoryActor
import data_source.position.command.AlivePositionCommand
import akka.util.Timeout

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}

object Main:

  final val width = 50
  final val height = 50

  val actor: PositionRepositoryActor = ActorSupporter.actor

  def makeLife(): Position =
    val r = new Random()
    AlivePosition(r.nextInt(width), r.nextInt(height))

  def init(): Board =
    val s = for (
      i <- 0 until width;
      j <- 0 until height
    ) yield EmptyPosition(i, j)

    Range(0, width * 10).foldLeft(Board(s.toList))((b, _) => b.update(makeLife()))

  def main(args: Array[String]): Unit =
    run(init())

  def print(board: Board): Unit =
    Process("clear").run
    println(Range(0, width + 2).map(_ => "-").mkString)
    for i <- 0 until height do
      println("|" + Range(0, width).map(x => if board.isAlive(x, i) then "*" else " ").mkString + "|")
    println(Range(0, width + 2).map(_ => "-").mkString)
    Console.flush()

  @tailrec
  def run(board: Board): Int =
    print(board)
    Thread.sleep(500)
    val updatedPos = board.nextGen2()
    updatedPos.map(command)
    run(board.update(updatedPos))

  def command(position: Position): Future[Position] =
    actor.find(position.key).update(position)
