//import models.{CarromBoard, Player}
//import org.specs2.mock.Mockito
//import org.specs2.mutable.Specification
//import services.Carrom
//
//class CleanStrikeSpec extends Specification with Mockito {
//
//  "Game" should {
//
//    "start the game" in {
//      val mockGame = mock[Carrom]
//      val mockPlayers = mock[List[Player]]
//      val cleanStrike = new CleanStrike(mockGame, mockPlayers)
//      mockGame.start(mockPlayers) returns ""
//      cleanStrike.play() mustEqual ""
//    }
//  }
//}
