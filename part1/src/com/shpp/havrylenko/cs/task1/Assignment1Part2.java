package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part2   4/26/16, 22:05
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
public class Assignment1Part2 extends KarelTheRobot {

    public void run() throws Exception {

        turnLeft();
        while(true) {
            // TODO: nice break, because for now it's not working
            if(facingEast() && frontIsBlocked()) break;
            while(frontIsClear()) {
                if(beepersPresent()) move();
                else putBeeper();
            }
            if(facingNorth()) {
                goToOtherCologne(true);
            } else {
                goToOtherCologne(false);
            }

        }

    }


    /**
     *
     * @param dir direction. 0 - to bottom, 1 - to top
     */
    public void goToOtherCologne(boolean dir) throws Exception {
        if(dir) {
            turnRight(this);
            moveTwice(this);
            moveTwice(this);
            turnLeft();
            if(frontIsBlocked()) {
                turnLeft();
                turnLeft();
            } else {
                turnLeft();
                while(frontIsClear()) move();
                turnLeft();
                turnLeft();
            }

        } else {
            turnLeft();
            moveTwice(this);
            moveTwice(this);
            turnRight(this);
            if(frontIsBlocked()) {
                turnLeft();
                turnLeft();
            } else {
                turnRight(this);
                while(frontIsClear()) move();
                turnLeft();
                turnLeft();
            }
        }
    }

}
