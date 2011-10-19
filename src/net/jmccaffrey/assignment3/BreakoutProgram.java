package net.jmccaffrey.assignment3;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Description of BreakoutProgram
 *
 * @author jmccaffrey
 */
public class BreakoutProgram extends GameProgram {
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

    private static final int NPADDLE_BOUNCES = 2;

    private GLabel gameMessage;
    private GLabel lifeDisplay;
    private BreakoutBall ball;
    private BreakoutPaddle paddle;
    private int paddleBounces = 0;
    private int lives;
    private int bricksRemaining;
    
    public void setup() {
        setSize(WIDTH, HEIGHT);
        getGCanvas().addMouseMotionListener(this);
        getGCanvas().addMouseListener(this);

        lifeDisplay = new GLabel("");
        lifeDisplay.setFont("*-bold-14");

        gameMessage = new GLabel("");
        gameMessage.setFont("*-bold-32");

        showMessage("CLICK TO BEGIN");
    }

    public void restartGame() {
        getGCanvas().removeAll();
        
        lives = NTURNS;
        bricksRemaining = NBRICK_ROWS * NBRICKS_PER_ROW;

        Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN };

        int xOffset = BRICK_SEP / 2;
        int yOffset = BRICK_Y_OFFSET;

        for (int row = 0; row < NBRICK_ROWS; row++) {
            Color color = colors[(int)((double)colors.length / (double)NBRICK_ROWS * row)];

            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                BreakoutBrick rect = new BreakoutBrick(col * BRICK_WIDTH + col * BRICK_SEP + xOffset, row * BRICK_HEIGHT + row * BRICK_SEP + yOffset, BRICK_WIDTH, BRICK_HEIGHT);
                rect.setColor(color);
                rect.setFilled(true);

                add(rect);
            }
        }

        paddle = new BreakoutPaddle(WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        add(paddle);

        ball = new BreakoutBall(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS, BALL_RADIUS);
        add(ball);

        lifeDisplay.setLabel(String.format("Lives remaining: %d", lives));
        lifeDisplay.setLocation(WIDTH - lifeDisplay.getWidth() - 10, lifeDisplay.getHeight() + 10);
        add(lifeDisplay);
        
        resumePlay();
    }

    public void play() {
        GCanvas canvas = getGCanvas();

        ball.move();

        if (ball.getX() < 0 || ball.getX() + ball.getDiameter() > WIDTH) {
            ball.bounceX();
        }

        if (ball.getY() < 0) {
            ball.bounceY();
        }

        if (ball.getY() + ball.getDiameter() > HEIGHT) {
            lives--;
            lifeDisplay.setLabel(String.format("Lives remaining: %d", lives));
            
            if (lives <= 0) {
                showMessage("DEFEAT!");
            } else {
                ball.setLocation(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);
            }

            return;
        }
        
        for (int i = 0; i < 4; i++) {
            GPoint point = new GPoint(ball.getX() + (i & 1) * ball.getDiameter(), ball.getY() + ((i & 2) / 2) * ball.getDiameter());
            GObject o = canvas.getElementAt(point);

            if (o == null) {
                continue;
            }

            if (o instanceof BreakoutBrick) {
                canvas.remove(o);
                bricksRemaining--;

                if (bricksRemaining <= 0) {
                    showMessage("VICTORY!");
                }
            } else if (o instanceof BreakoutPaddle) {
                paddleBounces++;

                if (paddleBounces > NPADDLE_BOUNCES) {
                    paddleBounces = 0;
                    ball.accelerate(1.0);
                }
            }

            if (point.getX() < o.getX() + o.getWidth() || point.getX() < o.getX()) {
                ball.bounceX();
            }

            if (point.getY() < o.getY() + o.getHeight() || point.getY() < o.getY()) {
                ball.bounceY();
            }

            return;
        }
    }

    public void showMessage(String message) {
        pausePlay();

        gameMessage.setLabel(message);
        gameMessage.setLocation(WIDTH / 2 - gameMessage.getWidth() / 2, HEIGHT / 2 - gameMessage.getHeight() / 2);

        add(gameMessage);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (isPaused()) {
            return;
        }
        
        paddle.setLocation(mouseEvent.getX() - paddle.getWidth() / 2, paddle.getY());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (!isPaused()) {
            return;
        }

        restartGame();
    }
}
