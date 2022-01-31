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
                     new Rectangle(generator().nextDouble() * Math.abs(maxWidth - minWidth) + minWidth,
                     generator().nextDouble() * Math.abs(maxLength - minLength) + minLength)
                    )

      }
  
    return rects

    }

   public static void main(String[] args) {


      def rectMap = generateRectMap(100, 1.0, 1000.0, 1.0, 1000.0)
 
      println rectMap
 
      // filter by length 
      
      
         
      // filter by perimeter
       
   }

}
