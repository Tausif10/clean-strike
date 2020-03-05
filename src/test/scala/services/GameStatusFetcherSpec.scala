package services

import models.{CarromBoard, GameStatus, Player, PlayingState}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class GameStatusFetcherSpec extends Specification {

  "Game status fetcher service" should {

    "return game status as Player1 win if palyer has 5 point and point diff with other player is 3 or more" in
      new Fixture {
        val player1 = Player("Player1").copy(point = 5)
        val player2 = Player("Player2")
        val updatedPlayers = List(player1.copy(playStatus = PlayingState.Win), player2)
        val players = List(player1, player2)
        val gameStatus = gameStatusFetcher.getStatus(players, carromBoard)
        val updatedPlayer1 = gameStatus.players.head
        val updatedPlayer2 = gameStatus.players.last
        updatedPlayer1.point mustEqual 5
        updatedPlayer1.playStatus mustEqual PlayingState.Win
        updatedPlayer1.name mustEqual "Player1"
        gameStatus mustEqual GameStatus(updatedPlayers, """Player1 won""", carromBoard)
      }

    "return game status as Draw when there is no player having point 5 or more and difference between coin is not 3 or greater" in
      new Fixture {
        val player1 = Player("Player1").copy(point = 5)
        val player2 = Player("Player2").copy(point = 3)
        val players = List(player1, player2)
        val gameStatus = gameStatusFetcher.getStatus(players, carromBoard)
        gameStatus mustEqual GameStatus(players, "Draw", carromBoard)
      }
  }

  trait Fixture extends Scope {
    val gameStatusFetcher = new GameStatusFetcher()
    val carromBoard = CarromBoard()
  }

}
