package com.shpp.havrylenko.cs.task2;

 /*
 * Assignment2Part4   5/3/16, 16:04
 *
 * By Kyrylo Havrylenko
 *
 */

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Draws Belgium flag
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment2Part4 extends WindowProgram {
    public static final double FLAG_WIDTH = 300;
    public static final double FLAG_HEIGHT = 250;
    public static final double ONE_STRIPE_WIDTH = FLAG_WIDTH / 3;
    public static final double ONE_STRIPE_HEIGHT = FLAG_HEIGHT;

    enum Colors {
        FIRST_STRIPE_COLOR, SECOND_STRIPE_COLOR, THIRD_STRIPE_COLOR;

        public static Color toColor(int stripeNum) {
            switch(stripeNum) {
                case 0:
                    return Color.decode("#000000");
                case 1:
                    return Color.decode("#FFE936");
                case 2:
                    return Color.decode("#FF0F21");
                default:
                    return Color.WHITE;
            }
        }
    }

    @Override
    public void run() {

        double startingX = getWidth() / 2  - FLAG_WIDTH / 2;
        double startingY = getHeight() / 2 - FLAG_HEIGHT / 2;
        GRect[] stripes = new GRect[3];

        for(int i = 0; i < stripes.length; i++) {
            double offsetX = ONE_STRIPE_WIDTH * i;
            stripes[i] = new GRect(startingX + offsetX, startingY, ONE_STRIPE_WIDTH, ONE_STRIPE_HEIGHT);
            stripes[i].setFilled(true);
            stripes[i].setColor(Colors.toColor(i));
            add(stripes[i]);
        }

        double labelOffsetY = 15;
        double labelOffsetX = 170;
//        GLabel flagName = new GLabel("Flag of Belgium", stripes[0].getX(), stripes[0].getY() + stripes[0].getHeight() + labelOffsetY);
        GLabel flagName = new GLabel("Flag of Belgium", getWidth() - labelOffsetX, getHeight() - labelOffsetY);
        flagName.setFont("Verdana-21");
        add(flagName);
    }
}
