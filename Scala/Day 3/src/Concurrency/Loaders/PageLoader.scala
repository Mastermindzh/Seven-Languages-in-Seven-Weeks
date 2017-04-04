package Concurrency.Loaders

import scala.io.Source

object PageLoader {
  def getPageSize(url: String) = Source.fromURL(url)(io.Codec("ISO-8859-1")).mkString.length
}

