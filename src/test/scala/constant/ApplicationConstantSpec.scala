package constant

import org.specs2.mutable.Specification

class ApplicationConstantSpec extends Specification {

  "Application constant" should {

    "contain all constant of application" >> {

      "Options available for playing games" in {
        val expectedOptions =
          """****** Options ******
            |Strike
            |Multi strike
            |Red strike
            |Striker strike
            |Defunct coin
            |Enter your choices as new line in file:[src/main/scala/resource/InputFile]
            |""".stripMargin
        ApplicationConstant.strikeOptions() mustEqual expectedOptions
      }

      "max successive fail strike allowed" in {
        ApplicationConstant.MAX_SUCCESSIVE_FAIL_ALLOW mustEqual (3)
      }

      "max foul allowed" in {
        ApplicationConstant.MAX_FOUL_COUNTS_ALLOW mustEqual (3)
      }

      "output file path" in {
        ApplicationConstant.OUTPUT_FILE mustEqual "src/main/scala/writers/output.txt"
      }

      "Player name file path" in {
        ApplicationConstant.PLAYER_NAME_FILE mustEqual "src/main/scala/resource/PlayersFile"
      }
    }
  }
}
