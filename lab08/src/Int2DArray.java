import java.util.Arrays;
import java.util.Random;

public class Int2DArray implements Int2DArrayADT {

    private int[][] array;
    private int rows;
    private int cols;

    public Int2DArray(int x, int y){
        this.array = new int[x][y];
        this.rows = x;
        this.cols = y;
        this.zeroArray();
    }

    public int[][] getArray(){
        return array;
    }


    public int[] getRow(int row){
        return array[row];
    }

    public int[] getCol(int col){
        int[] colArray = new int[this.cols];

        for(int i = 0; i < this.rows; i++){
            colArray[i] = array[i][col];
        }
        return colArray;
    }

    public int get(int x, int y){
        return array[x][y];
    }

    public void set(int value, int x, int y){
        array[x][y] = value;
    }
    public void zeroArray() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                array[i][j] = 0;
            }
        }
    }

    //added for hw03
    public void randomArray() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                Random rand = new Random();
                //add 1 to ensure the value can be 255, add 255 to ensure that subtracting does not put the random number out of range
                //subtract 255 to make sure the negative range is possible
                int randValue = rand.nextInt(255 + 1 + 255) - 255;
                array[i][j] = randValue;
            }
        }
    }

    public void setCol(int fill, int col){
        for(int i = 0; i < this.rows; i++){
            array[i][col] = fill;
        }
    }
    public void setrow(int fill, int row){
        for(int i = 0; i < this.cols; i++){
            array[row][i] = fill;
        }
    }

    @Override
    public String toString() {
        String build = "[\n";

        for (int i = 0; i < this.rows; i++) {
            build += "[";
            for (int j = 0; j < this.cols; j++) {
                build += array[i][j];
                if(j != this.cols - 1) {
                    build += ",  ";
                }
            }
            build += "]\n";
        }
        build += "]\n";
        return build;
    }

    public static void main(String[] args){

            //test constructor and toString
            Int2DArray test = new Int2DArray(10, 10);
            System.out.println(test);

            //test set and get
            test.set(100, 0, 0);
            System.out.println(test);
            System.out.println(test.get(0, 0));

            //testing getrow() and getCol()
            System.out.println(Arrays.toString(test.getRow(0)));
            test.set(10, 3, 0);
            test.set(20, 2, 0);
            test.set(80, 5, 0);
            System.out.println(test);
            System.out.println(Arrays.toString(test.getCol(0)));

            test.setCol(2, 5);
            System.out.println(Arrays.toString(test.getCol(5)));

            test.setrow(1, 2);
            System.out.println(Arrays.toString(test.getRow(2)));

            System.out.println(test);
        }

}
