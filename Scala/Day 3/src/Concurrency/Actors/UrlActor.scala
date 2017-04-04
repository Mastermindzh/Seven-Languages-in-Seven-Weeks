package Concurrency.Actors

import Concurrency.CaseClasses.{CalculateDuration, PrintSize}
import Concurrency.Loaders.{PageLinkLoader, PageLoader}
import akka.actor.Actor

class UrlActor extends Actor {
  def receive = {
    case PrintSize(url) =>
      println("Size for " + url + ": " + PageLoader.getPageSize(url))
      println("Number of links: " + PageLinkLoader.getLinks(url))
    case CalculateDuration(start) =>
      val end = System.nanoTime
      println("Method took " + (end - start) / 1000000000.0 + " seconds.")
      System.exit(1)
    case _ =>
      System.exit(1)
  }
}
