package actions

import models.actions.Strike
import models.{CarromBoard, Player}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class StrikeSpec extends Specification {

  "Strike Action" should {

    "Add 1 point to player's account" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) = strike.perform(player, carromBoard)
      updatedPlayer.point mustEqual 1
    }

    "remove successive failure count" in new Fixture {
      val playersWithSuccessiveFailureCount = player.copy(successiveFailTurn = 2)
      val (updatedPlayer, updatedCarromBoard) = strike.perform(playersWithSuccessiveFailureCount, carromBoard)
      updatedPlayer.successiveFailTurn mustEqual 1
    }

    "pocket black coin on carrom board" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) = strike.perform(player, carromBoard)
      updatedCarromBoard.blackCoin mustEqual 8
    }
  }

  trait Fixture extends Scope {
    val player = Player("test")
    val carromBoard = CarromBoard()
    val strike = new Strike()
  }
}
