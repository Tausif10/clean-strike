import constant.ApplicationConstant
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import readers.{ActionFileInputReader, PlayerFileInputReader}
import services.{Carrom, GameStatusFetcher}
import builders.{PlayerBuilder, StrikerActionBuilder}
import writers.OutputFileWriter

class CleanStrikeIntegrationSpec extends Specification with Mockito {

  "Clean strike" should {

    "start the game" in {
      val playerBuilder = new PlayerBuilder()
      val strikersActionBuilder = new StrikerActionBuilder()
      val inputReader = new ActionFileInputReader(strikersActionBuilder)
      val gameStatusFetcher = new GameStatusFetcher()
      val outputWriters = new OutputFileWriter(ApplicationConstant.OUTPUT_FILE)
      val carrom = new Carrom(gameStatusFetcher, outputWriters)
      val playerFileReader = new PlayerFileInputReader(playerBuilder)
      val cleanStrike = new CleanStrike(playerFileReader, inputReader, carrom)
      val res = cleanStrike.start()
      res must beSuccessfulTry
    }
  }
}
