package services

import carrom.actions.Strike
import constant.ApplicationConstant
import models.{CarromBoard, GameStatus, Player, PlayingState}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import writers.OutputWriter

class CarromSpec extends Specification with Mockito {

  "Carrom" should {

    "game is started by player1 and perform action" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(blackCoin = 8)
      val updatedPlayers = List(Player("Player1", 1, 0, 0, PlayingState.Wait), Player("Player2", 0, 0, 0, PlayingState.Play))
      val choices = List(Strike)

      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard.copy(blackCoin = 8)) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns GameStatus(updatedPlayers, "Draw", carromBoard)
      game.play(players, choices)

      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
    }

    "write game status to file" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(blackCoin = 8)
      val updatedPlayers = List(Player("Player1", 1, 0, 0, PlayingState.Wait), Player("Player2", 0, 0, 0, PlayingState.Play))
      val gameStatus: GameStatus = GameStatus(updatedPlayers, "Draw", carromBoard)
      val choices = List(Strike)

      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard.copy(blackCoin = 8)) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns gameStatus
      game.play(players, choices)

      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
      there was one(mockOutputWriters).write(gameStatus)
    }

  }

  trait Fixture extends Scope {
    val mockOutputWriters = mock[OutputWriter]
    val mockGameStatusFetcher = mock[GameStatusFetcher]
    val player1 = Player("Player1")
    val player2 = Player("Player2")
    val players = List(player1, player2)
    val carromBoard = CarromBoard()
    val gameStatusForEmptyInput = GameStatus(players, ApplicationConstant.strikeOptions(), carromBoard)
    val game = new Carrom(mockGameStatusFetcher, mockOutputWriters)
  }

}
