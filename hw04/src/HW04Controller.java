import java.util.*;
import java.awt.*;

public class HW04Controller implements  Observer{

    private HW04Model model;
    private HW04View view;

    public HW04Model getModel(){
        return this.model;
    }

    public HW04Controller(int width, int height, HW04View view, HW04Model model){
        this.model = model; //HW04Model(width, height);
        this.model.addObserver(this);
        this.view = view; //new HW04View(width, height);
        model.getStrings();
        String topString = model.aString();
        String bottomString = model.bString();
        //System.out.println(bottomString.length());
        //System.out.println(topString.length());


        for (int i = 0; i < topString.length() - 1; i++) {
            char letter = topString.charAt(i);
            if (letter == ' ') {
                //System.out.println(" spcae char ");
                view.drawPoint(i, 0, 0, 0, 0);
            } else if (letter == 'a' || letter == 'A' || letter == 'e' || letter == 'E' ||
                    letter == 'i' || letter == 'I' || letter == 'o' || letter == 'O' ||
                    letter == 'u' || letter == 'U') {
                //System.out.println(" Vowel ");
                view.drawPoint(i, 0, 255, 255, 0);
            } else {
                view.drawPoint(i, 0, 255, 165, 0);
            }
        }


        for (int i = 0; i < bottomString.length(); i++) {
            char letter = bottomString.charAt(i);
            if (letter == ' ') {
                //System.out.println(" spcae char ");
                view.drawPoint(0, i, 0, 0, 0);
            } else if (letter == 'a' || letter == 'A' || letter == 'e' || letter == 'E' ||
                    letter == 'i' || letter == 'I' || letter == 'o' || letter == 'O' ||
                    letter == 'u' || letter == 'U') {
                //System.out.println(" Vowel ");
                view.drawPoint(0, i, 255, 255, 0);
            } else {
                view.drawPoint(0, i, 255, 165, 0);
            }
        }
    }

    //int y = 0;

    public void update(Observable o, Object arg) {
        //y++;
        //System.out.println(y);
        //System.out.println("Update runs");
        int i = (int) arg;
        System.out.println(i);
        //System.out.println(getModel().charChaceLine((int) arg - 1));
        //System.out.println(getModel().charCacheLine(i));
        char[] toDraw = getModel().charCacheLine(i);
        int[] findMin = getModel().cacheLine(i);
        int lowest = findMin[1];
        for(int j = 1; j < findMin.length; j++){
            if(findMin[j] < lowest){
                lowest = j;
            }
        }

        for(int j = 0; j < toDraw.length; j++){
            if(toDraw[j] == ' ') {
                view.drawPoint(j, i, 0, 128, 0);
            }
            else if(toDraw[j] == 'S') {
                view.drawPoint(j, i, 255, 192, 203);
            }
            else if(toDraw[j] == 'D') {
                view.drawPoint(j, i, 255, 0, 0);
            }
            else if(toDraw[j] == 'I') {
                view.drawPoint(j, i, 0, 0, 255);
            }
        }
        System.out.println(Arrays.toString(findMin));
        System.out.println(lowest);
        view.drawPoint(lowest, i, 255, 255, 255);
    }



    public static void main(String[] args){

        HW04Model model = new HW04Model(1300, 1300);
        HW04View view = new HW04View(1300, 1300);
        HW04Controller cont = new HW04Controller(1300, 1300, view, model);
        cont.getModel().getStrings();
        model.editDistance(cont.getModel().get97str(), cont.getModel().get201str());
    }



}

