package carrom.actions

import models.{CarromBoard, Player}

trait Actions {

  def perform(player: Player, carromBoard: CarromBoard): (Player, CarromBoard)
}
