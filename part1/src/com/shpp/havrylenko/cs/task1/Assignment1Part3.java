package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part3   4/26/16, 22:53
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.karel.KarelTheRobot;

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
        putBeeper();
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
        while(frontIsClear()) move();
        if(!beepersPresent()) putBeeper();
        while(frontIsClear() && !beepersPresent()) move();
        turnLeft();
        turnLeft();
        move();
    }
}
