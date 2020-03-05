package readers

import models.Player

trait PlayerReader {
  def read(): List[Player]
}
