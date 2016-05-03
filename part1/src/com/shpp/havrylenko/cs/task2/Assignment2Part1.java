package com.shpp.havrylenko.cs.task2;

 /*
 * Assignment2Part1   4/29/16, 19:02
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Scanner;

/**
 * Console sqrt
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment2Part1 {

    /**
     * Gets roots of quadratic equation
     * @param a
     * @param b
     * @param c
     * @return array of {@code Double} with roots
     */
    public static Double[] getRoots(Double a, Double b, Double c) {
        Double[] result = new Double[2];
        double D = (b * b) - (4 * a * c);
        if(D < 0) return null;
        else if(D == 0) return calc(a, b, D, false);
        else return calc(a, b, D, true);
    }

    /**
     * Calculates roots of qudratic equation for {@code getRoots}
     * @param a
     * @param b
     * @param mode 0 - one root, 1 - 2 roots
     * @return array of {@code Double} with roots
     */
    private static Double[] calc(Double a, Double b, Double D, boolean mode) {
        Double[] result = new Double[2];
        result[0] = (-b + Math.sqrt(D)) / 2 * a;
        if(mode) {
            result[1] = (-b - Math.sqrt(D)) / 2 * a;
        }
        return result;
    }

    public static void main(String[] args) {

        double[] myArgs = new double[3];

        Scanner in = new Scanner(System.in);
        int i = 0;
        for(char c = 'a'; c < 'd'; c++, i++) {
            System.out.print("Please enter " + c + ": ");
            myArgs[i] = in.nextDouble();
        }

        Double[] res = getRoots(myArgs[0], myArgs[1], myArgs[2]);
        if(res == null) System.out.println("There are no real roots");
        else if(res[1] == null) System.out.println("There is one root: " + res[0]);
        else System.out.println("There are two roots: " + res[0] + " and " + res[1]);



    }
}
