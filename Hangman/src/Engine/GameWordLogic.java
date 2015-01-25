package Engine;

import Database.DBFetch;
import Word.Word;
import Word.WordList;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

/**
 * GameWordLogic takes care of all stuff connected with checking words.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class GameWordLogic {
    /**
     * Word list used in the game.
     */
    private WordList wordList;
    /**
     * Actual word index.
     */
    private int wordIndex;
    /**
     * The indexes of words from actual word list that has been used.
     */
    private boolean[] wordListIndexesUsed;
    /**
     * Actual word characters as char array.
     */
    private char[] wordCharacters;
    /**
     * Actual word category.
     */
    private String wordCategory;
    /**
     * The character indexes of actual word that can be used to show word.
     */
    private ArrayList<Integer> wordCharacterIndexes;
   
    private boolean guessedLetter = false;

    /**
     * Sets the indox to a random value.
     */
    public void setRandomIndex(){
        int randomIndex=0;
        
        wordListIndexesUsed[wordIndex] = true;
        Random rand = new Random();
        
        randomIndex = rand.nextInt(wordList.getAmount()-1);
        
        while(wordListIndexesUsed[randomIndex] == true){
            randomIndex = rand.nextInt(wordList.getAmount()-1);
        }
        
        setWordIndex(randomIndex);
    }
    // <editor-fold defaultstate="collapsed" desc="Getters Setters">
    /**
     * Sets the word index to the specified value.
     * @param wordIndex The value to be est as word index.
     */
    public void setWordIndex(int wordIndex) {
        this.wordIndex = wordIndex;
    }
    /**
     * Checks whether the letter has been guessed.
     * @return True if the letter was guessed.
     */
    public boolean isGuessedLetter() {
        return guessedLetter;
    }

    /**
     * Sets the letter to guessed (or not).
     * @param guessedLetter Set to true if guessed, false otherwise.
     */
    public void setGuessedLetter(boolean guessedLetter) {
        this.guessedLetter = guessedLetter;
    }

    /**
     * Gets the actual used word list.
     * @return The word list as WordList object.
     */
    public WordList getWordList() {
        return wordList;
    }

    /**
     * Gets the actual word index.
     * @return The actual word index as integer.
     */
    public int getWordIndex() {
        return wordIndex;
    }

    /**
     * Gets the word character char array.
     * @return Word characters char array.
     */
    public char[] getWordCharacters() {
        return wordCharacters;
    }

    /**
     * Gets the list of a word character indexes that can be used to show word.
     * @return Word character indexes as ArrayList of Integers.
     */
    public ArrayList<Integer> getWordCharacterIndexes() {
        return wordCharacterIndexes;
    }
    
    // </editor-fold>
    
    /**
     * Constructs a new game world object and initializes all variables.
     * @param wordCategory The category of words.
     */
    public GameWordLogic(String wordCategory){
        this.wordCategory = wordCategory;
        initializeWordEngine();
        wordToChar(wordList, wordIndex);
    }

     /**
     * Completely reset all in-game variables.
     */
    private void initializeWordEngine() {
        wordList = null;
        wordIndex = 0;
        wordCharacterIndexes = new ArrayList<>();

        Word[] words;
        // Fetch words
        if(wordCategory.equalsIgnoreCase("Wszystko"))
            words = DBFetch.fetchWords();
        else
            words = DBFetch.fetchWords(wordCategory);
        
        setupWords(words); 
        wordListIndexesUsed = new boolean[wordList.getAmount()];
        System.out.println(wordList);
        setRandomIndex();
    }

    /**
     * Creates a internal WordList with specified words.
     * @param words Words to input into internal WordList.
     */
    private void setupWords(Word... words) {
        wordList = new WordList();
        wordList.setWordCategoryName("Imiona");
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
