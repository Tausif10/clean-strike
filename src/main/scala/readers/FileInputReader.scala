package readers

import scala.util.Try

class FileInputReader extends InputReader {

  override def read(path: String): Try[List[String]] =
    Try(
      scala.io.Source.fromFile(path).getLines().toList
    )
}
