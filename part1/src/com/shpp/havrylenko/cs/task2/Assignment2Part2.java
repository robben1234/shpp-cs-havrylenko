package com.shpp.havrylenko.cs.task2;

 /*
 * Assignment2Part2   5/3/16, 15:20
 *
 * By Kyrylo Havrylenko
 *
 */

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Draws 4 JOval and 1 JRect
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;
    @Override
    public void run() {


        double circleWidth = getWidth() / 3;
        double circleHeight = getHeight() / 3;

        GOval[] circles = new GOval[4];
        circles[0] = new GOval(0, 0, circleWidth, circleHeight);
        circles[1] = new GOval(getWidth() - circleWidth, 0, circleWidth, circleHeight);
        circles[2] = new GOval(0, getHeight() - circleHeight, circleWidth, circleHeight);
        circles[3] = new GOval(getWidth() - circleWidth, getHeight() - circleHeight, circleWidth, circleHeight);

        for(GOval circle : circles) {
            circle.setFilled(true);
            circle.setColor(Color.BLACK);
        }

        for(GOval circle : circles) {
            add(circle);
        }

        double rectWidth = getWidth() / 1.5;
        double rectHeight = getHeight() / 1.7;

        GRect rect = new GRect(getWidth() / 2 - rectWidth / 2, getHeight() / 2 - rectHeight / 2, rectWidth, rectHeight);
        rect.setFilled(true);
        rect.setColor(Color.WHITE);
        add(rect);
    }
}
