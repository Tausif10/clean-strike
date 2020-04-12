package models.actions

import models.{CarromBoard, Player}

class ReadStrike extends StrikeActions {

  override def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addPoint.addPoint.addPoint.removeSuccessiveFailTurn, carromBoard.pocketReadCoin())
  }
}
