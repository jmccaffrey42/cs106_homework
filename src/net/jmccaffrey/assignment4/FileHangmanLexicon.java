package net.jmccaffrey.assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Description of FileHangmanLexicon
 *
 * @author jmccaffrey
 */
public class FileHangmanLexicon extends HangmanLexicon {
    private List<String> words = new ArrayList<String>();
    
    public FileHangmanLexicon() {
        this("HangmanLexicon.txt");
    }

    public FileHangmanLexicon(String filename) {
        String resourcePath = getClass().getResource(filename).toString();

        try {
            String line;
            FileReader reader = new FileReader(resourcePath);
            BufferedReader in = new BufferedReader(reader);

            while ((line = in.readLine()) != null) {
                words.add(line.toUpperCase().trim());
            }

            in.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw(new RuntimeException(e));
        }
    }
    
    public int getWordCount() {
        return words.size();
    }

    public String getWord(int index) {
        if (index < 0 || index >= words.size()) {
            return null;
        }
        
        return words.get(index);
    }
}
