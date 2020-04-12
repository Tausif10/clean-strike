

import constant.ApplicationConstant
import models.Player
import models.actions.StrikeActions
import readers.InputReader
import services.Carrom

import scala.util.Try

class CleanStrike(playerReader: InputReader[List[Player]],
                  actionReader: InputReader[List[StrikeActions]],
                  carrom: Carrom) {

  def start(): Try[Unit] = {
    for {
      players <- readPlayers
      choices <- readActions
    } yield carrom.play(players, choices)
  }

  def readPlayers: Try[List[Player]] = {
    playerReader.read(ApplicationConstant.PLAYER_NAME_FILE)
  }

  def readActions: Try[List[StrikeActions]] = {
    actionReader.read(ApplicationConstant.INPUT_FILE)
  }
}
