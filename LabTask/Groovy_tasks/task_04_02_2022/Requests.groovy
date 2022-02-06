import groovy.xml.*
import java.text.SimpleDateFormat

public class Request {

  public static void main(String[] args) {

  // Odata
    
    def get = new URL("https://services.odata.org/V2/Northwind/Northwind.svc/Customers").openConnection()
    get.setRequestProperty("Content-Type", "application/xml; charset=utf-8")
    get.setRequestProperty("Method", "GET")
    get.setRequestProperty("Accept", "*/xml")    
    def getRC = get.getResponseCode()
    def raw = "<root><message>Empty xml</message></root>" 
    if (getRC.equals(200)) {
     raw = get.getInputStream().getText() 
    }
   
   def slurper = new XmlSlurper()
   def xml = slurper.parseText(raw)
   File customers = new File("CustomersID.txt")
   customers.withWriter("utf-8") {
         writer ->
           xml.entry.content.properties.each{writer.writeLine it.CustomerID.text()}
   }


    // rss
   def rss = new URL("http://rss.cnn.com/rss/edition_world.rss").openConnection()
   def rssRC = rss.getResponseCode()
   def rssRaw = """<html>
                         <head>Empty page</head>
                   </html>"""

   if (rssRC.equals(200)) {
   rssRaw = rss.getInputStream().getText()
                 }

   def page = slurper.parseText(rssRaw)
   def searchByWord = {word ->
   File related = new File("related_to_\'${word}\'")
   related.withWriter("utf-8") { writer ->
   page.channel.item.each{it ->
        
                        if (it.description.text().contains(word)) {
                          writer.writeLine it.pubDate.text()
                                    }

                          }
                              }

   File unique = new File("uniqe_dates_for_${word}")
   unique.withWriter("utf-8") {
         writer ->
         def dates = []
         def inputDateFormat = "EEE, dd MMM yyyy HH:mm:ss ZZZ"
         def fmt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss ZZZ", Locale.US)
         // after reformatting date become "russian"
         related.eachLine {dates << fmt.parse(it).format("EEE, dd MMM yyyy")}
         dates.toSet().each{writer.writeLine it}
        }
   }
   
   searchByWord("Putin")
   
       

  }

 }
