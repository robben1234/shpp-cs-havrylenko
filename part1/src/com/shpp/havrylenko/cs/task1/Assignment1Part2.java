package com.shpp.havrylenko.cs.task1;

 /*
 * Assignment1Part2   4/26/16, 22:05
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.karel.KarelTheRobot;

import java.awt.image.Kernel;

import static com.shpp.havrylenko.cs.task1.SmartKarell.moveTwice;
import static com.shpp.havrylenko.cs.task1.SmartKarell.turnRight;

/**
 * Robot puts beepers on empty spots in colognes
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Assignment1Part2 extends KarelTheRobot {

    public void run() throws Exception {

        turnLeft();
        while(true) {
            // TODO: nice break, because for now it's not working
            while(frontIsClear()) {
                if(beepersPresent()) move();
                else putBeeper();
            }


            if(facingNorth()) {
                if(goToOtherCologne(true)) break;
            } else {
                if(goToOtherCologne(false)) break;
            }

        }

    }


    /**
     * Moves the robot to other cologne
     * @param dir direction. 0 - to bottom, 1 - to top
     * @return true if robot can't move
     * @throws Exception
     */
    public boolean goToOtherCologne(boolean dir) throws Exception {

            turn(!dir, this);
            if (checkForEnd(this)) return true;
            moveTwice(this);
            moveTwice(this);
            turn(dir, this);
            if(frontIsBlocked()) {
                turnLeft();
                turnLeft();
            } else {
                turn(dir, this);
                while(frontIsClear()) move();
                turnLeft();
                turnLeft();
            }

        return false;
    }

    /**
     * Checks for the end of the map
     * @param robot {@code KarelTheRobot} instance
     * @return true if the end
     * @throws Exception
     */
    public boolean checkForEnd(KarelTheRobot robot) throws Exception {
        if(robot.facingEast() && robot.frontIsBlocked()) {
            return true;
        }
        return false;
    }

    /**
     * Turns left or right on your input
     * @param dir 0 - left, 1 - right
     * @throws Exception
     */
    public void turn(boolean dir, KarelTheRobot robot) throws Exception {
        if(dir) {
            turnLeft();
        } else {
            turnRight(robot);
        }
    }

}
