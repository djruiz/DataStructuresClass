public class HammingDistance {

    public void compareHamming(int n){


        //generate two random DNA sequences of N length
        String dna1 = rndDNAGenerator.nextDNA(n);
        String dna2 = rndDNAGenerator.nextDNA(n);

        RandomChunkOfDNA rndDNAGenerator = new RandomChunkOfDNA();
        int hamming = 0;

        for(int i = 0; i < dna1.length(); i++){
            if(!(dna1.charAt(i) == dna2.charAt(i))){
                hamming += 1;
            }
        }
        System.out.println(dna1);
        System.out.println(dna2);
        System.out.println(hamming);
    }

    public static void main(String[] args){
        HammingDistance test = new HammingDistance();
        test.compareHamming(4);
        test.compareHamming(5);

    }

}
