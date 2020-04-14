package models.actions

import models.{CarromBoard, Player}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class DefunctCoinSpec extends Specification {

  "Defunct coin Action" should {

    "Add foul to the player" in new Fixture {
      player.fouls mustEqual 0
      val (updatedPlayer,updatedCarromBoard) = defunctCoinAction.perform(player, carromBoard)
      updatedPlayer.fouls mustEqual 1
    }

    "minus 2 points from player's point" in new Fixture {
      player.point mustEqual 0
      val (updatedPlayer,updatedCarromBoard) = defunctCoinAction.perform(player, carromBoard)
      updatedPlayer.point mustEqual -2
    }

    "add successive failure count in players account" in new Fixture {
      player.successiveFailTurn mustEqual 0
      val (updatedPlayer,updatedCarromBoard) = defunctCoinAction.perform(player, carromBoard)
      updatedPlayer.successiveFailTurn mustEqual 1
    }

    "take black coin out of the board if black coins are available" in new Fixture {
      val (updatedPlayer,updatedCarromBoard) = defunctCoinAction.perform(player, carromBoard)
      updatedCarromBoard.blackCoin mustEqual 8
    }

    "take read coin out of the board if black coins are not available" in new Fixture {
      val (updatedPlayer,updatedCarromBoard) = defunctCoinAction.perform(player, carromBoard.copy(blackCoin = 0))
      updatedCarromBoard.readCoin mustEqual 0
    }
  }

  trait Fixture extends Scope {
    val player = Player("test")
    val carromBoard = CarromBoard()
    val defunctCoinAction = new DefunctCoin()
  }
}
