package myApp

/**
  Self-Study day 2, practical assignments:

  • Use foldLeft to compute the total size of a list of strings.

  • Write a Censor trait with a method that will replace the curse words
    Shoot and Darn with Pucky and Beans alternatives. Use a map to
    store the curse words and their alternatives.

  • Load the curse words and alternatives from a file.

*/

object MyApp extends App with Censor{

  // print combined size of a list of strings
  val list = List("one", "two" , "three")
  println(list.foldLeft(0)((sum, value) => sum + value.length))

  val strToCensor = "Shoot !, this darn thing won't work!"

  // censor using builtin map
  println(this.censorText(strToCensor))

  // censor using curseWords.txt file from resources folder
  println(this.censorTextUsingFile(strToCensor, "curseWords.txt"))

}


