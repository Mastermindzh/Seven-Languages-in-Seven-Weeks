package myApp

trait Censor {

  val curseWords = Map("shoot" -> "pucky", "darn" -> "beans")

  /**
    * Awesome function which will build a curseWords map from a file.
    * @param input string input
    * @param path curseWords file
    * @return clean string
    */
  def censorTextUsingFile(input: String, path: String) : String = {
    // neat little statement which will read a file from a path, get it's lines
    // for each line it will split on "=" and put the values in the map
    val myMap = io.Source.fromResource(path).getLines.foldLeft(Map[String, String]())((map, line) =>
      map + (line.split("=")(0) -> line.split("=")(1))
    )

    censorText(input, myMap)
  }

  /**
    * censors input text using the builtin curseWords map or @param map
    * @param input string to be censored
    * @param map optional, map of curse words
    * @return censored string
    */
  def censorText(input: String, map: Map[String, String] = curseWords) : String ={
    val words = input.split(" ")

    words.map(word => map.getOrElse(word.toLowerCase, word)).mkString(" ")

  }
}