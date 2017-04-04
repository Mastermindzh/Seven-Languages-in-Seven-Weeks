package Concurrency

import Concurrency.Actors.UrlActor
import Concurrency.CaseClasses.{CalculateDuration, PrintSize}
import Concurrency.Loaders.PageLoader
import akka.actor.{ActorSystem, Props}

object MyApp extends App{

  val urls = List(
    "http://mastermindzh.com/",
    "http://linfinity.nl",
    "http://go8.nl",
    "http://servers.rickvanlieshout.com/"
  )

  def timeMethod(method: () => Unit) = {
    val start = System.nanoTime
    method()
    val end = System.nanoTime
    println("Method took " + (end - start) / 1000000000.0 + " seconds.")
  }

  def getPageSizeSequentially() = {
    for (url <- urls) {
      println("Size for " + url + ": " + PageLoader.getPageSize(url))
    }
  }

  def getPageSizeConcurrently() = {
    val start = System.nanoTime
    val system = ActorSystem("Sizer")
    val myActor = system.actorOf(Props(new UrlActor), name = "myActor")

    for (url <- urls) {
      myActor ! PrintSize(url)

      if (url == urls.last)
        myActor ! CalculateDuration(start)
    }
  }

  println("Sequential run:")
  timeMethod {
    getPageSizeSequentially
  }

  println("Concurrent run")
  getPageSizeConcurrently()

}
