package XML

object XML extends App{

  def doChore(chore: String): String = chore match {
    case "clean dishes" => "scrub, dry"
    case "cook dinner" => "chop, sizzle"
    case _ => "whine, complain"
  }

  println(doChore("clean dishes" ))
  println(doChore("mow lawn" ))

  def factorial(n: Int): Int = n match {
    case 0 => 1
    case y if n < 0 => 0
    case x if x > 0 => factorial(n - 1) * n
  }
  println(factorial(-2))
  println(factorial(0))

  // match if starts with F/f
  val reg = """^(F|f)\w*""".r
  println(reg.findFirstIn("Fantastic"))
  println(reg.findFirstIn("not Fantastic"))


  //xml manipulation
  val movies = <movies>
    <movie>The Incredibles</movie>
    <movie>WALL E</movie>
    <short>Jack Jack Attack</short>
    <short>Geri's Game</short>
  </movies>

  (movies \ "_" ).foreach { movie =>
    movie match {
      case <movie>{movieName}</movie> =>
        println(movieName)
      case <short>{shortName}</short> =>
        println(shortName + " (short)" )
    }
  }
}
