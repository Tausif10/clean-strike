package carrom.actions

import models.{CarromBoard, Player}

object DefunctCoin extends Actions {

  override def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addFoul.losePoint.losePoint.addSuccessiveFailTurnCount, carromBoard.takeCoinOut)
  }
}
