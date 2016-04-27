package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part4   4/27/16, 21:51
 *
 * By Kyrylo Havrylenko
 *
 */

/**
 * Makes robot to create chess board
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment1Part4 extends SmartKarell {

    public void run() throws Exception {
        putBeepers();
        while(true) {
            if(facingEast()) {
                turnLeft();
                if(frontIsBlocked()) return;
                move();
                turnLeft();
                putBeepers();
            } else if(facingWest()) {
                turnRight();
                if(frontIsBlocked()) return;
                move();
                turnRight();
                putBeepers();
            }
        }
    }

    public void putBeepers() throws Exception {
        while(frontIsClear()) {
            if(!beepersPresent()) putBeeper();
            move();
            if(frontIsClear()) move();

        }


    }
}
