package readers

import java.io.FileNotFoundException

import org.specs2.matcher.Matchers
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import scala.util.Success

class ActionFileInputReaderSpec extends Specification {

  "Action file Reader" should {

    "read player's input file from given path" in new Fixture {
      private val expectedPlayersInput = List("Strike", "Multi Strike")
      val playersInput = actionFileInputReader.read("src/test/scala/resources/testActionInputFile")
      playersInput mustEqual Success(expectedPlayersInput)
    }

    "return failure file doesn't exist" in new Fixture {
      val playersInput = actionFileInputReader.read("invalidPath")
      playersInput must beFailedTry
    }
  }

  trait Fixture extends Scope {
    val actionFileInputReader = new ActionFileInputReader()
  }

}
