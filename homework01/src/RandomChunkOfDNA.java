

import java.util.Random;
import static java.lang.System.out;

public class RandomChunkOfDNA {

    // generate a random DNA sequence of length n:
    public String nextDNA(int n) {
        String lDNA = "";
        Random lRandomizer = new Random();

        for (int i = 0; i < n; i++) {
            int j = lRandomizer.nextInt(4);
            if (j == 0) lDNA += "A";
            else if (j == 1) lDNA += "T";
            else if (j == 2) lDNA += "C";
            else if (j == 3) lDNA += "G";
        }
        return lDNA;
    }


    // some client code for testing:
    public static void main(String[] argv) {
        RandomChunkOfDNA rndDNAGenerator = new RandomChunkOfDNA();
        String dna;
        for (int i = 10; i<=1000; i = i * 10) {
            out.println("");
            dna = rndDNAGenerator.nextDNA(i);
            out.println("a DNA sequence " + i + " characters long: " + dna);
        }


        //generate two random DNA sequences of N length
        String dna1 = rndDNAGenerator.nextDNA(3);
        String dna2 = rndDNAGenerator.nextDNA(3);

        int hamming = 0;

        for(int i = 0; i < dna1.length(); i++){
            if(!dna1.substring(i).equals(dna2.substring(i))){
                hamming += 1;
            }
        }
        System.out.println(dna1);
        System.out.println(dna2);
        System.out.println(hamming);
    }

} // end of class RandomChunkOfDNA