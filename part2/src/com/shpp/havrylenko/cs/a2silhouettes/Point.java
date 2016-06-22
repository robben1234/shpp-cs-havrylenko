package com.shpp.havrylenko.cs.a2silhouettes;

import java.awt.image.BufferedImage;

import static com.shpp.havrylenko.cs.a2silhouettes.Argb.pixelToARGB;

/**
 * Util class of pixel coordinates and color information {@code Argb}
 *
 * @author Kyrylo Havrylenko
 * @see Argb
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

    /**
     * Generates Point instance out of coordinates
     * @param x Int X coord
     * @param y Int Y coord
     * @param image BufferedImage where to take info about pixel
     * @return Point instance
     */
    static Point generatePointOutOfCoords(int x, int y, BufferedImage image) {

        int pixel = image.getRGB(x, y);

        return new Point(x, y, pixelToARGB(pixel));

    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " " + argb + "\n";
    }
}