package com.shpp.havrylenko.cs.task4;

 /*
 * Breakout   5/17/16, 11:43
 *
 * By Kyrylo Havrylenko
 *
 */

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Creates 'Breakout' clone game
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;
    private static final int BALL_DIAMETER = BALL_RADIUS * 2;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;
    private static final int PAUSE_TIME = 1000 / 60;

    private static int lives = NTURNS;
    private GRect paddle;
    private GOval ball;
    private GLabel remainLives;
    private double vx;
    private double vy = 5.0;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private boolean isGameOn = true;
    private int brickCounter = NBRICK_ROWS * NBRICKS_PER_ROW;
    private boolean isRoundStarted = true;

    /**
     * Entry point
     */
    public void run() {

        addMouseListeners();
        drawPaddle();
        drawBall();
        drawLives();
        generateRandomVx();
        drawBricks();

        while (isGameOn) {
            moveBall();
        }
    }

    /**
     * Catches move event from mouse and moves along paddle
     *
     * @param event mouse event
     */
    public void mouseMoved(MouseEvent event) {
        if (event.getX() > PADDLE_WIDTH || event.getX() < getWidth() -
                PADDLE_WIDTH) {
            paddle.setLocation(event.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET);
        }
    }

    /**
     * Catches click event from mouse and set round to start
     *
     * @param event mouse event
     */
    public void mouseClicked(MouseEvent event) {
        isRoundStarted = true;
    }

    /**
     * Animates moves of ball
     */
    private void moveBall() {
        if (isRoundStarted) {
            ball.move(vx, vy);

            GObject collider = getCollidingObject();
            if (collider == paddle) {
                onPaddleCollide();
            } else if (collider != null && collider != remainLives) {
                remove(collider);
                brickCounter--;
                if (brickCounter <= 0)
                    playerWon();
                vy = -vy;
            }

            if (isHittingTopWall()) {
                vy = -vy;
            }

            if (isHittingSideWalls()) {
                vx = -vx;
            }

            if (isHittingBottowWall()) {
                loseRound();
            }
        }
        pause(PAUSE_TIME);

    }

    /**
     * Draws a GLabel with info about remaining player lives
     */
    private void drawLives() {
        if (remainLives != null) remove(remainLives);
        remainLives = new GLabel("REMAIN lives: " + lives, 20, 20);
        remainLives.setFont(new Font("Arial", Font.BOLD, getWidth() / 20));
        add(remainLives);
    }

    /**
     * Draws paddle on screen
     */
    private void drawPaddle() {
        paddle = new GRect(0, getHeight() - PADDLE_Y_OFFSET,
                           PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setColor(Color.BLACK);
        add(paddle);
    }

    /**
     * Draws ball on screen
     */
    private void drawBall() {
        ball = new GOval(getWidth() / 2 - BALL_DIAMETER, getHeight() / 2 -
                BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        add(ball);
    }

    /**
     * Draws all the needed bricks on screen
     */
    private void drawBricks() {
        Color[] brickColors = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.CYAN
        };

        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                double x = brickCoordX(i);
                double y = brickCoordY(j);
                GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setColor(brickColors[rgen.nextInt(0, 4)]);
                add(brick);
            }
        }
    }

    /**
     * Gets X coord for new brick
     *
     * @param colNum column number of new brick
     *
     * @return x coord
     */
    private double brickCoordX(int colNum) {
        double result = getWidth() / 2 - (NBRICKS_PER_ROW * BRICK_WIDTH) / 2
                - ((NBRICK_ROWS - 1) * BRICK_SEP) / 2 + BRICK_WIDTH * colNum
                + BRICK_SEP * colNum;
        return result;
    }

    /**
     * Gets Y coord for new brick
     *
     * @param rowNum column number of new brick
     *
     * @return y coord
     */
    private double brickCoordY(int rowNum) {
        double result = BRICK_Y_OFFSET + BRICK_HEIGHT * rowNum + BRICK_SEP *
                rowNum;
        return result;
    }

    /**
     * Checks if ball hitting side wall
     *
     * @return boolean true if hitting
     */
    private boolean isHittingSideWalls() {
        return (ball.getX() <= 0 || (ball.getX() + BALL_DIAMETER) >=
                getWidth());
    }

    /**
     * Checks if ball hitting top wall
     *
     * @return boolean true if hitting
     */
    private boolean isHittingTopWall() {
        return (ball.getY() <= 0);
    }

    /**
     * Checks if ball hitting bottom wall
     *
     * @return boolean true if hitting
     */
    private boolean isHittingBottowWall() {
        return (ball.getY() + BALL_DIAMETER > getHeight() - PADDLE_Y_OFFSET
                + PADDLE_HEIGHT);
    }

    /**
     * Changes direction of a ball when collided with paddle
     */
    private void onPaddleCollide() {
        vy = -vy;
    }

    /**
     * Generetes x-direction of a ball
     */
    private void generateRandomVx() {
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx;
    }

    /**
     * Sets ball on center of a screen when round is lost
     */
    private void loseRound() {
        generateRandomVx();
        ball.setLocation(getWidth() / 2, getHeight() / 2);
        lives--;
        drawLives();
        isRoundStarted = false;
        if (lives <= 0) gameOver();
    }

    /**
     * Draws GLabel with 'GAME OVER' caption when game is lost
     */
    private void gameOver() {
        isGameOn = false;
        removeAll();
        GLabel gameOver = new GLabel("GAME OVER");
        gameOver.setFont(new Font("Arial", Font.BOLD, getWidth() / 10));
        gameOver.setColor(Color.RED);
        add(gameOver,
            getWidth() / 2 - gameOver.getWidth() / 2,
            getHeight() /
                2);
    }

    /**
     * Draws GLabel with 'YOU WON' caption when game is lost
     */
    private void playerWon() {
        isGameOn = false;
        removeAll();
        GLabel gameOver = new GLabel("YOU WON");
        gameOver.setFont(new Font("Arial", Font.BOLD, getWidth() / 10));
        gameOver.setColor(Color.GREEN);
        add(gameOver,
            getWidth() / 2 - gameOver.getWidth() / 2,
            getHeight() / 2);
    }

    /**
     * Gets colliding object when ball hitting something
     *
     * @return GObject or Null if there's no collision
     */
    private GObject getCollidingObject() {

        double ballX = ball.getX();
        double ballY = ball.getY();
        GObject el = null;
        Double[] xs = {
                ballX,
                ballX + BALL_DIAMETER,
                ballX,
                ballX + BALL_DIAMETER,
        };
        Double[] ys = {
                ballY,
                ballY,
                ballY + BALL_DIAMETER,
                ballY + BALL_DIAMETER,
        };

        for (int i = 0; i < 4; i++) {

            double x = xs[i];
            double y = ys[i];

            el = getElementAt(x, y);
            if (el != null) {
                break;
            }
        }
        return el;
    }
}
