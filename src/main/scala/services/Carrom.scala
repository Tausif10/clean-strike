package services

import constant.ApplicationConstant
import constant.ApplicationConstant._
import models._
import readers.InputReader
import rules.ActionRuleHolder._
import writers.OutputWriters

import scala.annotation.tailrec
import scala.util.Try

class Carrom(reader: InputReader[Try[List[String]]], gameStatusFetcher: GameStatusFetcher, outputWriters: OutputWriters) {

  def play(players: List[Player]) {
    val actionInputs = readInputOption(ApplicationConstant.INPUT_FILE)
    val board = CarromBoard()
    val gameStatus = if (actionInputs.nonEmpty) {
      val (updatedPlayer, carromBoard) = performActions(actionInputs, players, board)
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
      val updatedPlayers = updatesPlayers(players, updatedPlayerOnTurn)
      performActions(inputs.tail, updatedPlayers, updatedCarromBoard)
    }
  }

  private def updatesPlayers(players: List[Player], updatedPlayerOnTurn: Player) = {
    val playersExcludingOnTurnPlayer = players.filterNot(_.name == updatedPlayerOnTurn.name)
    (updatedPlayerOnTurn :: playersExcludingOnTurnPlayer).map(_.updateStatus)
  }

  private def actions(choice: String, playerOnTurn: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    choice.trim.toLowerCase match {
      case STRIKE => rulesForStrike(playerOnTurn, carromBoard)
      case MULTI_STRIKE => rulesForMutiStrike(playerOnTurn, carromBoard)
      case RED_STRIKE => rulesForRedStrike(playerOnTurn, carromBoard)
      case STRIKER_STRIKE => rulesForStrikersStrike(playerOnTurn, carromBoard)
      case DEFUNCT_COIN => rulesForDefunctCoin(playerOnTurn, carromBoard)
      case _ => (playerOnTurn, carromBoard)
    }
  }

  private def findPlayerOnTurn(players: List[Player]): Player = {
    val playerOnTurns = players.filterNot(_.isMyTurn())
    playerOnTurns.headOption.getOrElse(players.head.updateStatus)
  }

  private def readInputOption(path: String): List[String] = {
    reader.read(path).getOrElse(List.empty[String])
  }

}
