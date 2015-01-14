/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.Engine;

import static hangman.Engine.GameLogic.HangmanChange.*;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author P
 */
public class GameLogic {

    public enum HangmanChange {

        WORSEN,
        RESET,
        FAIL,
        GUESS,
        WINGAME,
        LOSEGAME,
        DRAW,
        NONE
    }

    public enum GameDifficulty {

        EASY,
        MEDIUM,
        HARD
    }

    public enum GameType {

        SINGLE, // A one-game - one word to guess.
        MULTI, // Multiple level game - try to guess as many words as possible (maximum 15).
        PVP // Two player game - players take turns after every guessed (or not) word.
    }

    /**
     * The step in hanging process.
     */
    public int hangingTime = 0;
    /**
     * The jump caused by failed guess (easy - 1, medium - 2, hard - 4)
     */
    public static int hangingJump = 1;

    /**
     * Enumration considering actual game situation.
     */
    public HangmanChange hangmanChange;
    
    public WordList wordList;
    public int wordIndex;
    public char wordChar[];
    public ArrayList<Integer> wordCharIndexes;
        
    public boolean isFieldShown = false;
    public boolean isGameFinished = false;

    public int gameScore;

    private boolean guessedLetter;

    private static GameDifficulty gDiff;
    public static GameType gType;
    private static int gWordsToGuess;
    private static int gWordsUsed = 0;
    public static Player[] players = {null, null};
    
    public GameLogic() {

    }

    public void NewGame(/*Word ... words */) {

        resetGame();
        Word[] aWord = {new Word("Kaczka"), new Word("Romek"), new Word("Jasnek"), new Word("Marek"),
                            new Word("Dziwaczka"),new Word("Franek"),new Word("Tomasz"),new Word("Janusz"),
                            new Word("Word"),new Word("Kaszanka")}; // TO SUBSTITUTE
        wordIndex = 0; // TO SUBSTITUTE
       
        
        initializeWordEngine(aWord);
        wordToChar(wordList, wordIndex);

    }

    /**
     * Converts a word from string into a character array.
     * @param wordList Actual WordList object with words used in game.
     * @param wordIndex The index of a word in wordList.
     */
    public void wordToChar(WordList wordList, int wordIndex) {
        int wordLength = wordList.getWordName(wordIndex).length();

        this.wordChar = new char[wordLength];
        String tempWord = wordList.getWordName(wordIndex);

        for (int i = 0; i < wordLength; i++) {
            wordChar[i] = tempWord.charAt(i);
        }
    }

    /**
     * Method used for converting a word and showing a field with it.
     * When all words have been used the game is informed about it.
     * @param index Index of a word form particular WordList.
     */
    public void outputNewWord(int index) {
        if (index < wordList.getAmount()) {
            wordIndex = index;
            wordToChar(wordList, wordIndex);
            changeHangman(HangmanChange.RESET);
            isFieldShown = false;
        } else {
           
        }
    }

    /**
     * Creates a internal WordList with specified words.
     * @param words Words to input into internal WordList.
     */
    public void initializeWordEngine(Word... words) {
        wordList = new WordList();
        wordList.addWords(words);

    }

    /**
     * Sets the game difficulty (the amount of possible errors).
     * @param gameDiff Type of difficulty.
     */
    public static void setDifficulty(GameDifficulty gameDiff) {

        switch (gameDiff) {
            case EASY: {
                gDiff = GameDifficulty.EASY;
                hangingJump = 1;
                break;
            }
            case MEDIUM: {
                gDiff = GameDifficulty.MEDIUM;
                hangingJump = 2;
                break;
            }
            case HARD: {
                gDiff = GameDifficulty.HARD;
                hangingJump = 3;
                break;
            }
        }
    }

    /**
     * Sets the game type for later usage.
     * @param gameType
     * @return 
     */
    public static boolean setType(GameType gameType) {

        switch (gameType) {
            case SINGLE: {
                gType = GameType.SINGLE;
                gWordsToGuess = 1;
                break;
            }
            case MULTI: {
                gType = GameType.MULTI;
                gWordsToGuess = 10;
                break;
            }
            case PVP: {
                gType = GameType.PVP;
                gWordsToGuess=10;
                break;
            }

        }

        return true;
    }

    /**
     * Completely reset all in-game variables.
     */
    private void resetGame() {
        hangmanChange = HangmanChange.NONE;
        hangingTime = 0;
        
        gameScore = 0;
        guessedLetter = false;
        
        wordList = null;
        wordIndex = 0;
        wordCharIndexes = null;
        wordCharIndexes = new ArrayList<>();
       
        isFieldShown = false;
    }

    @Override
    public String toString() {
        return "Game type: " + gType + ", game difficulty: " + gDiff;
    }

    /**
     * Checks a particular letter in a word then changes the guess level,
     * hangman picture and winning/losing logic.
     * Changes the key to disabled after checking it.
     * @param small Small (char) representation of a letter.
     * @param big Big (char) representation of a letter.
     * @param keyButton A component (JLabel) that holds the letter.
     */
    public void checkLetter(char small, char big, JButton keyButton) {

        wordCharIndexes.clear();

        for (int i = 0; i < wordChar.length; i++) {
            if (wordChar[i] == small || wordChar[i] == big) {
                wordCharIndexes.add(i);
                wordList.getWord(wordIndex).checkLetter(wordChar[i]);
                guessedLetter = true;
            }
        }

        keyButton.setEnabled(false);
    }

    /**
     * Main logic behind checking the entire word.
     * The moment of winning, failing, making a mistake and not doing anything
     * is managed in this method.
     */
    public void checkGuessResult() {
        if(gWordsUsed < gWordsToGuess){
            if (wordList.getWord(wordIndex).isGuessed()) {
                changeHangman(GUESS);
                gWordsUsed += 1;
            } 
            else if (!guessedLetter) { 
                if (hangingTime > 11) { // Too much errors - fail!
                    changeHangman(FAIL);
                } else {
                    changeHangman(WORSEN);
                        if (hangingTime > 11) { // Too much errors = fail
                            changeHangman(FAIL);
                        }
                }
            } 
            else { // Nothing changed = a letter must have been guessed
                guessedLetter = false;
                changeHangman(NONE);
            }
        } else {
            changeHangman(WINGAME);
             isGameFinished = true;
        }

    }

    public void changeHangman(HangmanChange hC) {

        switch (hC) {
            case WORSEN: {
                hangingTime += hangingJump;
                hangmanChange = WORSEN;       
                break;
            }
            case RESET: {
                hangingTime = 0;
                setDifficulty(gDiff);
                hangmanChange =  RESET;      
                break;
            }
            case FAIL: {
                hangmanChange =  FAIL;       
                break;
            }
            case GUESS: {
                hangmanChange = GUESS;    
                break;
            }
            case WINGAME: {
                hangmanChange = WINGAME;
                break;
            }
            case LOSEGAME: {
                hangmanChange = LOSEGAME;
                break;
            }
            case DRAW: {
                hangmanChange = DRAW;
                break;
            }
            case NONE: {
                hangmanChange = NONE;        
                break;
            }
        }

    }

}
