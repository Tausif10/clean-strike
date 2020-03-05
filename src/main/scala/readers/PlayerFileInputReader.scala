package readers

import models.Player
import transformers.PlayerBuilder

import scala.util.{Failure, Success, Try}

class PlayerFileInputReader(playerBuilder: PlayerBuilder) extends PlayerReader {
  override def read(): List[Player] = {
    Try(
      scala.io.Source.fromFile("src/main/scala/resource/PlayersFile").getLines().filter(_.nonEmpty).toList
    ) match {
      case Success(playersName) =>playerBuilder.build(playersName)
      case Failure(exception) => List.empty[Player]
    }
  }
}
