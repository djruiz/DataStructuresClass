import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

public class lab14BinSort {

    public static Integer[] BinSort(Integer[] a){

        int max = a[0];
        for(int i = 1; i < a.length; i++){
            if(a[i] > max){
                max = a[i];
            }
        }

        ArrayList<LinkedList<Integer>> buckets = new ArrayList<LinkedList<Integer>>();
        for(int i = 0; i < max + 1; i++){
            buckets.add(new LinkedList<Integer>());
        }

        for(int i = 0; i < a.length; i++){
            buckets.get(a[i]).add(a[i]);
        }

        int counter = 0;
        for(int i = 0; i < buckets.size(); i++){
            while(!buckets.get(i).isEmpty()){
                a[counter] = buckets.get(i).pop();
                counter += 1;
            }

        }

        return a;
    }

    public static void main(String[] args){

        Integer[] test = {11, 20, 1, 15, 18};
        System.out.println(Arrays.toString(BinSort(test)));


        Integer[] test1 = {0,0,0,0,0,0,0,30,90,40,20,15,80,50,30,11, 20, 1, 15, 18};
        System.out.println(Arrays.toString(BinSort(test1)));




        Integer[] test3 = {0,0,0,0,0,0,0,30,90,40,20,15,80,50,30,11, 20, 1, 15, 18, 90, 20, 30, 20, 38, 48,50, 500, 2000, 200, 100, 123, 145};
        System.out.println(Arrays.toString(BinSort(test3)));
    }

}
