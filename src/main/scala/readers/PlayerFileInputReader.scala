package readers

import models.Player
import builders.PlayerBuilder

import scala.util.Try

class PlayerFileInputReader(playerBuilder: PlayerBuilder) extends InputReader[List[Player]] {

  override def read(path: String): Try[List[Player]] = {
    Try(
      scala.io.Source.fromFile(path).getLines().filter(_.nonEmpty).toList
    ).map(playerBuilder.build(_))
  }
}
