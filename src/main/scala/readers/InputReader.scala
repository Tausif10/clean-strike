package readers

import scala.util.Try

trait InputReader[T] {
  def read(path: String): Try[T]
}