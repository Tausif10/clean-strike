package models

import constant.ApplicationConstant._

case class Player(name: String, point: Int = 0,
                  fouls: Int = 0,
                  successiveFailTurn: Int = 0,
                  playStatus: PlayingState = PlayingState.Wait) {

  def isMyTurn(): Boolean = this.playStatus == PlayingState.Wait

  def addPoint: Player = this.copy(point = point + 1)

  def clearFoul: Player = this.copy(fouls = 0)

  def addFoul: Player = {
    val updatedPlayer = this.copy(fouls = fouls + 1)
    if (updatedPlayer.fouls == MAX_FOUL_COUNTS_ALLOW)
      updatedPlayer.clearFoul.losePoint
    else {
      updatedPlayer
    }
  }

  def declareWinner = this.copy(playStatus = PlayingState.Win)

  def updateStatus: Player = {
    if (this.playStatus == PlayingState.Play)
      this.copy(playStatus = PlayingState.Wait)
    else
      this.copy(playStatus = PlayingState.Play)
  }

  def losePoint: Player = this.copy(point = point - 1)


  def clearSuccessiveFailTurnCOunt = this.copy(successiveFailTurn = 0)

  def addSuccessiveFailTurnCount: Player = {
    val updatedPlayer = this.copy(successiveFailTurn = successiveFailTurn + 1)
    if (updatedPlayer.successiveFailTurn == MAX_SUCCESSIVE_FAIL_ALLOW) {
      updatedPlayer.clearSuccessiveFailTurnCOunt.losePoint
    } else {
      updatedPlayer
    }
  }

  def removeSuccessiveFailTurn: Player = {
    if (successiveFailTurn > 0)
      this.copy(successiveFailTurn = successiveFailTurn - 1)
    else this
  }
}
