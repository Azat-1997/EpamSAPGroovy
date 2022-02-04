import second.Rectangle

public class Task6 {


    public static void main(String[] args) {
       // Make writing in files instead of printing
       Rectangle[] sample = Rectangle.getRandomRects(100, 1.0, 1000.0, 1.0, 1000.0)
       File filteredByLength = new File("output/6a_filteredByLength.csv")
       File filteredByPerimeter = new File("output/6b_filteredByPerimeter.csv")
       double tresholdLength = 400
       double tresholdPerimeter = 400

       // filter by length
       filteredByLength.withWriter("utf-8") { writer ->
       writer.writeLine "length,width"
       sample.findAll({it.getLength() > tresholdLength}).each({writer.writeLine "${it.getLength()},${it.getWidth()}"})
       }

       // filter by perimeter

       filteredByPerimeter.withWriter("utf-8") { writer ->
       writer.writeLine "length,width,perimeter"
       sample.findAll({it.getPerimeter() > tresholdPerimeter}).each({writer.writeLine "${it.getLength()},${it.getWidth()},${it.getPerimeter()}"})

       }

       println "find maximum area"
       Rectangle maxRect = sample.max({it.getArea()})
       println maxRect
       println "find index in sample of the element with the biggest area"
       println sample.findIndexOf{it.getArea() == maxRect.getArea()}

     }




}
