package second

public class Rectangle {

       private double width      
       private double length

       public Rectangle(double width, double length) {

           this.width = width
           this.length = length

        }

       public getLength() {
          return this.length
        }

       public getWidth() {
          return this.width
       }

       public double getArea() {
           
           return this.width * this.length
        }
       
       public double getPerimeter() {

           return (this.width + this.length) * 2

       } 
        
       @Override
       public String toString() {
       
          return "width: ${this.width}, length: ${this.length}"
          
       }

       public static Rectangle[] getRandomRects(int sample, double min_width, double max_width,
                                                double min_length, double max_length) {
              
              List<Rectangle> rectCollection = new ArrayList<>()
              Random rand = new Random()
              double randLength
              double randWidth

              for(int i=0; i < sample; i++) {

                  randLength  = rand.nextDouble() * (max_length - min_length) + min_length
                  randWidth   = rand.nextDouble() * (max_width - min_length) + min_length
                  rectCollection.add(new Rectangle(randWidth, randLength))                    
                      
              }

              return rectCollection

}


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
       // Make writing in files instead of printing
       Rectangle[] sample = getRandomRects(100, 1.0, 1000.0, 1.0, 1000.0)
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
