package net.jmccaffrey.assignment4;

/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
    private GObject[] bodyShapes;
    private GLabel wordLabel;
    private GLabel guessLabel;

    private String incorrectGuesses;

    public HangmanCanvas() {
        bodyShapes = new GObject[8];

        wordLabel = new GLabel("");
        wordLabel.setFont("*-bold-32");

        guessLabel = new GLabel("");
        wordLabel.setFont("*-bold-32");
    }

/** Resets the display so that only the scaffold appears */
	public void reset() {
        GPen shape;
        
		removeAll();
        incorrectGuesses = "";
        guessLabel.setLabel("");
        
        int x = (getParent().getWidth() / 4) - ((BEAM_LENGTH + UPPER_ARM_LENGTH) / 2);
        int y = 10;

        // Labels
        wordLabel.setLocation(10, getParent().getHeight() - 50);
        guessLabel.setLocation(10, getParent().getHeight() - 20);
        add(wordLabel);
        add(guessLabel);

        // Scaffold
        add(new GLine(x, y, x + BEAM_LENGTH, y));
        add(new GLine(x, y, x, SCAFFOLD_HEIGHT));
        add(new GLine(x + BEAM_LENGTH, y, x + BEAM_LENGTH, y + ROPE_LENGTH));
        
        x += BEAM_LENGTH;
        y += ROPE_LENGTH;

        // Head
        bodyShapes[0] = new GOval(x - HEAD_RADIUS, y, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
        y += HEAD_RADIUS * 2;

        // Body
        shape = new GPen(x, y);
        shape.drawLine(0, BODY_LENGTH);
        shape.drawLine(-HIP_WIDTH / 2, 0);
        shape.drawLine(HIP_WIDTH, 0);

        bodyShapes[1] = shape;

        // Left arm
        shape = new GPen(x, y + ARM_OFFSET_FROM_HEAD);
        shape.drawLine(-UPPER_ARM_LENGTH, 0);
        shape.drawLine(0, LOWER_ARM_LENGTH);
        bodyShapes[2] = shape;

        // Right arm
        shape = new GPen(x, y + ARM_OFFSET_FROM_HEAD);
        shape.drawLine(UPPER_ARM_LENGTH, 0);
        shape.drawLine(0, LOWER_ARM_LENGTH);
        bodyShapes[3] = shape;

        y += BODY_LENGTH;

        // Legs
        bodyShapes[4] = new GLine(x - HIP_WIDTH / 2, y, x - HIP_WIDTH / 2, y + LEG_LENGTH);
        bodyShapes[5] = new GLine(x + HIP_WIDTH / 2, y, x + HIP_WIDTH / 2, y + LEG_LENGTH);

        // Feet
        bodyShapes[6] = new GLine(x - HIP_WIDTH / 2, y + LEG_LENGTH, x - HIP_WIDTH / 2 - FOOT_LENGTH, y + LEG_LENGTH);
        bodyShapes[7] = new GLine(x + HIP_WIDTH / 2, y + LEG_LENGTH, x + HIP_WIDTH / 2 + FOOT_LENGTH, y + LEG_LENGTH);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
        wordLabel.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		add(bodyShapes[incorrectGuesses.length()]);
        guessLabel.setLabel(incorrectGuesses += letter);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
