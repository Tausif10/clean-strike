package readers

import models.actions.{Multi, Actions}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import transformers.StrikerActionBuilder

import scala.util.{Success, Try}

class ActionFileInputReaderSpec extends Specification with Mockito {

  "Action file Reader" should {

    "read player's input file from given path" in new Fixture {
      private val expectedPlayersInput = List("Strike", "Multi Strike")
      val expectedActions: Try[List[Actions]] = Success(List(mock[Actions], mock[Multi]))
      mockStrikersActionBuilder.build(expectedPlayersInput) returns expectedActions
      val playersInput = actionFileInputReader.read("src/test/scala/resources/testActionInputFile")
      playersInput mustEqual expectedActions
    }

    "return failure file doesn't exist" in new Fixture {
      val playersInput = actionFileInputReader.read("invalidPath")
      playersInput must beFailedTry
    }
  }

  trait Fixture extends Scope {
    val mockStrikersActionBuilder = mock[StrikerActionBuilder]
    val actionFileInputReader = new ActionFileInputReader(mockStrikersActionBuilder)
  }

}
