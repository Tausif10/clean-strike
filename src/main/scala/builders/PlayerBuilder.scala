package builders

import models.Player

class PlayerBuilder {

  def build(playesrName: List[String]): List[Player] = {
    playesrName.take(4).map(playerName => Player(playerName.capitalize))
  }
}
