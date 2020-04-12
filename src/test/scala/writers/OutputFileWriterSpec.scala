package writers

import models.{CarromBoard, GameStatus, Player}
import org.specs2.mutable.Specification

class OutputFileWriterSpec extends Specification {

  "Output file writer" should {

    "write output to file" in {
      val players = List(Player("Player1"), Player("Player2"))
      val gameStatus = GameStatus(players, "Draw", CarromBoard())
      val pathToFile = "src/test/scala/resources/testOutputFile"
      val outputFileWriter = new OutputFileWriter(pathToFile)
      val expectedOutput = s"""players:\n\t${players.mkString("\n\t")}
                              |carrom board: ${gameStatus.carromBoard}
                              |Game Status: ${gameStatus.status}
                              |""".stripMargin
      outputFileWriter.write(gameStatus)
      val outputFile = scala.io.Source.fromFile(pathToFile).getLines().toList
      outputFile.mkString must beEqualTo(expectedOutput).ignoreSpace
    }
  }
}
