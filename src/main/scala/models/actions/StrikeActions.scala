package models.actions

import models.{CarromBoard, Player}

trait StrikeActions {

  def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard)
}
