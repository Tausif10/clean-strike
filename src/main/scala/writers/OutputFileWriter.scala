package writers

import java.io.{File, PrintWriter}

import models.GameStatus

class OutputFileWriter extends OutputWriters {
  override def write(gameStatus: GameStatus): Unit = {
    val output =
      s"""players: ${gameStatus.players.mkString("\n")}
         |carrom board: ${gameStatus.carromBoard}
         |Game Status: ${gameStatus.status}
         |""".stripMargin
    val printWriter = new PrintWriter(new File("src/main/scala/writers/output.txt"))
    printWriter.write(output)
    printWriter.close
  }
}
