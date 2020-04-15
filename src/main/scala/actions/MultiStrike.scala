package models.actions

import models.{CarromBoard, Player}

class Multi extends Actions {

  override def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addPoint.addPoint.removeSuccessiveFailTurn, carromBoard.pocketBlackCoin().pocketBlackCoin())
  }
}
