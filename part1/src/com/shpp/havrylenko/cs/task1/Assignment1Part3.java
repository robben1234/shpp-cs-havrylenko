package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part3   4/26/16, 22:53
 *
 * By Kyrylo Havrylenko
 *
 */

/**
 * Makes the robot find middle position
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment1Part3 extends SmartKarell {

    public void run() throws Exception {

        while(!beepersPresent()) putBeepers();
        move();
        while(beepersPresent()) {
            pickBeeper();
            if(frontIsClear()) move();
        }
        turnLeft();
        turnLeft();
        while(!beepersPresent()) move();
        move();
        while(beepersPresent()) {
            pickBeeper();
            if(frontIsClear()) move();
        }
    }

    public void putBeepers() throws Exception {
        if(!beepersPresent()) putBeeper();
        move();
        while(frontIsClear() && !beepersPresent()) {
            move();
        }
        if(!beepersPresent()) putBeeper();
        turnLeft();
        turnLeft();
        move();
    }
}
