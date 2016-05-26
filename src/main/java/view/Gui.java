package view;

import model.OvalComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by MaciekBihun on 2016-05-26.
 */
public class Gui {

    JLabel view;
    BufferedImage surface;
    Random random = new Random();
    static JFrame frame;

    private JPanel mainPanel;
    private JButton addOvalButton;

    public Gui() {
        surface = new BufferedImage(600,400,BufferedImage.TYPE_INT_RGB);
        view = new JLabel(new ImageIcon(surface));
        Graphics g = surface.getGraphics();

        frame.add(addOvalButton);
        addOvalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.setColor(Color.ORANGE);
                g.fillRect(0,0,600,400);
                g.setColor(Color.BLACK);
            }
        });
    }


    public static void main(String[] args)
    {
        Gui canvas = new Gui();
        JFrame frame = new JFrame();
        int vertexes = 0;
        // Change this next part later to be dynamic.
        vertexes = 10;
        int canvasSize = vertexes * vertexes;
        frame.setSize(canvasSize, canvasSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas.view);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
