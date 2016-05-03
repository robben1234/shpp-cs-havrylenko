package com.shpp.havrylenko.cs.task3;

 /*
 * Assignment3Part3   5/3/16, 23:36
 *
 * By Kyrylo Havrylenko
 *
 */

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment3Part3 {

    public static void main(String[] args) {
        System.out.println(raiseToPower(2.0, 3));
        System.out.println(raiseToPower(0.5, -2));
    }

    /**
     * Raises {@code base} to power of {@code exponent}
     * @param base number to raise
     * @param exponent raise to power
     * @return {@code Double} value
     */
    private static double raiseToPower(double base, int exponent) {
        if(exponent == 0) {
            return 1;
        } else if(exponent > 0) {
            return base * raiseToPower(base, exponent - 1);
        } else {
            return raiseToPower(1 / base, -exponent);
        }

    }
}
