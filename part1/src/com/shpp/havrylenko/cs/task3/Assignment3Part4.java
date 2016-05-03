package com.shpp.havrylenko.cs.task3;

 /*
 * Assignment3Part4   5/4/16, 00:00
 *
 * By Kyrylo Havrylenko
 *
 */

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Draws pyramid on the bottom of the window
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment3Part4 extends WindowProgram {
    public static final int BRICK_HEIGHT = 10;
    public static final int BRICK_WIDTH = 40;
    public static final int BRICKS_IN_BASE = 10;
    public static final int OFFSET = 10;

    @Override
    public void run() {
        // get bottom of the window
        int startingY = getHeight();

        for(int j = BRICKS_IN_BASE, rowsFromStart = 0; j > 0; j--, rowsFromStart++) {

            // starting X and Y points for current iteration
            int curX = getWidth() / 2 - (BRICK_WIDTH * j + OFFSET * j) / 2;
            int curY = startingY - BRICK_HEIGHT - OFFSET * rowsFromStart - BRICK_HEIGHT * rowsFromStart;

            for(int i = 0; i < j; i++) {
                GRect brick = new GRect(curX + BRICK_WIDTH * i + OFFSET * i, curY, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setColor(Color.BLACK);
                add(brick);
            }
        }
    }
}
