package writers

import models.GameStatus

trait OutputWriter {

  def write(gameStatus: GameStatus)
}
