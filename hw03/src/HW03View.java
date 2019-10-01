import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HW03View extends JComponent{

    private int width;
    private int height;
    private JFrame aJFrame;

    public HW03View(int width, int height){
        this.width = width;
        this.height = height;
        // create JFrame where we (the main object in its JComponent identity) can paint:
        this.aJFrame = new JFrame();
        aJFrame.setTitle("HW03");
        aJFrame.add(this);
        aJFrame.setSize(width, height);
        aJFrame.setVisible( true );
        aJFrame.setLocationRelativeTo(null);
        aJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void drawPoint(int x, int y, int value){
        Graphics pGraphics = getGraphics();
        Color c;

        if(value < 0){
            c = new Color(Math.max(Math.abs(value), 255), 0, 0);
        }
        else{
            c = new Color(0, Math.min(Math.abs(value), 255), 0);
        }

        pGraphics.setColor(c);
        pGraphics.drawOval(x, y, 1, 1);
        pGraphics.dispose();

    }

    public void clear(){

        Graphics g = this.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0 , width, height);
    }

    // we override the JComponent's paintComponent() method, to do some drawing:
    //  and we receive the currently active graphics for our JComponent:

    public static void main(String[] args) {
        HW03View view = new HW03View(700, 700);
        view.drawPoint(20, 30, 200);
        view.drawPoint(50, 50, 10);
        //view.drawPoint(100, 500, 10);
        //view.drawPoint(50, 50, 10);
    }


}
