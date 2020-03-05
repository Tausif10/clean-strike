package readers

import scala.util.Try

trait InputReader {
  def read(): Try[List[String]]
}