package readers

import scala.util.Try

trait InputReader {
  def read(path: String): Try[List[String]]
}