package com.shpp.havrylenko.cs.task2;

 /*
 * Assignment2Part5   5/3/16, 16:34
 *
 * By Kyrylo Havrylenko
 *
 */

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    @Override
    public void run() {
        double startingX = getWidth() / 2 - ((BOX_SIZE + BOX_SPACING) * NUM_COLS) / 2;
        double startingY = getHeight() / 2 - ((BOX_SIZE + BOX_SPACING) * NUM_ROWS) / 2;

        GRect[] rowBoxes = new GRect[NUM_COLS];

        for(int j = 0; j < NUM_ROWS; j++) {
            for(int i = 0; i < rowBoxes.length; i++) {
                rowBoxes[i] = new GRect(startingX + (i * BOX_SIZE + BOX_SPACING * i), startingY + (j * BOX_SIZE +
                        BOX_SPACING * j), BOX_SIZE, BOX_SIZE);
                rowBoxes[i].setFilled(true);
                rowBoxes[i].setColor(Color.BLACK);
                add(rowBoxes[i]);
            }
        }
    }
}
