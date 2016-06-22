package com.shpp.havrylenko.cs.a2silhouettes;

import java.awt.*;

/**
 * Util class to save info about color of pixel
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class Argb {

    double alpha;
    double red;
    double blue;
    double green;

    private Argb(double alpha, double red, double green, double blue) {
        this.alpha = alpha;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    /**
     * Transforms {@code int} representation of pixel to {@code Argb}
     * @param pixel int
     * @return Argb
     */
    static Argb pixelToARGB(int pixel) {

        Color color = new Color(pixel);

        return new Argb(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());

    }

    @Override
    public String toString() {
        return "ARGB: " + alpha + ", " + red + ", " + green + ", " + blue;
    }
}
