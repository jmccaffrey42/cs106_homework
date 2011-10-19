package net.jmccaffrey.assignment3;

import acm.graphics.GOval;
import acm.util.RandomGenerator;

/**
 * Description of BreakoutBall
 *
 * @author jmccaffrey
 */
public class BreakoutBall extends GOval {
    private double radius;
    private double velocityX = 0;
    private double velocityY = 2;
    private RandomGenerator rgen = RandomGenerator.getInstance();

    public BreakoutBall(double radius) {
        super(radius * 2, radius * 2);
        this.radius = radius;
        setupShape();
    }

    public BreakoutBall(double x, double y, double radius) {
        super(x, y, radius * 2, radius * 2);
        this.radius = radius;
        setupShape();
    }

    public void move() {
        super.move(velocityX, velocityY);
    }

    public void bounceX() {
        velocityX *= -1;
    }

    public void bounceY() {
        velocityY *= -1;
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public void accelerate(double speed) {
        velocityY = velocityY / Math.abs(velocityY) * Math.abs(velocityY) + speed;
    }

    private void setupShape() {
        setFilled(true);

        velocityX = rgen.nextDouble(2.0, 6.0);
        if (rgen.nextBoolean(0.5)) {
            velocityX = -velocityX;
        }
    }
}
