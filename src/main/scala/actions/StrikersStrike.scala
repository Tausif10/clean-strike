package models.actions

import models.{CarromBoard, Player}

class Strikers extends Actions {

  override def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addFoul.losePoint.addSuccessiveFailTurnCount, carromBoard)
  }
}
