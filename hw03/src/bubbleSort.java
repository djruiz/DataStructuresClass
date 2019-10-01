import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class bubbleSort {

    //input size to create random array of given size
    //numbers in array will range from 0 to the input size
    public static int[] createRandomArray(int size){
        int[] newArray = new int[size];

        Random random = new Random();

        for(int i = 0; i < size; i++){
            newArray[i] = random.nextInt(size);
        }
        return newArray;
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] sort(int[] array){
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for(int i = 0; i < n - 1; i++){
                if(array[i] > array[i + 1]){
                    //System.out.println("Switching: " + array[i] + " and " + array[i + 1]);
                    swap(array, i, i+1);
                    swapped = true;
                }
            }
            //System.out.println("Updated array: " + Arrays.toString(array));
        }while(swapped);
        return array;
    }


    public static void main(String[] args){
        int[] test = createRandomArray(10);
        System.out.println(Arrays.toString(test));
        sort(test);
        System.out.println(Arrays.toString(test));
    }
}
