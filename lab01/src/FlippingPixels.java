import java.awt.Graphics;
import javax.swing.JFrame;


public class FlippingPixels extends JFrame{

    public static void main(String[] args){
        JFrame frame = new FlippingPixels();
        frame.setSize(200, 400);
        frame.setVisible(true);
    }

    public void paint(Graphics g){

        g.fillOval(50, 50, 50, 50);

    }


}