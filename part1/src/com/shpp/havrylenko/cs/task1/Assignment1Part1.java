package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part1   4/26/16, 21:47
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.karel.KarelTheRobot;


/**
 * Robot moves to the newspaper, picks it and goes back
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment1Part1 extends SmartKarell {

    public void run() throws Exception {

        moveToPaper();
        pickPaper();
        moveHome();

    }

    /**
     * Moves robot to the paper
     * @throws Exception
     */
    public void moveToPaper() throws Exception {
        if(frontIsClear()) {
            moveTwice();
            turnRight();
            if(frontIsClear()) {
                move();
                turnLeft();
                if(frontIsClear()) {
                    moveTwice();
                }
            }
        }
    }

    /**
     * Picks papers (beeper)
     * @throws Exception
     */
    public void pickPaper() throws Exception {
        if(beepersPresent()) {
            pickBeeper();
        }
    }

    /**
     * Moves robot back to 'home' position
     * @throws Exception
     */
    public void moveHome() throws Exception {

        turnLeft();
        turnLeft();
        moveTwice();
        moveTwice();
        turnRight();
        if(frontIsClear()) {
            move();
            turnRight();
        }
    }


}
