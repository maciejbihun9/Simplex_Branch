package model;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

/**
 * Created by MaciekBihun on 2016-05-26.
 */
public class OvalComponent extends JComponent {

    private static int width = 50;
    private static int height = 50;

    private int left;
    private int up;
    public OvalComponent(int left, int up){
        this.left = left;
        this.up = up;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(20, 20, width, height);
            g.drawLine(20, 20, 50, 50);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }

}
