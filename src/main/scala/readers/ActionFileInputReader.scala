package readers

import models.actions.Actions
import transformers.StrikerActionBuilder

import scala.util.Try

class ActionFileInputReader(strikerActionBuilder: StrikerActionBuilder) extends InputReader[List[Actions]] {

  override def read(path: String): Try[List[Actions]] =
    Try(scala.io.Source.fromFile(path).getLines().toList)
      .flatMap(strikerActionBuilder.build)
}
