package rules

import models.{CarromBoard, Player}

trait RuleMaker[T] {
  def rulesForStrike(player: Player, carromBoard: CarromBoard): T

  def rulesForMutiStrike(player: Player, carromBoard: CarromBoard): T

  def rulesForRedStrike(player: Player, carromBoard: CarromBoard): T

  def rulesForStrikersStrike(player: Player, carromBoard: CarromBoard): T

  def rulesForDefunctCoin(player: Player, carromBoard: CarromBoard): T
}
