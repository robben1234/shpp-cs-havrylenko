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
public class Assignment3Part5 {

    public static void main(String[] args) {
        int money = 0;
        int counter = 0;
        int prevmoney = 0;
        while(money < 20) {
            prevmoney = money;
            money += playPetersburgGame();
            System.out.println("This game, you earned $" + (money - prevmoney));
            System.out.println("Your total is $" + money);
            counter++;
        }
        System.out.println("It took " + counter + " games to earn $20");
    }

    public static int playPetersburgGame() {
        Random rand = new Random();
        int startMoney = 1;

        while(true) {
            // true = heads, false = tails
            if(rand.nextBoolean()) {
                startMoney *= 2;
            } else {
                return startMoney;
            }
        }
    }
}
