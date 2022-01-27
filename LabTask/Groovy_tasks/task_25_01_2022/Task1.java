public class Task1 {

    static int[] insertionSort(int[] array) {
      int tmp;
      for (int i=1; i < array.length; i++) {

          for (int j=0; j < i; j++) {

             if (array[j] > array[i]) {
                 tmp = array[i];
                 array[i] = array[j];
                 array[j] = tmp;
               }

             }
        }

        return array;

                    }



    public static void main(String[] args) {
        System.out.println("Start...");
        int[] array = {2, 1, 0, 9, 7, 9 , 2, 2};
        for(int a : insertionSort(array)) {
            
            System.out.print(a);
            System.out.print(" ");       
        
        }
        System.out.println();

     }


}
