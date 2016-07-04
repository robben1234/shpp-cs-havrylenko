package com.shpp.havrylenko.cs.a4fractals;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Generates Mandelbrot set from user input
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Mandelbrot {

    private static final int MAX_ITER = 255;
    private static final Complex MIN = new Complex(-0.7, 0.3);
    private static final double SCALE = 1.5;
    private static int picSize;

    /**
     * Calculates Mandelbrot set point for x, y
     * @param z0 starting point
     * @return RGB info about pixel
     */
    private static int calculateSet(Complex z0) {
        Complex z = z0;
        for (int i = 0; i < MAX_ITER; i++) {
            // если модуль z больше 2, значит последовательность стремится к inf
            if (z.abs() > 2.0)
                return i;
            z = z.mult(z).plus(z0);
        }
        return MAX_ITER;
    }

    /**
     * Calculates Complex number out of pixel coordinates
     * @param x int
     * @param y int
     * @return Complex ready to be argument of {@code calculateSet}
     */
    private static Complex calculateStart(int x, int y) {
        double real = MIN.real - SCALE / 2 + SCALE * x / picSize;
        double imag = MIN.imag - SCALE / 2 + SCALE * y / picSize;
        return new Complex(real, imag);
    }

    /**
     * Entry point of the app
     * @param args String[]
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        if (args.length != 4) {
            System.out.println("USAGE: java Mandelbrot <size> <filename>");
            System.out.println("<size>: size of resulting picture in pixels. Mind it'll be a square pic. Should be " +
                                       "less than 12001");
            System.out.println("<filename>: name of resulting file. Without extension.");
            System.out.println("Example: java Mandelbrot 5000 result");
            return;
        }

        picSize = Integer.parseInt(args[2]);
        BufferedImage pic = new BufferedImage(picSize, picSize, BufferedImage.TYPE_3BYTE_BGR);

        System.out.println("Generation of " + args[2] + "px fractal started. Wait...");

        for (int i = 0; i < picSize; i++) {
            for (int j = 0; j < picSize; j++) {
                int mandelbrotColor = calculateSet(calculateStart(i, j));
                pic.setRGB(i, picSize - 1 - j, mandelbrotColor);
            }
        }

        ImageIO.write(pic, "jpg", new File(args[3] + ".jpg"));
        System.out.println("Your image has been generated. Look for " + args[3] + ".jpg");
    }
}
