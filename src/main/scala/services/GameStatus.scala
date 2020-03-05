package services

import models.{CarromBoard, GameStatus, Player}

class GameStatusFetcher() {

  def getStatus(players: List[Player], carromBoard: CarromBoard) = {
    val playersSortByPoint = players.sortWith(_.point > _.point)
    val highScorer = playersSortByPoint.filter(_.point >= 5)
    if (highScorer.isEmpty) {
      GameStatus(playersSortByPoint, "Draw", carromBoard)
    } else {
      if (playersSortByPoint.tail.forall(player2 => pointDiff(playersSortByPoint.head, player2) >= 3)) {
        val updatedPlayers = playersSortByPoint.head.declareWinner :: playersSortByPoint.tail
        GameStatus(updatedPlayers, s"${updatedPlayers.head.name} won", carromBoard)
      } else {
        GameStatus(playersSortByPoint, "Draw", carromBoard)
      }
    }
  }

  def isGameOver(carromBoard: CarromBoard): Boolean = carromBoard.numOfCoinOnBoard <= 0

  private def pointDiff(player1: Player, player2: Player) = {
    player1.point - player2.point
  }
}
