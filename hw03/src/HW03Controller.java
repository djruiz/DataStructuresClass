import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class HW03Controller implements Observer {

    HW03Model model;
    HW03View view1;

    public HW03Controller(HW03View view, HW03Model model){
        this.model = model;
        this.model.addObserver(this);
        this.view1 = view;

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update runs");
        int[][] modelArray = model.getArray();
        //use method I havent written in view yet
        for(int i = 0; i < model.getRows(); i++){
            for(int j = 0; j < model.getCols(); j++){
                view1.drawPoint(i, j, modelArray[i][j]);
            }
        }
    }

    public static void main(String[] args){
        HW03Model model = new HW03Model(300, 300);
        HW03View view = new HW03View(300, 300);
        HW03Controller cont = new HW03Controller(view, model);

        model.randomize();
        model.sortArray1();
        model.sortArray2();
        System.out.println(Arrays.deepToString(model.getArray()));
        //for(int i = 0; i < model.getRows(); i++){
        //    System.out.println(Arrays.toString(model.getArray()[i]));
        //}

        //model.sortArray1();

    }
}
