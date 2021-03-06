package carrom.actions

import models.{CarromBoard, Player}

object StrikersStrike extends Actions {

  override def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addFoul.losePoint.addSuccessiveFailTurnCount, carromBoard)
  }
}
