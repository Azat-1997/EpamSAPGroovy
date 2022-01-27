public class Task1 {
 
    static int[] insertionSort(int[] array) {
      int tmp
      int[] sortedArray = array.clone()

      for (int i=1; i < sortedArray.length; i++) {
  
          for (int j=0; j < i; j++) {

             if (sortedArray[j] > sortedArray[i]) {
                 tmp = sortedArray[i]
                 sortedArray[i] = sortedArray[j]
                 sortedArray[j] = tmp
               }    

             }         
         
        }

        return sortedArray

                    }

    public static void main(String[] args) {
        println("task1")
        int[] array = [2, 1, 0, 9, 7, 9 , 2, 2, 10, 15, 26, -11] 
        def closureSort = {println insertionSort(array)}
        closureSort.call()
        println("task2")
        def anotherClosure = {number -> 
            println("${number * number}")
            closureSort.call(array)
          }
        anotherClosure.call(5)
        println("task3")
        def twoParamsClosure = {number, closure -> 
                               closure.call(number)
             }

        twoParamsClosure.call(5, anotherClosure)
         
        println("task4")
        def squareClosure = {x -> x * x}
        println("\n    For cycle")
        for (int element : array) {

           println("       ${element} ^ 2 = ${squareClosure.call(element)}")

           }

        println("\n     Each cycle")

        array.each({it -> println("       ${it} ^ 2 = ${squareClosure.call(it)}") })

     

       println("\ntask5")
       def compareLastDigits = {x, y -> 
                                      if(Math.abs(x) % 10 > Math.abs(y) % 10) {
                                              x  
                                      } else {
                                              y
                                         }
                                }
      
       println(compareLastDigits.call(20, -11)) // -11
       println(compareLastDigits.call(23, 25)) // 25
 }

}
