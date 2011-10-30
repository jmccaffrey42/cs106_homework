package net.jmccaffrey.assignment4;

/**
 * Description of HangmanLexicon
 *
 * @author jmccaffrey
 */
public abstract class HangmanLexicon {
    public int getWordCount() {
        return 0;
    }

    public String getWord(int index) {
        return null;
    }

    public String getRandomWord() {
        return getWord((int)(Math.random() * getWordCount()));
    }
}
