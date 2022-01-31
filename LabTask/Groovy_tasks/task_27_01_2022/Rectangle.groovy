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


    public static void main(String[] args) {
       // Make writing in files instead of printing
       Rectangle[] sample = getRandomRects(100, 1.0, 1000.0, 1.0, 1000.0)
       File filteredByLength = new File("filteredByLength.csv")
       File filteredByPerimeter = new File("filteredByPerimeter.csv")
       // filter by length
       filteredByLength.withWriter("utf-8") { writer ->
       writer.writeLine "length,width"
       sample.findAll({it.getLength() > 400}).each({writer.writeLine "${it.getLength()},${it.getWidth()}"})
       }

       // filter by perimeter

       filteredByPerimeter.withWriter("utf-8") { writer ->
       writer.writeLine "length,width,perimeter"
       sample.findAll({it.getPerimeter() > 400}).each({writer.writeLine "${it.getLength()},${it.getWidth()},${it.getPerimeter()}"})
        
       }

       println "find maximum area"
       Rectangle maxRect = sample.max({it.getArea()})
       println maxRect
       println "find index in sample of the element with the biggest area"
       println sample.findIndexOf{it.getArea() == maxRect.getArea()}
      
     }

}
