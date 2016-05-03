package com.shpp.havrylenko.cs.task2;

 /*
 * Assignment2Part6   5/3/16, 17:06
 *
 * By Kyrylo Havrylenko
 *
 */

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Draws caterpillar
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment2Part6 extends WindowProgram {
    public static final int NUM_OF_OVALS = 6;
    public static final Color MAIN_COLOR = Color.GREEN;
    public static final Color BORDER_COLOR = Color.RED;
    public static final double OVAL_SIZE = 100;

    @Override
    public void run() {

        boolean isOdd = false;

        for(int i = 0; i < NUM_OF_OVALS; i++) {
            double curX = i * (OVAL_SIZE / 1.5);
            double curY = isOdd ? 0 : (OVAL_SIZE / 2);
            GOval oval = new GOval(0 + curX, curY, OVAL_SIZE, OVAL_SIZE);
            oval.setFilled(true);
            oval.setColor(MAIN_COLOR);
            GOval border = new GOval(0 + curX, curY, OVAL_SIZE, OVAL_SIZE);
            border.setColor(BORDER_COLOR);
            add(oval);
            add(border);
            isOdd = !isOdd;
        }
    }
}
