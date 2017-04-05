import java.io.{File, PrintWriter}

import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer
import scala.io.Source

object PageLinkLoader {

  //I stole this regex from somewhere for a previous project, don't remember where
  val hrefRegex = """<a\s+(?:[^>]*?\s+)?href=(["'])(.*?)\1""".r

  // merge two maps
  def mergeMap[A, B](ms: List[Map[A, B]])(f: (B, B) => B): Map[A, B] =
    (Map[A, B]() /: (for (m <- ms; kv <- m) yield kv)) { (a, kv) =>
      a + (if (a.contains(kv._1)) kv._1 -> f(a(kv._1), kv._2) else kv)
    }

  // run through all links on a page and add them to a list
  def getLinks(url: String, urls : Map[String, Int], visited : List[String] = List[String]()) : Map[String, Int] = {

    val content = Source.fromURL(url)(io.Codec("ISO-8859-1")).mkString
    val links = hrefRegex.findAllIn(content).matchData.toList.map(_.group(2).toString)

    // filter out / , # , http://url and anything else not starting with http:// or www.
    val filteredList = links.filter(
        l => l.startsWith("/") ||
        l.startsWith("#") ||
        l.startsWith(url) ||
      l.contains(url.replace(url.split("/").last, ""))
    )

    // create new mutable list and fill with visited
    val newVisited = scala.collection.mutable.ListBuffer.empty[String]
    newVisited ++= visited.filter(p => !p.equals(url))

    //add to newVisited
    filteredList.foreach(s => {
      if(!newVisited.contains(s)){
        newVisited += s
      }
    })

    // convert list to map, count occurrences
    val foldedLinks = filteredList.foldLeft(Map[String, Int]())((map, link : String) =>
      map + (link -> links.count(_.equals(link)))
    )

    if(newVisited.isEmpty){
      // return merged map
      mergeMap(List(urls, foldedLinks))((v1, v2) => v1 + v2)
    }else{
      getLinks(newVisited(0),mergeMap(List(urls, foldedLinks))((v1, v2) => v1 + v2), newVisited.toList)
    }
  }

}

object MyApp extends App{

  val urls = List(
    "http://servers.rickvanlieshout.com/scalatest"
  )

  def getSiteMap() = {

    var siteMap : Map[String, Int] = Map[String, Int]()

    for (url <- urls) {
      siteMap = PageLinkLoader.getLinks(url, siteMap)
      siteMap.foreach (x => println (x._1 + "-->" + x._2))
      createSiteMap(url, siteMap)
    }
  }

  // creates a sitemap, uses Java which is wicked.
  def createSiteMap(url: String, urls : Map[String, Int]) = {
    // start off with a priority of 1.0 (ranges from 1.0 to 0)
    var currentPriority = 1.0

    //sort descending
    val descUrls = ListMap(urls.toSeq.sortWith(_._2 > _._2):_*)

    // write to a file called "sitemap_for_domain.txt"
    val domainRegex = """^(?:https?:\/\/)?(?:[^@\/\n]+@)?(?:www\.)?([^:\/\n]+)""".r
    val pw = new PrintWriter(new File("sitemap_for_"+domainRegex.findAllIn(url).matchData.toList.map(_.group(1)).mkString+".txt" ))
    //write xml header
    pw.write("\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<urlset\n      xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n      xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9\n            http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\">")

    // for each url write an url block with priority, then decrease priority
    descUrls.foreach(s => {
        pw.write(
          "\n<url>\n  " +
            "<loc>" + s._1 + "</loc>\n" +
            "<priority>" + currentPriority + "</priority>" +
            "</url>"
        )
        // decrease priority if possible
        if(currentPriority >= 0.1){
          currentPriority -= 0.1
        }
      }
    )

    // write the closing statement for a sitemap
    pw.write("\n</urlset>")
    pw.close()
  }

  getSiteMap()

}