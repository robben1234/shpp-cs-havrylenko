package com.shpp.havrylenko.cs.a1calculator;

 /*
 * Main   6/16/16, 21:00
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Arrays;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Main {
    public static void main(String[] args) {

        String[] in = {"1", "+", "2", "+", "(", "2", "+", "3.1", ")"};
        System.out.println("INPUT: ");
        Arrays.stream(in).forEach(System.out::print);
        System.out.println("\nOUTPUT: ");
        System.out.print(PolishNotationCalculator.calculate(ShuntingYard.toPostfix(in).split("\\s")));
        System.out.println("hey");
        System.out.println(PolishNotationCalculator.calculate(ShuntingYard.toPostfix(new String[]{"1"}).split("\\s")));
    }
}
