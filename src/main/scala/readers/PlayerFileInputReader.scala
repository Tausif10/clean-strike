package readers

import java.nio.file.Path

import models.Player
import transformers.PlayerBuilder

import scala.util.{Failure, Success, Try}

class PlayerFileInputReader(playerBuilder: PlayerBuilder) extends PlayerReader {
  override def read(path: String): List[Player] = {
    Try(
      scala.io.Source.fromFile(path).getLines().filter(_.nonEmpty).toList
    ) match {
      case Success(playersName) =>playerBuilder.build(playersName)
      case Failure(exception) => List.empty[Player]
    }
  }
}
