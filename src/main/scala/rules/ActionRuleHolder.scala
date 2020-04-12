package rules

import models.{CarromBoard, Player}

object ActionRuleHolder extends RuleMaker[(Player, CarromBoard)] {

  def rulesForStrike(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addPoint.removeSuccessiveFailTurn, carromBoard.pocketBlackCoin())
  }

  def rulesForMultiStrike(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addPoint.addPoint.removeSuccessiveFailTurn, carromBoard.pocketBlackCoin().pocketBlackCoin())
  }

  def rulesForRedStrike(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addPoint.addPoint.addPoint.removeSuccessiveFailTurn, carromBoard.pocketReadCoin())
  }

  def rulesForStrikersStrike(player: Player, carromBoard: CarromBoard): (Player, CarromBoard) = {
    (player.addFoul.losePoint.addSuccessiveFailTurnCount, carromBoard)
  }

  def rulesForDefunctCoin(player: Player, carromBoard: CarromBoard) = {
    (player.addFoul.losePoint.losePoint.addSuccessiveFailTurnCount, carromBoard)
  }

}
