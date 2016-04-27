package com.shpp.havrylenko.cs.task1;

 /*
 * SmartKarell   4/26/16, 22:05
 *
 * By Kyrylo Havrylenko
 *
 */

import com.shpp.karel.KarelTheRobot;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class SmartKarell extends KarelTheRobot {

    /**
     * Moves robot forward twice
     * @throws Exception
     */
    public void moveTwice() throws Exception {

        move();
        move();

    }

    /**
     * Turns robot right
     * @throws Exception
     */
    public void turnRight() throws Exception {

        turnLeft();
        turnLeft();
        turnLeft();

    }

    /**
     * Turns robot to the up side
     * @throws Exception
     */
    public void turnUp() throws Exception {

        while(!facingNorth()) turnLeft();

    }

    /**
     * Turns robot to the down side
     * @throws Exception
     */
    public void turnDown() throws Exception {

        while(!facingSouth()) turnLeft();

    }


}
