package net.jmccaffrey.assignment2;

import acm.graphics.GCanvas;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * Description of PyramidProgram
 *
 * @author jmccaffrey
 */
public class PyramidProgram extends GraphicsProgram {
    /** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

    /** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

    /** Number of bricks in the base of the assignment3 */
	private static final int BRICKS_IN_BASE = 14;

    private int pyramidHeight;
    private int pyramidWidth;
    
    public void init() {
        pyramidWidth = BRICK_WIDTH * BRICKS_IN_BASE;
        pyramidHeight = BRICK_HEIGHT * BRICKS_IN_BASE;

        setSize(pyramidWidth + 200, pyramidHeight + 100);
    }

    public void run() {
        GCanvas canvas = getGCanvas();

        int xOffset = canvas.getWidth() / 2 - pyramidWidth / 2;
        int yOffset = canvas.getHeight() - pyramidHeight;
        int lineNumber = 0;

        for (int i = 1; i <= BRICKS_IN_BASE; i++) {
            int lineXOffset = xOffset + ((BRICKS_IN_BASE - i) * BRICK_WIDTH) / 2;

            for (int j = 0; j < i; j++) {
                GRect rect = new GRect(lineXOffset + j * BRICK_WIDTH, yOffset + lineNumber * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
                canvas.add(rect);
            }

            lineNumber += 1;
        }
    }
}
