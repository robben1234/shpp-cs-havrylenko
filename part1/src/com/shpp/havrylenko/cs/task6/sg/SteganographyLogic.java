package com.shpp.havrylenko.cs.task6.sg;

import acm.graphics.GImage;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     *
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {

        int[][] pixelArray = source.getPixelArray();

        boolean[][] message = new boolean[pixelArray.length][pixelArray[0].length];

        for(int i = 0; i < pixelArray.length; i++) {
            for(int j = 0; j < pixelArray[0].length; j++) {
                message[i][j] = ((GImage.getRed(pixelArray[i][j]) % 2) != 0);
            }
        }
        return message;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     *
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] sourceImage = source.getPixelArray();
        int[][] secretImage = new int[sourceImage.length][sourceImage[0].length];

        for(int i = 0; i < sourceImage.length; i++) {
            for(int j = 0; j < sourceImage[0].length; j++) {
                if(message[i][j]) {
                    // нечетным red
                    secretImage[i][j] = makeItOdd(sourceImage[i][j]);

                } else {
                    // четным red
                    secretImage[i][j] = makeItEven(sourceImage[i][j]);
                }
            }
        }

        return new GImage(secretImage);
    }

    /**
     * Makes red part of pixel odd
     * @param pix pixel
     * @return new pixel
     */
    private static int makeItOdd(int pix) {
        int oldRedValue = GImage.getRed(pix);
        int newRedValue;

        if(oldRedValue % 2 == 1) {
            newRedValue = oldRedValue;
        } else {
            newRedValue = oldRedValue + 1;
        }

        return GImage.createRGBPixel(newRedValue,
                                     GImage.getGreen(pix),
                                     GImage.getBlue(pix),
                                     GImage.getAlpha(pix));
    }

    /**
     * Makes red part of pixel even
     * @param pix pixel
     * @return new pixel
     */
    private static int makeItEven(int pix) {
        int oldRedValue = GImage.getRed(pix);
        int newRedValue;

        if(oldRedValue % 2 == 0) {
            newRedValue = oldRedValue;
        } else {
            newRedValue = oldRedValue - 1;
        }

        return GImage.createRGBPixel(newRedValue,
                                     GImage.getGreen(pix),
                                     GImage.getBlue(pix),
                                     GImage.getAlpha(pix));
    }
}
