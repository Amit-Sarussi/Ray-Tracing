package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import Objects.RGB;

public class Canvas extends JComponent{
    
    int width;
    int height;
    RGB[][] frameData = new RGB[height][width];
    public Canvas(int w, int h){
        this.width = w;
        this.height = h;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                Rectangle2D.Double r = new Rectangle2D.Double(j, i, 1, 1);
                g2d.setColor(new Color(
                    (int) (this.frameData[i][j].getR()*255),
                    (int) (this.frameData[i][j].getG()*255),
                    (int) (this.frameData[i][j].getB()*255)
                ));
                g2d.fill(r);
            }
        }
    }

    public void setFrame(RGB[][] frame){
        this.frameData = frame;
        repaint();
    }
}
