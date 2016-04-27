package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part2   4/26/16, 22:05
 *
 * By Kyrylo Havrylenko
 *
 */

/**
 * Robot puts beepers on empty spots in colognes
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment1Part2 extends SmartKarell {

    public void run() throws Exception {

        turnLeft();
        while(true) {
            for(int i = 0; i < 5; i++) {
                if(!beepersPresent()) {
                    putBeeper();
                }
                if(frontIsClear()) move();

            }


            if(facingNorth()) {
                turnRight();
                if(facingEast() && frontIsBlocked()) {
                    break;
                }
                moveTwice();
                moveTwice();
                turnRight();

            } else {
                turnLeft();
                if(facingEast() && frontIsBlocked()) {
                    break;
                }
                moveTwice();
                moveTwice();
                turnLeft();

            }

        }

    }

}
