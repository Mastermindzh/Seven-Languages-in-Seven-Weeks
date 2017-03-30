// basic while loop
def whileLoop {
  var i = 1
  while(i <= 3) {
    println("while: " + i)
    i += 1
  }

  println();
}

whileLoop

// basic for loop
def forLoop {
  for(i <- 0 until 3) {
    println("for: " + i)
  }

  println();
}
forLoop

// ruby ruby ruby ruby
def rubyStyleForLoop {
  val array = Array(1, 2, 3)

  array.foreach { i =>
    println("arrow: " + i)
  }

  println();
}
rubyStyleForLoop