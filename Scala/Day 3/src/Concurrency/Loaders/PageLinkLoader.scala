package Concurrency.Loaders

import scala.io.Source

object PageLinkLoader {

  //I stole this regex from somewhere for a previous project, don't remember where
  val hrefRegex = """<a\s+(?:[^>]*?\s+)?href=(["'])(.*?)\1""".r

  def getLinks(url: String) : Int = {
    val content = Source.fromURL(url)(io.Codec("ISO-8859-1")).mkString
    val test = hrefRegex.findAllIn(content).matchData.toList.map(_.group(2))

    test.size
  }

}
