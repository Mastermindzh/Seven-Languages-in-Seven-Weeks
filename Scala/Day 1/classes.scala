class PersonOneLiner(firstName: String, lastName: String);
new PersonOneLiner("Johnny", "Cash");

class Compass {
    val directions = List("north" , "east" , "south" , "west" )
    var bearing = 0

    println("Initial bearing: " + direction)

    // uses bearing and prints element in directions list
    def direction() = directions(bearing)

    def inform(turnDirection: String) {
      println("Turning " + turnDirection + ". Now bearing " + direction)
    }

    def turnRight() {
      bearing = (bearing + 1) % directions.size
      inform("right" )
    }

    def turnLeft() {
      bearing = (bearing + (directions.size - 1)) % directions.size
      inform("left")
    }
}

val myCompass = new Compass

myCompass.turnRight
myCompass.turnRight
myCompass.turnLeft
myCompass.turnLeft
myCompass.turnLeft


class Person(first_name: String) {
  println("Outer constructor" )
  def this(first_name: String, last_name: String) {
    this(first_name) // this is mandatory.
    println("Inner constructor" )
  }
  def talk() = println("Hi" )
}

val johnny = new Person("Johnny" )


val johnnyCash = new Person("Johnny" , "Cash" )

