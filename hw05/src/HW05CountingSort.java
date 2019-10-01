import java.util.Arrays;
import java.util.Random;

public class HW05CountingSort {

    public int[] countingSort(int[] array) {

        System.out.println("Original Array");
        System.out.println(Arrays.toString(array));
        //find the max value of the array
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        //initialize the array size to the max value
        int[] new_array = new int[max + 1];

        //initialize the new array
        for (int i = 0; i < max; i++) {
            new_array[i] = 0;
        }

        //fill out new array making indexes represent values from array and their values
        //representing their occurences in array
        for (int i = 0; i < array.length; i++) {
            int valueAt = array[i];
            new_array[valueAt] += 1;
        }
        System.out.println("Array matching keys(values from first array) to their occureences");
        System.out.println(Arrays.toString(new_array));

        //System.out.println(Arrays.toString(new_array));

        //calculate starting index in B
        for (int i = 1; i < new_array.length; i++) {
            new_array[i] += new_array[i - 1];
        }
        System.out.println("Updated intermediate array that shows where we insert into our final returned array");
        System.out.println(Arrays.toString(new_array));

        int[] finalArray = new int[array.length];


        for(int i = array.length - 1; i > -1; i--){
            int current = array[i];
            //System.out.println(Arrays.toString(array));
            System.out.print(current);
            finalArray[--new_array[current]] = current;
        }


        //go backwards through original array and use new_array as a look up table


        System.out.println("Sorted Array");
        System.out.println(Arrays.toString(finalArray));
        return finalArray;
    }

    public static int[] createRandomArray(int size){
        int[] newArray = new int[size];

        Random random = new Random();

        for(int i = 0; i < size; i++){
            newArray[i] = random.nextInt(size);
        }

        return newArray;
    }

    public static void main(String[] args){
        HW05CountingSort sort = new HW05CountingSort();
        int[] test = createRandomArray(10);
        int[] test1 = createRandomArray(100);
        int[] test2 = createRandomArray(1000);
        int[] test3 = createRandomArray(10000);
        int[] test4 = createRandomArray(100000);
        int[] test5 = createRandomArray(50000000);
        int[] test6 = createRandomArray(100000000);

        //int[] sorted = sort.countingSort(test);
        //int[] sorted1 = sort.countingSort(test1);
        //int[] sorted2 = sort.countingSort(test2);
        //int[] sorted3 = sort.countingSort(test3);
        //int[] sorted4 = sort.countingSort(test4);
        int[] sorted5 = sort.countingSort(test5);

    }
}
