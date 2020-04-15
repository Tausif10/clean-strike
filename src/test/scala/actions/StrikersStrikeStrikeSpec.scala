package actions

import models.actions.Strikers
import models.{CarromBoard, Player}
import org.specs2.matcher.Scope
import org.specs2.mutable.Specification

class StrikersStrikeSpec extends Specification {

  "Strikers strike" should {

    "Add foul to player's account" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) = strikersStrike.perform(player, carromBoard)
      updatedPlayer.fouls mustEqual 1
    }

    "lose 1 point from player's account" in new Fixture {
      val (updatedPlayer, updatedCarromBoard) = strikersStrike.perform(player, carromBoard)
      updatedPlayer.point mustEqual -1
    }

    "add successive failure count in players account" in new Fixture {
      val (updatedPlayer,updatedCarromBoard) = strikersStrike.perform(player, carromBoard)
      updatedPlayer.successiveFailTurn mustEqual 1
    }
  }

  trait Fixture extends Scope {
    val player = Player("test")
    val carromBoard = CarromBoard()
    val strikersStrike = new Strikers()
  }
}
