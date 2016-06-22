package com.shpp.havrylenko.cs.a2silhouettes;

/**
 * <what class do>
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

    static Argb pixelToARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        return new Argb(alpha, red, green, blue);
    }

    @Override
    public String toString() {
        return "ARGB: " + alpha + ", " + red + ", " + green + ", " + blue;
    }
}
