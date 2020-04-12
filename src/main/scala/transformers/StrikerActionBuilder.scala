package transformers

import constant.ApplicationConstant._
import models.actions._

import scala.util.{Failure, Success, Try}

class StrikerActionBuilder {

  def build(inputs: List[String]): Try[List[StrikeActions]] = {
    convertToSeq(inputs.map(actions))
  }

  private def actions(choice: String): Try[StrikeActions] = {
    choice.trim.toLowerCase match {
      case STRIKE => Success(new Strike())
      case MULTI_STRIKE => Success(new MultiStrike())
      case RED_STRIKE => Success(new ReadStrike())
      case STRIKER_STRIKE => Success(new StrikersStrike())
      case DEFUNCT_COIN => Success(new DefunctCoin())
      case _ => Failure(throw new Exception("Invalid Input"))
    }
  }

  private def convertToSeq(strikersAction: List[Try[StrikeActions]],
                           result: Try[List[StrikeActions]] = Success(List.empty)): Try[List[StrikeActions]] = {
    strikersAction match {
      case Nil => result
      case head :: _ if head.isFailure => Failure(head.failed.get)
      case head :: rest => convertToSeq(rest, Success(result.get :+ head.get))
    }
  }

}
