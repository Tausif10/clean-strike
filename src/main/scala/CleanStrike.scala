

import constant.ApplicationConstant
import readers.PlayerReader
import services.Carrom

class CleanStrike(playerReader: PlayerReader, carrom: Carrom) {

  def start(): Unit = {
    val players = playerReader.read(ApplicationConstant.PLAYER_NAME_FILE)
    carrom.play(players)
  }
}
