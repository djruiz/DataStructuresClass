import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Observable;

public class HW04Model extends Observable{

    private int width;
    private int height;
    private FileReader fr97;
    private FileReader fr201;
    private String str97;
    private String str201;

    int[][] cache;
    private char[][] charCache;



    public HW04Model(int width, int height){
        try {
            fr97 = new FileReader("C:\\Users\\djrui\\Desktop\\C343djruiz\\homework\\hw04\\src\\pg97Truncated.txt");
            fr201 = new FileReader("C:\\Users\\djrui\\Desktop\\C343djruiz\\homework\\hw04\\src\\pg201Truncated.txt");
            str97 = "";
            str201 = "";
        }
        catch (FileNotFoundException e){}
        if(width < 1280 || width > 5120) throw new IllegalArgumentException("Width should be between 1280 and 5120");
        if(height < 1024 || height > 2880) throw new IllegalArgumentException("Height should be between 1024 and 2880");
        this.width = width;
        this.height = height;
    }

    public void getStrings(){

        int i;
        try {
            while ((i = fr97.read()) != -1) {
                //System.out.print((char) i + " ");
                //System.out.print(i);
                if(i != 10) {
                    str97 += (char) i;

                }
            }
            while ((i = fr201.read()) != -1) {
                if( i != 10) {
                    str201 += (char) i;
                }
            }
        }
        catch (IOException e){}
    }

    public String get201str(){
        return str201;
    }

    public String get97str(){
        return str97;
    }

    public int[] cacheLine(int i){
        return cache[i];
    }

    public char[] charCacheLine(int j){
        return charCache[j];
    }

    //private String s1;
    //private String s2;

    public String aString(){
        return str97;
    }

    public String bString(){
        return str201;
    }



    public int editDistance(String s1, String s2){

        //aString();
        //bString();


        cache = new int[s1.length() + 1][s2.length() + 1];
        charCache = new char[s1.length() + 1][s2.length() + 1];


        for(int i = 0; i < s1.length() + 1; i++){
            cache[i][0] = i;
        }
        for(int i = 0; i < s2.length() + 1; i++){
            cache[0][i] = i;
        }

        int sub;
        int ins;
        int del;
        int min;
        char exp;
        for(int i = 1; i <= s1.length(); i++){
            String aStringret = "";
            for(int j = 1; j <= s2.length(); j++){


                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    sub = 0;
                }
                else{
                    sub = 1;
                    //System.out.println("sub = 1 rn");
                }
                del = cache[i - 1][j] + 1;
                ins = cache[i][j - 1] + 1;
                sub =  cache[i - 1][j - 1] + sub;
                min = Math.min(Math.min(del, ins), sub);

                if(sub == cache[i - 1][j - 1]){
                    charCache[i][j] = ' ';
                }
                if(min == del){
                    charCache[i][j] = 'D';
                }
                else if(min == ins){
                    charCache[i][j] = 'I';
                }
                else{
                    charCache[i][j] = 'S';
                }
                //min = Math.min(min, sub);
                //System.out.println(min);
                cache[i][j] = min;

            }
            //aString();
            //this.charCache[i] = charCache[i];
            setChanged();
            notifyObservers(i);
        }
        //System.out.println(Arrays.toString(charCacheLine(0, charCache)));

        //System.out.println(Arrays.deepToString(cache));
        //for(int i = 0; i < cache.length; i++){
        //    System.out.println(Arrays.toString(cache[i]));
        //}
        //System.out.println("\n\n");
        //for(int i = 0; i < cache.length; i++){
         //   System.out.println(Arrays.toString(charCache[i]));
        //}
        return cache[s1.length()][s2.length()];
    }




    public static void main(String[] args){
        HW04Model test = new HW04Model(2000, 2000);
        test.getStrings();
        System.out.println(test.get97str().length());
        System.out.println("\n\n\n\n");
        System.out.println(test.get201str().length());



        //edit distance testing
        System.out.println(test.editDistance("testing", "tastey"));
        //expected 4
    }


}
