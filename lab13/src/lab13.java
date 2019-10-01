import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

public class lab13<E extends Comparable<E>>{



    public int binarySearch(E[] sortedArray, E k){
        int l = -1;
        int r = sortedArray.length;
        while(l + 1 != r){
            int i = (l + r)/2;
            if(k.compareTo(sortedArray[i]) == 0){
                return i;
            }
            else if(k.compareTo(sortedArray[i]) < 0){
                r = i;
            }
            else{
                l = i;
            }
        }
        return sortedArray.length;
    }
    public static void main(String[] args) {




        Integer[] intArray = new Integer[100];
        for (int i = 0; i < intArray.length; i++) {
            Integer j = new Integer(i);
            intArray[i] = j;
        }
        lab13 testing = new lab13();
        System.out.println(testing.binarySearch(intArray, 50));

        //StringBuilder build = new StringBuilder();
        String[] strArray = new String[26];
        for (int i = 0; i < strArray.length; i++) {
            /*
            StringBuilder build = new StringBuilder();
            char test = (char) i;
            System.out.println(test);
            build.append(test);
            System.out.println(build.toString());
            String temp = build.toString();
            strArray[i - 97] = temp;
            */
            strArray[i] = (char)((int) 'a' + i) + "";
        }

        lab13<String> testing1 = new lab13<>();
        System.out.println("strArray");
        System.out.println(Arrays.toString(strArray));
        System.out.println(testing1.binarySearch(strArray, "a"));

        BigDecimal[] bdArray = new BigDecimal[10];
        bdArray[0] = new BigDecimal(0.5);
        bdArray[1] = new BigDecimal(0.8);
        bdArray[2] = new BigDecimal(1.5);
        bdArray[3] = new BigDecimal(2.5);
        bdArray[4] = new BigDecimal(3.5);
        bdArray[5] = new BigDecimal(4.5);
        bdArray[6] = new BigDecimal(5.5);
        bdArray[7] = new BigDecimal(6.5);
        bdArray[8] = new BigDecimal(7.5);
        bdArray[9] = new BigDecimal(9.5);
        System.out.println(Arrays.toString(bdArray));
        BigDecimal count = new BigDecimal(0.5);
        for (int i = 0; i < bdArray.length; i++) {
            BigDecimal add = new BigDecimal(0.5);
            bdArray[i] = bdArray[i].add(add);
        }
        System.out.println(bdArray);
        //
        System.out.println(testing.binarySearch(bdArray, new BigDecimal(2.5)));

    }
}
