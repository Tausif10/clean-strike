package readers

import models.Player

trait PlayerReader {
  def read(path: String): List[Player]
}
