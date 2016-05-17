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
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        String resultingString = "";
        int leftover = 0;

        if(n1.length() != n2.length()) {
            int neededLength = 0;
            if(n1.length() > n2.length()) {
                n2 = String.format("%0" + (n1.length() - n2.length()) + "d%s",
                                   0, n2);
            } else {
                n1 = String.format("%0" + (n2.length() - n1.length()) + "d%s",
                                   0, n1);
            }

            System.out.println("N1: " + n1);
            System.out.println("N2: " + n2);

        }

        for(int i = n1.length() - 1; i >= 0; i--) {

            int top = Integer.parseInt("" + n1.charAt(i));
            int down = Integer.parseInt("" + n2.charAt(i));

            if(leftover > 0) {
                top += leftover;
                leftover = 0;
            }

            int result = top + down;
            if(result > 9) {
                resultingString += result - 10;
                leftover = 1;
            } else {
                resultingString += result;
            }

        }

        if(leftover > 0) resultingString += leftover;

        return new StringBuilder(resultingString).reverse().toString();
    }
}
