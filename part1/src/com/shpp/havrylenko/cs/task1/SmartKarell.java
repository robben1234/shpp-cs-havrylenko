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
public class SmartKarell {

    public static void moveTwice(KarelTheRobot robot) throws Exception {

        robot.move();
        robot.move();

    }

    public static void turnRight(KarelTheRobot robot) throws Exception {

        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();

    }

    public static void turnUp(KarelTheRobot robot) throws Exception {

        while(!robot.facingNorth()) robot.turnLeft();

    }

    public static void turnDown(KarelTheRobot robot) throws Exception {

        while(!robot.facingSouth()) robot.turnLeft();

    }


}
