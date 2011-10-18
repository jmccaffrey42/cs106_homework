package net.jmccaffrey.assignment2;

import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Description of TargetProgram
 *
 * @author jmccaffrey
 */
public class TargetProgram extends GraphicsProgram {
    public void init() {

    }

    public void run() {
        GCanvas canvas = getGCanvas();
        GOval o1 = new GOval(canvas.getWidth() / 2 - 50, canvas.getHeight() / 2 - 50, 100, 100);
        o1.setColor(Color.RED);
        o1.setFillColor(Color.RED);
        o1.setFilled(true);

        GOval o2 = new GOval(canvas.getWidth() / 2 - 30, canvas.getHeight() / 2 - 30, 60, 60);
        o2.setColor(Color.WHITE);
        o2.setFillColor(Color.WHITE);
        o2.setFilled(true);

        GOval o3 = new GOval(canvas.getWidth() / 2 - 15, canvas.getHeight() / 2 - 15, 30, 30);
        o3.setColor(Color.RED);
        o3.setFillColor(Color.RED);
        o3.setFilled(true);

        canvas.add(o1);
        canvas.add(o2);
        canvas.add(o3);
    }
}
