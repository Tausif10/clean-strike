package readers

trait InputReader[T] {
  def read(path: String): T
}