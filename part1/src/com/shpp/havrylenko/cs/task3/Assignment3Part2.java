package com.shpp.havrylenko.cs.task3;

 /*
 * Assignment3Part2   5/3/16, 18:35
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Scanner;

/**
 * Demonstrates Collatz conjecture on user input
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment3Part2 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int userInput = in.nextInt();
        System.out.println();
        conjecture(userInput);
    }

    /**
     * Recursively illustrates all the way down to 1 in Collatz conjecture
     * @param input {@code int} number
     */
    public static void conjecture(int input) {
        if(input % 2 == 0) {
            System.out.print(input + " is even so I take half: ");
            input /= 2;
            System.out.println(input);
        } else {
            System.out.print(input + " is odd so I make 3n + 1: ");
            input = input * 3 + 1;
            System.out.println(input);
        }
        if(input != 1) conjecture(input);
    }
}
