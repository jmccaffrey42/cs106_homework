package net.jmccaffrey.assignment2;

import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * Description of CheckerboardProgram
 *
 * @author jmccaffrey
 */
public class CheckerboardProgram extends GraphicsProgram {
    private static final int BLOCK_SIZE = 28;
    private static final int BLOCK_ROWS = 8;
    private static final int BLOCK_COLS = 8;
    private static final int CIRCLE_DIAMETER = 22;
    
    private static final int WIDTH = BLOCK_SIZE * BLOCK_COLS;
    private static final int HEIGHT = BLOCK_SIZE * BLOCK_ROWS;

    public void init() {
        setSize(WIDTH + 1, HEIGHT + 1);
    }

    public void run() {
        add(new GRect(0, 0, WIDTH, HEIGHT));

        for (int r = 1; r < BLOCK_ROWS; r++) {
            add(new GLine(0, r * BLOCK_SIZE, WIDTH, r * BLOCK_SIZE));
        }

        for (int c = 1; c < BLOCK_COLS; c++) {
            add(new GLine(c * BLOCK_SIZE, 0, c * BLOCK_SIZE, HEIGHT));
        }

        for (int r = 0; r < BLOCK_ROWS; r++) {
            for (int c = 0; c < BLOCK_COLS; c++) {
                if (c % 2 == r % 2) {
                    int centerOffset = BLOCK_SIZE / 2 - CIRCLE_DIAMETER / 2;
                    int x = c * BLOCK_SIZE + centerOffset;
                    int y = r * BLOCK_SIZE + centerOffset;
                    
                    GOval oval = new GOval(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
                    oval.setFilled(true);
                    add(oval);
                }
            }
        }
    }
}
