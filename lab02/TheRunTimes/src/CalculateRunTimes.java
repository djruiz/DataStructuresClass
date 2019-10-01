public class CalculateRunTimes {
    public int countInstructions(int n){
        /*
        int sum2 = 0;
        /*
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){

                sum2++;
            }
        }
         */


        //System.out.println("n = " + n + " instructioncounter = " + sum2 + " (instructioncounter / n) = " + sum2 / n);
        int a[n], int b[n];
        for(int z = 0; z < a.length; z++){
            a[z] = ;
        }

        for(int z = 0; z < b.length; z++){
            b[z] = Math.random() * 10;
        }

        int m = 10;
        int d[0][0] = 0;
        for(int i = 1; i <= m; i ++)  {
            for(int j = 1; j <= n; j ++) {
                if(a[i] == b[j]) int c = 0;
                else int c = 1;
                d[i][j] = Math.min(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1]+c);
            }
        }


        return sum2;
    }

    public static void main(String[] args){
        CalculateRunTimes runtimes = new CalculateRunTimes();

        runtimes.countInstructions(10);
        runtimes.countInstructions(100);
        runtimes.countInstructions(1000);
        runtimes.countInstructions(10000);
        runtimes.countInstructions(100000);
        runtimes.countInstructions(1000000);

    }

}
