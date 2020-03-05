

import readers.PlayerReader
import services.Carrom

class CleanStrike(playerReader: PlayerReader, carrom: Carrom) {

  def start(): Unit = {
    val players = playerReader.read()
    carrom.play(players)
  }
}
