//C343 Daniel Ruiz 5/14/19
public interface Int2DArrayADT<E>{



    int[] getRow(int row);
    int[] getCol(int col);
    int get(int x, int y);
    void set(int value, int x, int y);
    void zeroArray();
    void setCol(int fill[], int col);
    void setrow(int fill, int row);



}
