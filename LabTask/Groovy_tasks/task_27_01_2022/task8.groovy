import groovy.xml.*
import groovy.json.*
import second.Rectangle

public class Task8 {

public static void main(String[] args) {
      def rectMap = Rectangle.generateRectMap(100, 1.0, 1000.0, 1.0, 1000.0)
      File longRectMap = new File("output/7a_filteredByLengthMap.csv")
      File perimeterRectMap = new File("output/7b_filteredByPerimeterMap.csv")
      double tresholdLength = 400
      double tresholdPerimeter = 400
      
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
      File xmlByStringBuilder = new File("output/8a_xmlByStringBuilder.xml")
      xmlByStringBuilder.withWriter("utf-8") {writer -> writer.writeLine builder.toString()}
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
       // Let's create json
       // Pattern 1
       File pattern1 = new File("pattern1.json")
       def builder1 = new groovy.json.JsonBuilder()
       builder1 {

          elements (rectMap.collect {[id: it.key, length: it.value.getLength(), width: it.value.getWidth()]})

       }

      pattern1.withWriter("utf-8"){writer -> writer.writeLine builder1.toPrettyString()}

      //Pattern2
      File pattern2 = new File("pattern2.json")
      def builder2 = new groovy.json.JsonBuilder()
      
                     
      builder2 {
                        
                rectMap.collect {
                   rect ->
                   "element_${rect.key}" {
                              
                   id rect.key
                   length rect.value.getLength()
                   width rect.value.getWidth()
                         }
        

               } 
              
           }
          
      pattern2.withWriter("utf-8") {writer -> writer.writeLine builder2.toPrettyString()}
      
    }

 
}
