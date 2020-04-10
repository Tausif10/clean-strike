import models.{CarromBoard, Player}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import readers.{ActionFileInputReader, PlayerFileInputReader}
import services.{Carrom, GameStatusFetcher}
import transformers.PlayerBuilder
import writers.OutputFileWriter

class CleanStrikeIntegrationSpec extends Specification with Mockito {

  "Game" should {

    "start the game" in {
      val playerBuilder = new PlayerBuilder()
      val inputReader = new ActionFileInputReader()
      val gameStatusFetcher = new GameStatusFetcher()
      val outputWriters = new OutputFileWriter()
      val carrom = new Carrom(inputReader, gameStatusFetcher, outputWriters)
      val playerFileReader = new PlayerFileInputReader(playerBuilder)
      val cleanStrike = new CleanStrike(playerFileReader, carrom)
      cleanStrike.start()
      true
    }
  }
}
