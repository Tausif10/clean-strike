package actions

import models.actions.RedStrike
import models.{CarromBoard, Player}
import org.specs2.matcher.Scope
import org.specs2.mutable.Specification

class RedStrikeSpec extends Specification {

  "Red Strike Action" should {

    "Add 3 point to player's account" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) = readStrike.perform(player, carromBoard)
      updatedPlayer.point mustEqual 3
    }

    "remove successive failure turn count" in new Fixture {
      val playersWithSuccessiveFailureCount = player.copy(successiveFailTurn = 2)
      val (updatedPlayer, updatedCarromBoard) = readStrike.perform(playersWithSuccessiveFailureCount, carromBoard)
      updatedPlayer.successiveFailTurn mustEqual 1
    }

    "pocket read coin" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) = readStrike.perform(player, carromBoard)
      updatedCarromBoard.readCoin mustEqual 0
    }
  }

  trait Fixture extends Scope {
    val player = Player("test")
    val carromBoard = CarromBoard()
    val readStrike = new RedStrike()
  }
}
