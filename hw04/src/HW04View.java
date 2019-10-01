import javax.swing.*;
import java.awt.*;

public class HW04View extends JComponent {


    private JFrame aJFrame;
    private int width;
    private int height;


    public HW04View(int width, int height){
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

    public void drawPoint(int x, int y, int r, int g, int b){
        Graphics pGraphics = getGraphics();
        Color c;


        if(r > 255){
            r = 255;
        }
        else if(r < 0){
            r = 0;
        }

        if(b > 255){
            b = 255;
        }
        else if(b < 0){
            b = 0;
        }

        if(g > 255){
            g = 255;
        }
        else if(g < 0){
            g = 0;
        }

        c = new Color(r, g, b);

        pGraphics.setColor(c);
        pGraphics.drawOval(x, y, 1, 1);
        pGraphics.dispose();

    }

    public void clear(){
        Graphics g = this.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0 , width, height);
    }


    public static void main(String[] args){
        HW04View view = new HW04View(1200, 1200);
        try {
            Thread.sleep(100);
        }
        catch (Exception e){};
        for(int i = 0; i < 1000; i++) {
            view.drawPoint(i, i, 100, 100, 100);
        }
    }
}
