package readers

import models.Player
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import builders.PlayerBuilder

import scala.util.Success

class PlayerFileInputReaderSpec extends Specification with Mockito {

  "Player file reader" should {
    "build player from name when file is non empty and file is exists" in {
      val expectedPlayers = List(Player("Player1"), Player("Player2"))
      val mockPlayerBuilder = mock[PlayerBuilder]
      mockPlayerBuilder.build(List("Player1", "Player2")) returns expectedPlayers
      val playerFileInputReader = new PlayerFileInputReader(mockPlayerBuilder)
      val players = playerFileInputReader.read("src/test/scala/resources/testPlayerFile")
      players mustEqual Success(expectedPlayers)
      there was one(mockPlayerBuilder).build(List("Player1", "Player2"))
    }

    "return failure with exception when file does not exists or file is empty" in {
      val mockPlayerBuilder = mock[PlayerBuilder]
      val playerFileInputReader = new PlayerFileInputReader(mockPlayerBuilder)
      val players = playerFileInputReader.read("invalid")
      players must beFailedTry
      there was no(mockPlayerBuilder).build(any())
    }
  }
}
