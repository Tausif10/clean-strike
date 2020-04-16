package carrom.actions

import models.{CarromBoard, Player}

object Strike extends Actions {

  override def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addPoint.removeSuccessiveFailTurn, carromBoard.pocketBlackCoin())
  }
}
