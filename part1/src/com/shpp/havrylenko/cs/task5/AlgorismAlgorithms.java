package com.shpp.havrylenko.cs.task5;

 /*
 * Assignment5Part2   5/17/16, 16:57
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.cs.a.console.TextProgram;

/**
 * Addition algorithms
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class AlgorismAlgorithms extends TextProgram {

    private static int leftover = 0;

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     *
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {

        if (n1.length() != n2.length()) {

            if (n1.length() > n2.length()) {
                n2 = formatString(n2, n1.length());
            } else {
                n1 = formatString(n1, n2.length());
            }

        }

        return new StringBuilder(calculateSum(n1, n2)).reverse().toString();
    }

    /**
     * Formats strings to be of equal length
     * @param stringToFormat lesser string
     * @param biggerLength length of bigger string
     * @return String formatted lesser string
     */
    private String formatString(String stringToFormat, int biggerLength) {
        return String.format("%0" + (biggerLength - stringToFormat.length()) + "d%s",
                             0, stringToFormat);
    }

    /**
     * Returns leftover value and substruct 1 from it if its bigger than 0
     * @return int leftover
     */
    private int takeLeftoverValue() {

        return (leftover > 0)
                ? leftover--
                : leftover;
    }

    /**
     * Check if leftover needs to be incremented, increment if do
     * @param result int result of sum
     * @return int resulting number
     */
    private int checkForShiftingIn(int result) {

        if (result > 9) {
            leftover = 1;
            return result - 10;

        } else {
            return result;
        }
    }

    /**
     * Calculates the sum of prepared for this operation strings
     * @param n1 String first argument
     * @param n2 String second argument
     * @return String result of (numbers) n1 + n2
     */
    private String calculateSum(String n1, String n2) {

        String resultingString = "";

        for (int i = n1.length() - 1; i >= 0; i--) {

            int top = Integer.parseInt("" + n1.charAt(i));
            int down = Integer.parseInt("" + n2.charAt(i));

            top += takeLeftoverValue();

            int result = top + down;

            resultingString += checkForShiftingIn(result);

        }

        if (leftover > 0)
            resultingString += takeLeftoverValue();

        return resultingString;
    }
}
