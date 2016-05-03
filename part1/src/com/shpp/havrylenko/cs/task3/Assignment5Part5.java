package com.shpp.havrylenko.cs.task3;

 /*
 * Assignment5Part5   5/4/16, 00:25
 *
 * By Kyrylo Havrylenko
 *
 */

import java.util.Random;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment5Part5 {

    public static void main(String[] args) {
        int money = 1;
        int counter = 0;
        while(money < 20) {
            money += playPetersburgGame(money);
            counter++;
        }
        System.out.println("It took " + counter + " games to earn $20");
    }

    public static int playPetersburgGame(int moneyOnTable) {
        Random rand = new Random();
        int result = moneyOnTable;

        // true = heads, false = tails
        if(rand.nextBoolean()) {
           int iteration = playPetersburgGame(moneyOnTable * 2);
            result += iteration;
            System.out.println("This game, you earned $" + iteration);
        } else {
            System.out.println("This game, you earned $" + moneyOnTable);
            System.out.println("Your total is $" + result);
            return result;
        }
        return result;
    }
}
