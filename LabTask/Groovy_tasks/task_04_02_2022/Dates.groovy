public class Dates {


public static Date getRandomDate() {
     Random generator = new Random() 
     long timestamp = Math.abs(generator.nextLong() / 3e6)
     Date randomDate = new Date(timestamp)
     return randomDate


}

public static void main(String[] args) {
      File dates = new File("dates") 
      // create random dates
      dates.withWriter("utf-8") { 
      writer ->
      for(int i=0; i<1000; i++) {
 
           writer.writeLine getRandomDate().format("yyyy-MM-dd")

                           }

      }

      // convert yyyy-MM-dd to yyyy.MM.dd
      // upd: to dd.MM.yyyy
      File pointSep = new File("pointSeparated")
      pointSep.withWriter("utf-8") {
          writer ->
          dates.eachLine {it ->
                      def dt = it.split("-")
                      writer.writeLine "${dt[1]}.${dt[2]}.${dt[0]}"
                     }
      }       
 
       File central = new File("CentralEuropean")
       String pattern = "yyyy-MM-dd HH:mm:ss"
       central.withWriter("utf-8") {
          
        writer ->
       
        for(int j=0; j<1000; j++) {

            writer.writeLine  getRandomDate().format(pattern)

                           }
        }

      // read file in Central European Time (CET) and convert in GMT
      TimeZone cet = TimeZone.getTimeZone("CET")
      TimeZone gmt = TimeZone.getTimeZone("GMT")
      // delete shift of source time an add shift (offset) of new timezone
      def date
      File greenwich = new File("GreenwichMean")
      greenwich.withWriter("utf-8") {
           writer ->
           central.eachLine{
                   it ->
                   date = Date.parse(pattern, it)
                   writer.writeLine new Date(date.time - cet.rawOffset + gmt.rawOffset).format("yyyyMMdd HHmm")

                 }
         }

      }

    }

