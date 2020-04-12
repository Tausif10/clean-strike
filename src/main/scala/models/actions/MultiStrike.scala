package models.actions

import models.{CarromBoard, Player}

class MultiStrike extends StrikeActions {

  override def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addPoint.addPoint.removeSuccessiveFailTurn, carromBoard.pocketBlackCoin().pocketBlackCoin())
  }
}
