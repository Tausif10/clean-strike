package builders

import carrom.actions._
import constant.ApplicationConstant._

import scala.util.{Failure, Success, Try}

class StrikerActionBuilder {

  def build(inputs: List[String]): Try[List[Actions]] = {
    convertToSeq(inputs.map(actions))
  }

  private def actions(choice: String): Try[Actions] = {
    choice.trim.toLowerCase match {
      case STRIKE => Success(Strike)
      case MULTI_STRIKE => Success(MultiStrike)
      case RED_STRIKE => Success(RedStrike)
      case STRIKER_STRIKE => Success(StrikersStrike)
      case DEFUNCT_COIN => Success(DefunctCoin)
      case _ => Failure(new Exception("Invalid Input"))
    }
  }

  private def convertToSeq(strikersAction: List[Try[Actions]],
                           result: Try[List[Actions]] = Success(List.empty)): Try[List[Actions]] = {
    strikersAction match {
      case Nil => result
      case head :: _ if head.isFailure => Failure(head.failed.get)
      case head :: rest => convertToSeq(rest, Success(result.get :+ head.get))
    }
  }

}
