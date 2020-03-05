package services

import constant.ApplicationConstant
import models.{CarromBoard, GameStatus, Player, PlayingState}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import readers.InputReader
import writers.OutputWriters

import scala.util.Success

class CarromSpec extends Specification with Mockito {

  "Carrom" should {

    "game is started by player1 and add one point if input is 'Strike'" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(blackCoin = 8)
      val updatedPlayers = List(Player("Player1", 1, 0, 0, PlayingState.Wait), Player("Player2", 0, 0, 0, PlayingState.Play))
      mockReader.read() returns Success(List("Strike"))
      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard.copy(blackCoin = 8)) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns GameStatus(updatedPlayers, "Draw", carromBoard)
      game.play(players)
      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
    }

    "Player win two point if input is 'Multi strike'" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(blackCoin = 7)
      val updatedPlayers = List(Player("Player1", 2, 0, 0, PlayingState.Wait), Player("Player2", 0, 0, 0, PlayingState.Play))
      mockReader.read() returns Success(List("Multi strike"))
      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard.copy(blackCoin = 7)) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns GameStatus(updatedPlayers, "Draw", carromBoard)
      game.play(players)
      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
    }

    "Player win three point if input is 'Red strike'" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(readCoin = 0)
      val updatedPlayers = List(Player("Player1", 3, 0, 0, PlayingState.Wait), Player("Player2", 0, 0, 0, PlayingState.Play))
      mockReader.read() returns Success(List("Red strike"))
      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns GameStatus(updatedPlayers, "Draw", carromBoard)
      game.play(players)
      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
    }

    "Player lose 1 point and add foul and successive fail count if input is 'Striker strike'" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(blackCoin = 8)
      val updatedPlayers = List(Player("Player2", 1, 0, 0, PlayingState.Wait), Player("Player1", -1, 1, 1, PlayingState.Play))
      mockReader.read() returns Success(List("Striker strike", "Strike"))
      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns GameStatus(updatedPlayers, "Draw", carromBoard)
      game.play(players)
      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
    }

    "Player lose 2 point and add a foul and a successive fail count if input is 'Striker strike'" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(blackCoin = 8)
      val updatedPlayers = List(Player("Player2", 1, 0, 0, PlayingState.Wait), Player("Player1", -2, 1, 1, PlayingState.Play))
      mockReader.read() returns Success(List("Defunct coin", "Strike"))
      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns GameStatus(updatedPlayers, "Draw", carromBoard)
      game.play(players)
      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
    }

    "Player lose 1 extra point for on 3 successive fail count or 3 foul and clear his successive fail count" in new Fixture {
      val updatedBoard: CarromBoard = carromBoard.copy(blackCoin = 6)
      val updatedPlayers = List(Player("Player2", 3, 0, 0, PlayingState.Wait), Player("Player1", -5, 0, 0, PlayingState.Play))
      mockReader.read() returns Success(List("Striker strike", "Strike", "Striker strike", "Strike", "Striker strike", "Strike"))
      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(updatedBoard) returns true
      mockGameStatusFetcher.getStatus(updatedPlayers, updatedBoard) returns GameStatus(updatedPlayers, "Draw", carromBoard)
      game.play(players)
      there was one(mockGameStatusFetcher).getStatus(updatedPlayers, updatedBoard)
    }

    "update player and carrom updatedBoard and write output to file" in new Fixture {
      val updatedPlayers = List(Player("Player2", 1, 0, 0, PlayingState.Wait), Player("Player1", 1, 0, 0, PlayingState.Play))
      val gameStatus = GameStatus(updatedPlayers, "Draw", carromBoard)
      mockReader.read() returns Success(List("Strike", "Strike"))
      mockGameStatusFetcher.isGameOver(carromBoard) returns false
      mockGameStatusFetcher.isGameOver(carromBoard.copy(blackCoin = 8)) returns false
      mockGameStatusFetcher.isGameOver(carromBoard.copy(blackCoin = 7)) returns true

      mockGameStatusFetcher.getStatus(updatedPlayers, carromBoard.copy(blackCoin = 7)) returns gameStatus
      game.play(players)
      there was three(mockGameStatusFetcher).isGameOver(any())
      there was one(mockGameStatusFetcher).getStatus(any(), any())
      there was one(mockOutputWriters).write(any(), any())
    }

    "when there is no input provided then write available options to output file" in new Fixture {
      mockReader.read() returns Success(List.empty)
      game.play(players)
      there was one(mockOutputWriters).write(gameStatusForEmptyInput.copy())
      there was one(mockReader).read()
      there was no(mockGameStatusFetcher).isGameOver(any())
        }
  }

  trait Fixture extends Scope {
    val mockOutputWriters = mock[OutputWriters]
    val mockGameStatusFetcher = mock[GameStatusFetcher]
    val mockReader = mock[InputReader]
    val player1 = Player("Player1")
    val player2 = Player("Player2")
    val players = List(player1, player2)
    val carromBoard = CarromBoard()
    val gameStatusForEmptyInput = GameStatus(players, ApplicationConstant.strikeOptions(), carromBoard)
    val game = new Carrom(mockReader, mockGameStatusFetcher, mockOutputWriters)

  }

}
