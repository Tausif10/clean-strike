package readers

import models.Player
import transformers.PlayerBuilder

import scala.util.Try

class PlayerFileInputReader(playerBuilder: PlayerBuilder) extends InputReader[List[Player]] {
  override def read(path: String): List[Player] = {
    Try(scala.io.Source.fromFile(path).getLines().filter(_.nonEmpty).toList).
      map(playerBuilder.build(_)).getOrElse(List.empty[Player])
  }
}
