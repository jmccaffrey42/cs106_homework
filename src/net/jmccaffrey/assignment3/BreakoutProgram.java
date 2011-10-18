package net.jmccaffrey.assignment3;

import acm.graphics.GCanvas;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Description of BreakoutProgram
 *
 * @author jmccaffrey
 */
public class BreakoutProgram extends GraphicsProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;
    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;
    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;
    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;
    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;
    /** Separation between bricks */
    private static final int BRICK_SEP = 4;
    /** Width of a brick */
    private static final int BRICK_WIDTH =
      (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;
    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;
    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;
    /** Number of turns */
    private static final int NTURNS = 3;

    private BreakoutBall ball;
    private BreakoutPaddle paddle;
    private Timer timer;
    
    public void init() {
        setSize(WIDTH, HEIGHT);
        paddle = new BreakoutPaddle(WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new BreakoutBall(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS, BALL_RADIUS);
    }

    public void run() {
        Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN };

        int xOffset = BRICK_SEP / 2;
        int yOffset = BRICK_Y_OFFSET;

        for (int row = 0; row < NBRICK_ROWS; row++) {
            Color color = colors[(int)((double)colors.length / (double)NBRICK_ROWS * row)];

            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                GRect rect = new GRect(col * BRICK_WIDTH + col * BRICK_SEP + xOffset, row * BRICK_HEIGHT + row * BRICK_SEP + yOffset, BRICK_WIDTH, BRICK_HEIGHT);
                rect.setColor(color);
                rect.setFilled(true);

                add(rect);
            }
        }

        add(paddle);
        add(ball);

        getGCanvas().addMouseMotionListener(this);

        timer = new Timer(35, new TimerAction());
        timer.start();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        paddle.setLocation(mouseEvent.getX() - paddle.getWidth() / 2, paddle.getY());
    }

    class TimerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GCanvas canvas = getGCanvas();

            ball.move();

            if (ball.getX() < 0 || ball.getX() + ball.getDiameter() > WIDTH) {
                ball.bounceX();
            }

            if (ball.getY() < 0 || ball.getY() + ball.getDiameter() > HEIGHT) {
                ball.bounceY();
            }

            GObject o = canvas.getElementAt(ball.getX(), ball.getY());
            if (o != null) {
                if (ball.getX() < o.getX() + o.getWidth()) {
                    ball.bounceX();
                }

                if (ball.getY() < o.getY() + o.getHeight()) {
                    ball.bounceY();
                }

                return;
            }
        }
    }
}
