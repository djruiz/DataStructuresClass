import java.util.Observable;

public class HW03Model extends Observable {

    Int2DArray twoD;

    public HW03Model(int width, int height){
        twoD = new Int2DArray(width, height);
    }

    public int[][] getArray(){
        this.twoD.getArray();
    }

    public void randomize(){
        twoD.randomArray();
    }

    public void sortRows(){
        notifyObservers();
    }

    public void sortCols(){
        notifyObservers();
    }

}
