import groovy.xml.*
import second.Rectangle

public class RectangleMap{
  // Remark: maybe make inheritance like "RectangleMap extends Rectangle"?

     
   static HashMap<Integer, Rectangle> generateRectMap(int sample, double minWidth,
                          double maxWidth, double minLength,
                          double maxLength) {

      Random generator = new Random()
      HashMap<Integer, Rectangle> rects = new HashMap<>()
  
      for(int i=0; i<sample; i++) {

           rects.put(Integer.valueOf(i),
                     new Rectangle(generator.nextDouble() * Math.abs(maxWidth - minWidth) + minWidth,
                                   generator.nextDouble() * Math.abs(maxLength - minLength) + minLength)
                    )
      }
  
    return rects

    }

   public static void main(String[] args) {

      def rectMap = generateRectMap(100, 1.0, 1000.0, 1.0, 1000.0)
      File longRectMap = new File("output/7a_filteredByLengthMap.csv")
      File perimeterRectMap = new File("output/7b_filteredByPerimeterMap.csv")
      double tresholdLength = 400
      double tresholdPerimeter = 400


      // filter by length
      longRectMap.withWriter("utf-8") { writer ->
           writer.writeLine "id,length,width"
           rectMap.findAll({it.value.getLength() > tresholdLength}).each({
                                writer.writeLine "${it.key},${it.value.getLength()},${it.value.getWidth()}"
                                           })
      
       }

      // filter by perimeter
      perimeterRectMap.withWriter("utf-8") { writer ->
          writer.writeLine "id,length,width,perimeter"
          rectMap.findAll({it.value.getPerimeter() > tresholdPerimeter}).each({
                           writer.writeLine "${it.key},${it.value.getLength()},${it.value.getWidth()},${it.value.getPerimeter()}"
                                      })


      }

      // find maximum area
      Rectangle bigRect = rectMap.values().max({it.getArea()})
      println "The biggest rectangle"
      println bigRect
      println "key of rectangle and rectangle itself which is equals to the biggest one"
      println rectMap.find({it.value.getArea() == bigRect.getArea()})
      
      // Generate xml with StringBuilder
      StringBuilder builder = new StringBuilder()
      // start from root
      builder.append("<root>\n")
      rectMap.each({
                   builder.append("  <element id=\"${it.key}\">\n")
                   builder.append("    <width>${it.value.getWidth()}</width>\n")
                   builder.append("    <length>${it.value.getLength()}</length>\n") 
                   builder.append("  </element>\n")
                   })

      // add closed-tag
      builder.append("</root>") 
      // make String from builder for writing     
      String xmlString = builder.toString()
      File xmlByStringBuilder = new File("output/8a_xmlByStringBuilder.xml")
      xmlByStringBuilder.withWriter("utf-8") {writer -> writer.writeLine xmlString}



      // Generate xml with MarkupBuilder
      def xmlWriter = new StringWriter()
      def xml = new MarkupBuilder(xmlWriter)   
      xml.root{                    
                     // if default alias 'it' is used then Groovy throws null pointer exception (I don't understand why it's happening)
                     rectMap.each({rect ->
                         element(id: "${rect.key}") {
                             width "${rect.value.getWidth()}"
                             length "${rect.value.getLength()}"

                                        }                                
                                 })
                  
             }
       // Read xmlString in file
       File xmlByMarkup = new File("output/8b_xmlByMarkup.xml")
       
       xmlByMarkup.withWriter("utf-8") {writer -> writer.writeLine xmlWriter.toString()}
  
       

   }

}
