package services

import constant.ApplicationConstant
import constant.ApplicationConstant._
import models._
import readers.InputReader
import writers.OutputWriters

import scala.annotation.tailrec
import scala.util.{Failure, Success}

class Carrom(reader: InputReader, gameStatusFetcher: GameStatusFetcher, outputWriters: OutputWriters) {

  def play(players: List[Player]) {
    val playersInputs = readInputOption()
    val board = CarromBoard()
    val gameStatus = if (playersInputs.nonEmpty) {
      val (updatedPlayer, carromBoard) = performActions(playersInputs, players, board)
      gameStatusFetcher.getStatus(updatedPlayer, carromBoard)
    } else {
      GameStatus(players, ApplicationConstant.strikeOptions(), board)
    }
    outputWriters.write(gameStatus)
  }

  @tailrec
  private def performActions(inputs: List[String], players: List[Player], carromBoard: CarromBoard): (List[Player], CarromBoard) = {
    if (gameStatusFetcher.isGameOver(carromBoard) || inputs.isEmpty) {
      (players, carromBoard)
    } else {
      val (updatedPlayers, updatedCarromBoard) = actions(inputs.head, players, carromBoard)
      performActions(inputs.tail, updatedPlayers, updatedCarromBoard)
    }
  }

  private def actions(choice: String, players: List[Player], carromBoard: CarromBoard): (List[Player], CarromBoard) = {
    val playerOnTurn = findPlayerOnTurn(players)
    val playersExcludingOnTurnPlayer = players.filterNot(_.name == playerOnTurn.name)

    choice.trim match {
      case STRIKE => ((playerOnTurn.addPoint.removeSuccessiveFailTurn :: playersExcludingOnTurnPlayer).map(_.updateStatus),
        carromBoard.pocketBlackCoin())
      case MULTI_STRIKE => ((playerOnTurn.addPoint.addPoint.removeSuccessiveFailTurn ::
        playersExcludingOnTurnPlayer).map(_.updateStatus), carromBoard.pocketBlackCoin().pocketBlackCoin())
      case RED_STRIKE => ((playerOnTurn.addPoint.addPoint.addPoint.removeSuccessiveFailTurn ::
        playersExcludingOnTurnPlayer).map(_.updateStatus), carromBoard.pocketReadCoin())
      case STRIKER_STRIKE => ((playerOnTurn.addFoul.losePoint.addSuccessiveFailTurnCount ::
        playersExcludingOnTurnPlayer).map(_.updateStatus), carromBoard)
      case DEFUNCT_COIN => ((playerOnTurn.addFoul.losePoint.losePoint.addSuccessiveFailTurnCount ::
        playersExcludingOnTurnPlayer).map(_.updateStatus), carromBoard)
      case _ => (playerOnTurn :: playersExcludingOnTurnPlayer, carromBoard)
    }
  }

  private def findPlayerOnTurn(players: List[Player]): Player = {
    val (playerWaitingForTurn, playerOnTurn) = players.partition(_.isMyTurn())
    if (playerOnTurn.isEmpty) playerWaitingForTurn.head.updateStatus else playerOnTurn.head
  }

  private def readInputOption(): List[String] = {
    reader.read() match {
      case Success(inputOptions) => inputOptions
      case Failure(exception) => List.empty[String]
    }
  }

}
