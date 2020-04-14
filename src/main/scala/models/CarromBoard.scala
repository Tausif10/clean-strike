package models

case class CarromBoard(blackCoin: Int = 9, readCoin: Int = 1, striker: Striker = Striker()) {

  def pocketBlackCoin(): CarromBoard = {
    if (blackCoin >= 1) this.copy(blackCoin = blackCoin - 1) else this
  }

  def pocketReadCoin(): CarromBoard = {
    if (readCoin == 1) this.copy(readCoin = readCoin - 1) else this
  }

  def takeCoinOut: CarromBoard = if(blackCoin != 0) this.pocketBlackCoin() else this.pocketReadCoin()

  def numOfCoinOnBoard: Int = blackCoin +readCoin
}
