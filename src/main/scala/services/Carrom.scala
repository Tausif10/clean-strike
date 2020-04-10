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
    val playersInputs = readInputOption(ApplicationConstant.INPUT_FILE)
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
      val (updatedPlayerOnTurn, updatedCarromBoard) = actions(inputs.head, findPlayerOnTurn(players), carromBoard)
      performActions(inputs.tail, updatesPlayers(players, updatedPlayerOnTurn), updatedCarromBoard)
    }
  }

  private def updatesPlayers(players: List[Player], updatedPlayerOnTurn: Player) = {
    val playersExcludingOnTurnPlayer = players.filterNot(_.name == updatedPlayerOnTurn.name)
    (updatedPlayerOnTurn :: playersExcludingOnTurnPlayer).map(_.updateStatus)
  }

  private def actions(choice: String, playerOnTurn: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    choice.trim.toLowerCase match {
      case STRIKE => (playerOnTurn.addPoint.removeSuccessiveFailTurn, carromBoard.pocketBlackCoin())
      case MULTI_STRIKE => (playerOnTurn.addPoint.addPoint.removeSuccessiveFailTurn, carromBoard.pocketBlackCoin().pocketBlackCoin())
      case RED_STRIKE => (playerOnTurn.addPoint.addPoint.addPoint.removeSuccessiveFailTurn, carromBoard.pocketReadCoin())
      case STRIKER_STRIKE => (playerOnTurn.addFoul.losePoint.addSuccessiveFailTurnCount, carromBoard)
      case DEFUNCT_COIN => (playerOnTurn.addFoul.losePoint.losePoint.addSuccessiveFailTurnCount, carromBoard)
      case _ => (playerOnTurn, carromBoard)
    }
  }

  private def findPlayerOnTurn(players: List[Player]): Player = {
    val playerOnTurns = players.filterNot(_.isMyTurn())
    playerOnTurns.headOption.getOrElse(players.head.updateStatus)
  }

  private def readInputOption(path: String): List[String] = {
    reader.read(path) match {
      case Success(inputOptions) => inputOptions
      case Failure(exception) => List.empty[String]
    }
  }

}
