package net.jmccaffrey.assignment4;

/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;

public class Hangman extends ConsoleProgram {
    private HangmanCanvas canvas;
    private HangmanLexicon lexicon;

    private final static int TOTAL_GUESSES = 8;
    
    public void run() {
        setSize(640, 480);

//        lexicon = new StaticHangmanLexicon();
        lexicon = new FileHangmanLexicon();
        canvas = new HangmanCanvas();
        
	    add(canvas);

        startGame();
    }

    public void startGame() {
        boolean playAgain = true;
        
        while (playAgain) {
            canvas.reset();

            int remainingGuesses = TOTAL_GUESSES;
            String answerWord = lexicon.getRandomWord();

            StringBuffer guessedWord = new StringBuffer(answerWord.replaceAll(".", "-"));

            while(remainingGuesses > 0) {
                canvas.displayWord(guessedWord.toString());
                
                println(String.format("The word now looks like this: %s", guessedWord.toString()));
                println(String.format("You have %d guesses left.", remainingGuesses));

                char guess = readLetter("Your guess: ");

                int matches = 0;
                for (int i = 0; i < answerWord.length(); i++) {
                    if (answerWord.charAt(i) == guess) {
                        guessedWord.setCharAt(i, guess);
                        matches++;
                    }
                }

                if (matches == 0) {
                    canvas.noteIncorrectGuess(guess);
                    
                    println(String.format("There are no %c's in the word.", guess));
                    remainingGuesses--;
                } else {
                    println("That guess is correct.");

                    if (guessedWord.indexOf("-") < 0) {
                        break;
                    }
                }
            }

            if (remainingGuesses == 0) {
                canvas.displayWord(answerWord);
                
                println("You're completely hung.");
                println(String.format("The word was: %s", answerWord));
                println("You lose.");
            } else {
                canvas.displayWord(answerWord);

                println(String.format("You guessed the word: %s", answerWord));
                println("You win.");
            }

            playAgain = readBoolean("Play again? [y/n]: ", "y", "n");
        }
    }

    public char readLetter(String message) {
        char letter = ' ';
        
        while (letter == ' ') {
            print(message);
            String line = readLine().trim().toUpperCase();

            if (!line.matches("^[a-zA-Z]$")) {
                println("Please enter a letter A-Z");
                continue;
            }

            letter = line.charAt(0);
        }

        return letter;
    }
}
