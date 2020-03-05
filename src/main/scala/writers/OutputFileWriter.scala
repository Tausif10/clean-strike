package writers

import java.io.{File, PrintWriter}

import models.GameStatus

class OutputFileWriter extends OutputWriters {
  override def write(gameStatus: GameStatus, pathToWrite: String): Unit = {
    val output =
      s"""players:\n\t${gameStatus.players.mkString("\n\t")}
         |carrom board: ${gameStatus.carromBoard}
         |Game Status: ${gameStatus.status}
         |""".stripMargin
    val printWriter = new PrintWriter(new File(pathToWrite))
    printWriter.write(output)
    printWriter.close
  }
}
