import second.Rectangle

public class Task7 {

public static void main(String[] args) {
      def rectMap = Rectangle.generateRectMap(100, 1.0, 1000.0, 1.0, 1000.0)
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


    }


}
