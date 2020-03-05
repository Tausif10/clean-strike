package constant

object ApplicationConstant {

  val MAX_SUCCESSIVE_FAIL_ALLOW = 3
  val MAX_FOUL_COUNTS_ALLOW = 3


  val STRIKE = "Strike"
  val MULTI_STRIKE = "Multi strike"
  val RED_STRIKE = "Red strike"
  val STRIKER_STRIKE = "Striker strike"
  val DEFUNCT_COIN = "Defunct coin"


  def strikeOptions() = {
    s"""****** Options ******
       |$STRIKE
       |$MULTI_STRIKE
       |$RED_STRIKE
       |$STRIKER_STRIKE
       |$DEFUNCT_COIN
       |Enter your choices as new line in file:[src/main/scala/resource/InputFile]
       |""".stripMargin
  }
}
