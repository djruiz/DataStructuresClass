import java.util.Arrays;
import java.util.Random;

public class hw07 {


    public static int[] createRandomArray(int size){
        int[] newArray = new int[size];

        Random random = new Random();

        for(int i = 0; i < size; i++){
            newArray[i] = random.nextInt(size);
        }
        return newArray;
    }

    public static boolean subsetHelper(int[] n, int k, int subtract){
        if(k == 0){
            return true;
        }
        else if(subtract == 0){
            return false;
        }
        else{
            return subsetHelper(n, k - n[subtract], subtract - 1) || subsetHelper(n, k, subtract - 1);
        }
    }

    public static boolean subset(int[] n, int k){
        return subsetHelper(n, k, n.length-1);
    }

    public static boolean dynamicSubset(int[] n, int k){
        boolean[][] cache = new boolean[k + 1][n.length];

        for(int i = 0; i < k + 1; i++){
            for(int j = 0; j < n.length; j++){
                if(i == 0){
                    cache[i][j] = true;
                }
                else if(i - n[j] == 0){
                    cache[i][j] = true;
                }
                else if(j - 1 < 0){
                    cache[i][j] = false;
                }
                else if(cache[i][j - 1]){
                    cache[i][j] = true;
                }
                else if(i - n[j] < 0){
                    cache[i][j] = false;
                }

                else {
                    cache[i][j] = cache[i - n[j]][j - 1];
                }
            }
        }

        for(int i = 0; i < cache.length; i++){
            System.out.println(Arrays.toString(cache[i]));
        }
        return cache[k][n.length - 1];
    }




    public static void main(String[] args){
        int[] test = createRandomArray(20);
        System.out.println(Arrays.toString(test));
        if(subset(test, 1)){
            System.out.println("1 is a subset");
        }
        if(subset(test, 2)){
            System.out.println("2 is a subset");
        }
        if(subset(test, 3)){
            System.out.println("3 is a subset");
        }
        if(subset(test, 4)){
            System.out.println("4 is a subset");
        }
        if(subset(test, 5)){
            System.out.println("5 is a subset");
        }
        if(subset(test, 6)){
            System.out.println("6 is a subset");
        }
        if(subset(test, 7)){
            System.out.println("7 is a subset");
        }
        if(subset(test, 8)){
            System.out.println("8 is a subset");
        }
        if(subset(test, 9)){
            System.out.println("9 is a subset");
        }
        if(subset(test, 0)){
            System.out.println("0 is a subset");
        }
        if(subset(test, -1)){
            System.out.println("-1 is a subset");
        }
        else{
            System.out.println("-1 not a subset");
        }
        if(subset(test, 20)){
            System.out.println("20 is a subset");
        }

        System.out.println(dynamicSubset(test, 20));
    }



}
