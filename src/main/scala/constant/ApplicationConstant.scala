package constant

object ApplicationConstant {

  val MAX_SUCCESSIVE_FAIL_ALLOW = 3
  val MAX_FOUL_COUNTS_ALLOW = 3

  val PLAYER_NAME_FILE = "src/main/scala/resource/PlayersFile"
  val OUTPUT_FILE = "src/main/scala/writers/output.txt"
  val INPUT_FILE = "src/main/scala/resource/InputFile"

  val STRIKE = "strike"
  val MULTI_STRIKE = "multi strike"
  val RED_STRIKE = "red strike"
  val STRIKER_STRIKE = "striker strike"
  val DEFUNCT_COIN = "defunct coin"


  def strikeOptions() = {
    s"""****** Options ******
       |${STRIKE.capitalize}
       |${MULTI_STRIKE.capitalize}
       |${RED_STRIKE.capitalize}
       |${STRIKER_STRIKE.capitalize}
       |${DEFUNCT_COIN.capitalize}
       |Enter your choices as new line in file:[${INPUT_FILE}]
       |""".stripMargin
  }
}
