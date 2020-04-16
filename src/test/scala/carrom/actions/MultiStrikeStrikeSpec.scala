package carrom.actions

import models.{CarromBoard, Player}
import org.specs2.matcher.Scope
import org.specs2.mutable.Specification

class MultiStrikeStrikeSpec extends Specification {

  "Multi Strike Action" should {

    "Add 2 point to player's account" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) =multiStrike.perform(player, carromBoard)
      updatedPlayer.point mustEqual 2
    }

    "minus successive fail turn count if any" in new Fixture {
      val playerWithSuccessiveFailedCount = player.copy(successiveFailTurn = 2)
      val (updatedPlayer, updatedCarromBoard) = multiStrike.perform(playerWithSuccessiveFailedCount, carromBoard)
      updatedPlayer.successiveFailTurn mustEqual 1
    }

    "pocket two black coin on the board" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) =multiStrike.perform(player, carromBoard)
      updatedCarromBoard.blackCoin mustEqual 7
    }
  }

  trait Fixture extends Scope {
    val player = Player("test")
    val carromBoard = CarromBoard()
    val multiStrike = MultiStrike
  }
}
