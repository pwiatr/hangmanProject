/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.Engine;

import Word.Word;
import Word.WordList;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author P
 */
public class GameWordLogic {
    private WordList wordList;
    private int wordIndex;
    private char[] wordCharacters;
    private ArrayList<Integer> wordCharacterIndexes;
    private boolean guessedLetter = false;

    // <editor-fold defaultstate="collapsed" desc="Getters Setters">
    public void setWordIndex(int wordIndex) {
        this.wordIndex = wordIndex;
    }
        
    public boolean isGuessedLetter() {
        return guessedLetter;
    }

    public void setGuessedLetter(boolean guessedLetter) {
        this.guessedLetter = guessedLetter;
    }

    public WordList getWordList() {
        return wordList;
    }

    public int getWordIndex() {
        return wordIndex;
    }

    public char[] getWordCharacters() {
        return wordCharacters;
    }

    public ArrayList<Integer> getWordCharacterIndexes() {
        return wordCharacterIndexes;
    }
    
    // </editor-fold>
    
    public GameWordLogic(){
        initializeWordEngine();
        wordToChar(wordList, wordIndex);
    }

    private Word[] fetchWords() {
        // TO-DO Połączenie z bazą danych i kopiowanie słów

        // TO-DO Obliczenia wymagające wyboru X-ilości wyrazów (użycie Random)
        // Zwrócenie 
        Word[] wyrazy = {new Word("Aaa")};
        return wyrazy;
    }

     /**
     * Completely reset all in-game variables.
     */
    private void initializeWordEngine() {
        wordList = null;
        wordIndex = 0;
        wordCharacterIndexes = new ArrayList<>();

        // Fetch words
        Word[] aWord = {new Word("Kaczka"), new Word("Romek"), new Word("Jasnek"), new Word("Marek"),
            new Word("Dziwaczka"), new Word("Franek"), new Word("Tomasz"), new Word("Janusz"),
            new Word("Word"), new Word("Kaszanka")}; // TO SUBSTITUTE
        wordIndex = 0; // TO SUBSTITUTE

        setupWords(aWord); // To-Do usunąć, gdy już będzie podpięta baza
    }

    /**
     * Creates a internal WordList with specified words.
     * @param words Words to input into internal WordList.
     */
    private void setupWords(Word... words) {
        wordList = new WordList();
        wordList.addWords(words);
    }
    
    /**
     * Converts a word from string into a character array.
     * @param wordList Actual WordList object with words used in game.
     * @param wordIndex The index of a word in wordList.
     */
    private void wordToChar(WordList wordList, int wordIndex) {
        int wordLength = wordList.getWordName(wordIndex).length();

        this.wordCharacters = new char[wordLength];
        String tempWord = wordList.getWordName(wordIndex);

        for (int i = 0; i < wordLength; i++) {
            wordCharacters[i] = tempWord.charAt(i);
        }
    }

    /**
     * Method used for converting a word and showing a field with it. When all
     * words have been used the game is informed about it. Used for continuous
     * game - showing the next word.
     *
     * @param index Index of a word form particular WordList.
     * @return False because the word has been converted but it's not visible.
     */
    public boolean convertNewWord(int index) {
        if (index < wordList.getAmount()) {
            wordToChar(wordList, index);
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Checks a particular letter in a word then informs the game about it.
     * Changes the particular key to disabled mode after checking it.
     *
     * @param small Small (char) representation of a letter.
     * @param big Big (char) representation of a letter.
     * @param keyButton A component (JButton) that holds the letter.
     */
    public void checkLetter(char small, char big, JButton keyButton) {

        wordCharacterIndexes.clear();

        for (int i = 0; i < wordCharacters.length; i++) {
            if (wordCharacters[i] == small || wordCharacters[i] == big) {
                wordCharacterIndexes.add(i);
                wordList.getWord(wordIndex).checkLetter(wordCharacters[i]);
                setGuessedLetter(true);
            }
        }

        keyButton.setEnabled(false);
    }


}
