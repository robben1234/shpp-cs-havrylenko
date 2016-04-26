package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part1   4/26/16, 21:47
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.karel.KarelTheRobot;

import static com.shpp.havrylenko.cs.task1.SmartKarell.moveTwice;
import static com.shpp.havrylenko.cs.task1.SmartKarell.turnRight;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {

        if(moveToPaper()) {
            if(pickPaper()) {
                moveHome();
            }
        }

    }

    public boolean moveToPaper() throws Exception {
        if(frontIsClear()) {
            moveTwice(this);
            turnRight(this);
            if(frontIsClear()) {
                move();
                turnLeft();
                if(frontIsClear()) {
                    moveTwice(this);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pickPaper() throws Exception {
        if(beepersPresent()) {
            pickBeeper();
            if(beepersInBag()) return true;
        }
        return false;
    }

    public boolean moveHome() throws Exception {

        turnLeft();
        turnLeft();
        moveTwice(this);
        moveTwice(this);
        turnRight(this);
        if(frontIsClear()) {
            move();
            turnRight(this);
            return true;
        }
        return false;
    }


}
