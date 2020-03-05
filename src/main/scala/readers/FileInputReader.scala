package readers

import scala.util.Try

class FileInputReader extends InputReader {

  override def read(): Try[List[String]] =
    Try(
      scala.io.Source.fromFile("src/main/scala/resource/InputFile").getLines().toList
    )
}
