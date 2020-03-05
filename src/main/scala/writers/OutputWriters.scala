package writers

import constant.ApplicationConstant
import models.GameStatus

trait OutputWriters {

  def write(gameStatus: GameStatus, pathToWrite: String = ApplicationConstant.OUTPUT_FILE )
}
