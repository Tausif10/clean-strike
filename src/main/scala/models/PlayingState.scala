package models

sealed trait PlayingState{
  def getState: String
}

object PlayingState {

  case object Play extends PlayingState {
    override def getState: String = "play"
  }
  case object Wait extends PlayingState{
    override def getState: String = "wait"
  }

  case object Win extends PlayingState {
    override def getState: String = "win"
  }
}
