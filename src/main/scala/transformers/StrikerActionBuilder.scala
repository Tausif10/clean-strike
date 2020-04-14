package transformers

import constant.ApplicationConstant._
import models.actions._

import scala.util.{Failure, Success, Try}

class StrikerActionBuilder {

  def build(inputs: List[String]): Try[List[Actions]] = {
    println("inputes ===== "+inputs)
    val s = convertToSeq(inputs.map(actions))
    println("build ====> "+s)
    s
  }

  private def actions(choice: String): Try[Actions] = {
    choice.trim.toLowerCase match {
      case STRIKE => Success(new Strike())
      case MULTI_STRIKE => Success(new Multi())
      case RED_STRIKE => Success(new Read())
      case STRIKER_STRIKE => Success(new Strikers())
      case DEFUNCT_COIN => Success(new DefunctCoin())
      case _ => Failure(throw new Exception("Invalid Input"))
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
