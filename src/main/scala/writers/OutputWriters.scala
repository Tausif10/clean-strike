package writers

import models.GameStatus

trait OutputWriters {

  def write(gameStatus: GameStatus)
}
