package builders

import carrom.actions.{DefunctCoin, MultiStrike, RedStrike, Strike, StrikersStrike}
import org.specs2.matcher.Scope
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import scala.util.{Failure, Success}

class StrikerActionBuilderSpec extends Specification with Mockito {

  "Striker Action builder" should {

    "build strike action when input is 'Strike'" in new Fixture {
      val input = List("Strike")
      val actualAction = strikerActionBuilder.build(input)
      actualAction mustEqual Success(List(Strike))
    }

    "build multi strike action when input is 'multi strike'" in new Fixture {
      val input = List("multi strike")
      val actualAction = strikerActionBuilder.build(input)
      actualAction mustEqual Success(List(MultiStrike))
    }

    "build red strike action when input is 'red strike'" in new Fixture {
      val input = List("red strike")
      val actualAction = strikerActionBuilder.build(input)
      actualAction mustEqual Success(List(RedStrike))
    }

    "build striker strike action when input is 'striker strike'" in new Fixture {
      val input = List("striker strike")
      val actualAction = strikerActionBuilder.build(input)
      actualAction mustEqual Success(List(StrikersStrike))
    }

    "build defunct coin action when input is 'defunct coin'" in new Fixture {
      val input = List("defunct coin")
      val actualAction = strikerActionBuilder.build(input)
      actualAction mustEqual Success(List(DefunctCoin))
    }

    "return failure with error msg on input other than above" in new Fixture {
      val input = List("invalid input")
      val actualAction = strikerActionBuilder.build(input)
      actualAction must beFailedTry
    }
  }

  trait Fixture extends Scope {
    val strikerActionBuilder = new StrikerActionBuilder()
  }
}
