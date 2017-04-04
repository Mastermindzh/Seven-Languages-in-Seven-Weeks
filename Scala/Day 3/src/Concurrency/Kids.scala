package Concurrency


import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class Kid extends Actor {
  def receive = {
    case "Poke" =>
      println("Ow... says " + this.self.path.name)
      println("Quit it... says " + this.self.path.name)
    case "Feed" =>
      println("Gurgle... says " + this.self.path.name)
      println("Burp...  says " + this.self.path.name)
    case _ =>
      Thread.sleep(1000)
      println("D'oh! says " + this.self.path.name)
  }
}

object Kids extends App {

  val system = ActorSystem("KidSystem")
  val lisa = system.actorOf(Props(new Kid), name = "Lisa")
  val bart = system.actorOf(Props(new Kid), name = "Bart")
  val homer = system.actorOf(Props(new Kid), name = "Homer")


  homer ! "?"
  homer ! "Poke"

  bart ! "Poke"
  lisa ! "Poke"

  bart ! "Feed"
  lisa ! "Feed"

}



