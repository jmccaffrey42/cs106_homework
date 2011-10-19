package net.jmccaffrey.assignment3;

import acm.graphics.GCanvas;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description of GameProgram
 *
 * @author jmccaffrey
 */
public class GameProgram extends GraphicsProgram {
    private Timer timer;

    @Override
    public void init() {
        timer = new Timer(35, new TimerAction());
    }

    @Override
    public void run() {
        setup();
    }

    public void setup() {

    }

    public void play() {

    }

    public void pause() {
        timer.stop();
    }

    public void resume() {
        timer.start();
    }

    class TimerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            play();
        }
    }
}
