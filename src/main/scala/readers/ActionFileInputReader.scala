package readers

import models.actions.StrikeActions
import transformers.StrikerActionBuilder

import scala.util.Try

class ActionFileInputReader(strikerActionBuilder: StrikerActionBuilder) extends InputReader[List[StrikeActions]] {

  override def read(path: String): Try[List[StrikeActions]] =
    Try(scala.io.Source.fromFile(path).getLines().toList)
      .flatMap(strikerActionBuilder.build)
}
