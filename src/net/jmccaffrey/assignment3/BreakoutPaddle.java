package net.jmccaffrey.assignment3;

import acm.graphics.GRect;

/**
 * Description of BreakoutPaddle
 *
 * @author jmccaffrey
 */
public class BreakoutPaddle extends GRect {
    public BreakoutPaddle(double w, double h) {
        super(w, h);
        setupShape();
    }

    public BreakoutPaddle(double x, double y, double w, double h) {
        super(x, y, w, h);
        setupShape();
    }

    private void setupShape() {
        setFilled(true);
    }
}
