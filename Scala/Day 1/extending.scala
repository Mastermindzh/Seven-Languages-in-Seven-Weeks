import javax.management.BadAttributeValueExpException

/* Companion objects */
object Enterprise {
  def go = println("Go where no man has gone before" )
}

Enterprise.go


println

class Person(val name: String) {
  def talk(message: String) = println(name + " says " + message)
  def id(): String = name
}
class Employee(override val name: String, val number: String) extends Person(name) {

  override def talk(message: String) {
    println(name + " with number " + number + " says: " + message)
  }

  override def id():String = number.toString

}
val employee = new Employee("Spock" , "S02E24")
employee.talk("Computers make excellent and efficient servants, but I have no wish to serve under them." )

println

/* Traits */

trait Nice {

  def greet() = println("Hello my old friend!")

}
// extend person and add the nice trait
class NicePerson(override val name:String) extends Person(name) with Nice

val niceperson = new NicePerson("Mr. Nice")
niceperson.greet

println

trait Bad{

  def curse() = println("Object oriented languages are cool too!");

}
// multiple traits
class IndecisivePerson(override val name:String) extends Person(name) with Nice with Bad

val indecisiveperson = new IndecisivePerson("Mr. indecisive")
indecisiveperson.greet()
indecisiveperson.curse


