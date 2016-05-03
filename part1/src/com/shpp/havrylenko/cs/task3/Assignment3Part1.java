package com.shpp.havrylenko.cs.task3;

 /*
 * Assignment3Part1   5/3/16, 17:27
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Scanner;

/**
 * Calculates numbers of good days in terms of health for user input
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment3Part1 {

    public static void main(String[] args) {
        final int daysInWeek = 7;
        Integer[] mins = new Integer[daysInWeek];
        Scanner in = new Scanner(System.in);
        for(int i = 0; i < daysInWeek; i++) {
            System.out.print("How many minutes did you do on day " + (i + 1) + "? ");
            mins[i] = in.nextInt();
        }
        System.out.println();
        int goodCardioDays = 0;
        int goodBloodDays = 0;
        for(int i = 0; i < daysInWeek; i++) {
            if(mins[i] >= 40) {
                goodCardioDays++;
                goodBloodDays++;
            } else if(mins[i] >= 30) {
                goodCardioDays++;
            }
        }
        System.out.println("Cardiovacular health:");
        if(goodCardioDays >= 5) {
            System.out.println("Great job! You've done enough exercise for cardiovacular health.");
        } else {
            System.out.println("You needed to train hard for at least " + (5 - goodCardioDays) + " more day(s) a " +
                                       "week!");
        }
        System.out.println("Blood pressure:");
        if(goodBloodDays >= 3) {
            System.out.println("Great job! You've done enough exercise for a low blood pressure.");
        } else {
            System.out.println("You needed to train hard for at least " + (3 - goodBloodDays) + " more day(s) a " +
                                       "week!");
        }

    }
}
