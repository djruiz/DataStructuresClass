import javax.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.Canvas;


public class FlippingPixels extends Canvas{

    public static void main(String[] args){
        JFrame frame = new JFrame("Lab 01");
        Canvas canvas = new FlippingPixels();
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true)
    }

    public void paint(Graphics g){

        g.fillOval(100, 100, 200, 200);

    }


}