public class Task1 {

    public static void main(String[] args) {
        println("task1")
        int[] array = [2, 1, 0, 9, 7, 9 , 2, 2, 10, 15, 26, -11] 
        def closureSort = {arr -> 
                           int tmp
                           /* Work with left part and keep it sorted:
                              when we add new element in left part we compare it with all elements in left segment
                              and swap them if new element is smaller (sort by increasing).
                           */
                           for(int i=1; i < arr.length; i++) { 
 
                                  for(int j=0; j < i; j++) {
    
                                       if (arr[i] < arr[j]) {
                                             tmp = arr[i] 
                                             arr[i] = arr[j]
                                             arr[j] = tmp                                                   
                                             
                                            }  
                                    }

                                 }

                            println("sorted array is ${arr}")
                           
                          }
          println("source array is ${array}")
          closureSort(array)


        println("task2")
        def anotherClosure = {number -> 
            println("${number * number}")
            closureSort(array)
          }
        anotherClosure(5)
        println("task3")
        def twoParamsClosure = {number, closure -> 
                               closure(number)
             }

        twoParamsClosure(5, anotherClosure)
         
        println("task4")
        def squareClosure = {x -> x * x}
        println("\n    For cycle")
        for (int element : array) {

           println("       ${element} ^ 2 = ${squareClosure(element)}")

           }

        println("\n     Each cycle")

        array.each({it -> println("       ${it} ^ 2 = ${squareClosure(it)}") })

     

       println("\ntask5")
       /* if we want to compare by last digit we can use residuals of division by 10:
          all possible residuals 0, 1, 2 .. 9 (or n-1 where n is denominator) 
       */
       def compareLastDigits = {x, y -> 
                                      if(Math.abs(x) % 10 > Math.abs(y) % 10) {
                                              x  
                                      } else {
                                              y
                                         }
                                }
      
       println(compareLastDigits(20, -11)) // -11
       println(compareLastDigits(23, 25)) // 25
 }

}
