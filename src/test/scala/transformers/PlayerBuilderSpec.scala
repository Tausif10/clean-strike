package transformers

import models.Player
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class PlayerBuilderSpec extends Specification {

  "Player build" should {

    "build players from name list" in new Fixture {
      val playersName = List("Player1", "Player2")
      val expectedPlayers = List(Player("Player1"), Player("Player2"))
      playerBuilder.build(playersName) mustEqual expectedPlayers
    }

    "build only 4 players when input names are more than 4" in new Fixture {
      val moreThan4Names = List("Player1", "Player2", "Player3", "Player4", "Player5", "Player6")
      val expectedPlayers = List(Player("Player1"), Player("Player2"),
        Player("Player3"), Player("Player4"))
      playerBuilder.build(moreThan4Names) mustEqual expectedPlayers
    }
  }

  trait Fixture extends Scope {
    val playerBuilder = new PlayerBuilder()
  }

}
