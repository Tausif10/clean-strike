

import constant.ApplicationConstant
import models.Player
import readers.InputReader
import services.Carrom

import scala.util.Try

class CleanStrike(playerReader: InputReader[List[Player]], carrom: Carrom) {

  def start(): Try[Unit] = {
    readPlayers.map(carrom.play)
  }

  def readPlayers: Try[List[Player]] = {
    playerReader.read(ApplicationConstant.PLAYER_NAME_FILE)
  }
}
