/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author junio
 */
public class CustomInfiniteProgressBar extends JPanel {
    private static final long serialVersionUID = 223086939802246968L;
    private static final int DELAY = 100;
    private static final int MAX_AMOUNT = 100;
    private static final int SPACE = 30;
    private static final int NUMBER_OF_RECTS = 5;
    private Timer timer;
    private int value = 0, darkRect = 0;
 
    public CustomInfiniteProgressBar() {
        timer = new Timer(DELAY, new TimerActionListener());
        timer.start();
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
 
        // draw rectangular progress
        int paddingToFrameBorders = 10; //dummy values for position
        int smallerSize = 5, largerSize = 10; //dummy values for rectangle size
 
        int rectX, rectY;
        for (int i = 0; i < NUMBER_OF_RECTS; i++) {
            rectX = paddingToFrameBorders + i * SPACE;
            rectY = paddingToFrameBorders;
            if(darkRect == i) {
                g2d.fillRect(rectX, rectY, largerSize, largerSize);
            } else {
                // to center smaller rectangle respective to larger rectangle.
                rectX += (largerSize - smallerSize) / 2;
                rectY += (largerSize - smallerSize) / 2;
 
                g2d.drawRect(rectX, rectY, smallerSize, smallerSize);
            }
        }
    }
 
    /**
    * Action Listener of Timer.
    */
    private class TimerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            value++;
            if(value == MAX_AMOUNT) { // you can have some condition here to stop the timer.
                timer.stop();
            }
            darkRect = (value % NUMBER_OF_RECTS);
            repaint();
        }
    }
}
