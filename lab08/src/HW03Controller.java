import java.util.Observable;
import java.util.Observer;

public class HW03Controller implements Observer {

    HW03Model model;

    public HW03Controller(int height, int width){
        this.model = new HW03Model(height, width);
        model.addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        model.getArray();
        //use method I havent written in view yet
    }

    public static void main(String[] args){
        HW03Model model = new HW03Model();
        HW03View view = new HW03View(1280, 1024);
    }
}
