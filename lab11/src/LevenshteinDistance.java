import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LevenshteinDistance {

    private FileReader fr97;
    private FileReader fr201;
    private String str97;
    private String str201;


    public LevenshteinDistance() {
        try {
            fr97 = new FileReader("C:\\Users\\djrui\\Desktop\\C343djruiz\\homework\\hw04\\src\\pg97Truncated.txt");
            fr201 = new FileReader("C:\\Users\\djrui\\Desktop\\C343djruiz\\homework\\hw04\\src\\pg201Truncated.txt");
            str97 = "";
            str201 = "";
        } catch (FileNotFoundException e) {
        }
    }

    public void getStrings() {

        int i;
        try {
            while ((i = fr97.read()) != -1) {
                //System.out.print((char) i + " ");
                //System.out.print(i);
                if (i != 10) {
                    str97 += (char) i;

                }
            }
            while ((i = fr201.read()) != -1) {
                if (i != 10) {
                    str201 += (char) i;
                }
            }
        } catch (IOException e) {
        }
    }

    public String get201str() {
        return str201;
    }

    public String get97str() {
        return str97;
    }

    public int editDistance(String s1, String s2){
        int[][] d = new int[get97str().length()][get201str().length()];

        for(int i = 0; i <= get97str().length(); i++){
            d[i][0] = i;
        }
        for(int i = 0; i <= get201str().length(); i++){
            d[0][i] = i;
        }



    }

    public static void main(String[] args){
        LevenshteinDistance test = new LevenshteinDistance();
        test.getStrings();
        System.out.println(test.get97str());
        System.out.println("\n\n\n\n");
        System.out.println(test.get201str());
    }


}