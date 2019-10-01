import java.util.Observable;

public class HW03Model extends Observable {

    Int2DArray twoD;

    public HW03Model(int width, int height){
        twoD = new Int2DArray(width, height);
    }

    public int[][] getArray(){
        return this.twoD.getArray();
    }

    public int getRows(){
        return this.twoD.getRowSize();
    }

    public int getCols(){
        return this.twoD.getColSize();
    }

    public void randomize(){
        twoD.randomArray();
        setChanged();
        notifyObservers();
    }

    public void sortRows(){
        twoD.sortrow();
        setChanged();
        notifyObservers();
    }

    public void sortCols(){
        twoD.sortCol();
        setChanged();
        notifyObservers();
    }

    public void sortArray1(){
        // your method's declaration {
        long lTimeBefore = System.nanoTime();
        // your method's body

        sortCols();
        sortRows();

        long lTimeAfter = System.nanoTime();

        // print out duration:
        long lElapsedNanoSeconds = (lTimeAfter - lTimeBefore);
        System.out.println(lElapsedNanoSeconds);
    }
    public void sortArray2(){
        long lTimeBefore = System.nanoTime();
        sortRows();
        sortCols();
        long lTimeAfter = System.nanoTime();

        // print out duration:
        long lElapsedNanoSeconds = (lTimeAfter - lTimeBefore);
        System.out.println(lElapsedNanoSeconds);
    }

}
