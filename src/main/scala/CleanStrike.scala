

import constant.ApplicationConstant
import models.Player
import readers.InputReader
import services.Carrom

class CleanStrike(playerReader: InputReader[List[Player]], carrom: Carrom) {

  def start(): Unit = {
    val players = playerReader.read(ApplicationConstant.PLAYER_NAME_FILE)
    carrom.play(players)
  }
}
