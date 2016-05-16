package com.shpp.havrylenko.cs.task3;

 /*
 * Assignment3Part6   5/11/16, 23:50
 *
 * By Kyrylo Havrylenko
 *
 */

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Shows simple animation
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment3Part6 extends WindowProgram {

    public static final double PAUSE_TIME = 1000.0 / 50;

    @Override
    public void run() {
        GRect rect = new GRect(100, 100);
        rect.setFilled(true);
        rect.setColor(Color.green);
        add(rect);
        rect.scale(0.3);

        double x = rect.getX();
        double y = rect.getY();

        for(int i = 0; i < 5; i++) {
            rect.setLocation(x, y);
            for(int j = 0; j < 50; j++) {
                rect.move(rect.getX() + 1, rect.getY() + 1);
                pause(PAUSE_TIME);
            }
        }
    }
}
