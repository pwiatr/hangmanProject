/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.Engine;

import hangman.GUI.GameAreaPanel;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author P
 */
public class GameLogic {

    public enum HangmanChange {

        WORSEN,
        RESET,
        FAIL,
        NONE
    }

    public enum UpdateType {

        ChangeKeyMode,
        ChangeHangman,
        ShowPopup
    }

    public enum GameType {

        SINGLE,
        MULTI,
        PVP
    }

    public char wordChar[];
    public ArrayList<Integer> indexes;
    public int hangingTime = 0;
    public boolean tempGuess;
    public WordList wordList;
    public int wordIndex;
    public int actualHangmanChange = -1;

    public GameLogic() {
        indexes = new ArrayList<>();
        tempGuess = true;

    }

    public void NewGame(GameType gType) {
        switch (gType) {
            case SINGLE: {
                Word[] aWord = {new Word("Kaczka"), new Word("Romek")};
                initializeWordEngine(aWord);
                wordIndex = 0;
                wordToChar(wordList, wordIndex);
                break;
            }
            case MULTI: {
                initializeWordEngine();
                break;
            }
            case PVP: {
                initializeWordEngine();
                break;
            }

        }
    }

    public void initializeWordEngine(Word... words) {
        wordList = new WordList();
        wordList.addWords(words);

    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean wordToChar(WordList wordList, int wordIndex) {
        int wordLength = wordList.getWordName(wordIndex).length();

        this.wordChar = new char[wordLength];
        String tempWord = wordList.getWordName(wordIndex);

        for (int i = 0; i < wordLength; i++) {
            wordChar[i] = tempWord.charAt(i);
        }

        return true;
    }

    /**
     * Checks a particular letter in a word then changes the guess level,
     * hangman picture and winning/losing logic.
     *
     * @param small Small (char) representation of a letter.
     * @param big Big (char) representation of a letter.
     * @param c A component (JLabel) that holds the letter.
     */
    public void checkLetter(char small, char big, JButton keyButton) {

        indexes.clear();

        for (int i = 0; i < wordChar.length; i++) {
            if (wordChar[i] == small || wordChar[i] == big) {
                indexes.add(i);
                tempGuess = true;
            }
        }

        keyButton.setEnabled(false);
    }

    public HangmanChange checkLetterResult() {

        if (!tempGuess) {
            tempGuess = false;
            if (hangingTime < 10) { // Make worse
                changeHangman(HangmanChange.WORSEN);
                return HangmanChange.WORSEN;
            } else { // Completely fail
                changeHangman(HangmanChange.FAIL);
                return HangmanChange.FAIL;
            }
        }
        tempGuess = false;
        changeHangman(HangmanChange.NONE); // Do nothing
        return HangmanChange.NONE;
    }

    public boolean mainLoop() {

        return false;
    }

    public void changeHangman(HangmanChange hC) {

        switch (hC) {
            case WORSEN: {
                hangingTime++;
                actualHangmanChange = 0;
                break;
            }
            case RESET: {
                hangingTime = 0;
                actualHangmanChange = 1;
                break;
            }
            case FAIL: {
                actualHangmanChange = 2;
                break;
            }
            case NONE: {
                actualHangmanChange = -1;
                break;
            }
        }

    }

}
