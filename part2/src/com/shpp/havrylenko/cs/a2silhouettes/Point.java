package com.shpp.havrylenko.cs.a2silhouettes;

import java.awt.image.BufferedImage;

import static com.shpp.havrylenko.cs.a2silhouettes.Argb.pixelToARGB;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class Point {

    int x;
    int y;
    Argb argb;

    private Point(int x, int y, Argb argb) {
        this.x = x;
        this.y = y;
        this.argb = argb;
    }

    static Point generatePointOutCoords(int x, int y, BufferedImage image) {

        int pixel = image.getRGB(x, y);

        return new Point(x, y, pixelToARGB(pixel));

    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " " + argb + "\n";
    }
}