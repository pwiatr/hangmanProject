package Word;

import java.util.ArrayList;

/**
 * A customized container for Word objects.
 *
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class WordList {

    /**
     * An ArrayList containing Word objects.
     */
    private final ArrayList<Word> words;
    /**
     * The amount of words in a WordList.
     */
    private int wordsAmount = 0;
    /**
     * The name of a category that words belong to.
     */
    private String wordCategoryName;

    /**
     * Constructs a new word list.
     */
    public WordList() {
        this.words = new ArrayList();
    }

    /**
     * Adds a single/many Word object(s) to a WordList object.
     *
     * @param aWords One/multiple Word objects.
     * @return True if added all words, false otherwise.
     */
    public boolean addWords(Word... aWords) {
        boolean added = false;
        for (Word aWord : aWords) {
            this.words.add(aWord);
            this.wordsAmount++;
            added = true;
        }
        return added;
    }

      //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    
    /**
     * Checks the amount of guessed words.
     *
     * @return Integer number of guessed words amount.
     */
    public int getGuessedAmount() {
        int guessed = 0;

        for (Word aWord : words) {
            if (aWord.isGuessed()) {
                guessed++;
            }
        }

        return guessed;
    }

    /**
     * Gets a word on specified index.
     *
     * @param index The index of a word on a list.
     * @return Particular word as Word object, otherwise null.
     */
    public Word getWord(int index) {
        if (index < this.wordsAmount) {
            return words.get(index);
        } else {
            // There isn't a word with such index
            return null;
        }
    }

    /**
     * Gets a word name at specified index.
     *
     * @param index The index of a word on a list.
     * @return String representation of a word name, otherwise null.
     */
    public String getWordName(int index) {
        if (index < this.wordsAmount) {
            return words.get(index).getWord();
        } else {
            // There isn't a word with such index
            return null;
        }
    }

    /**
     * Returns the string representation of category.
     *
     * @return String category name.
     */
    public String getWordCategoryName() {
        return wordCategoryName;
    }

    /**
     * Sets the word category name.
     *
     * @param wordCategoryName The name the category will be set to.
     */
    public void setWordCategoryName(String wordCategoryName) {
        this.wordCategoryName = wordCategoryName;
    }

    /**
     * The amount of words in a WordList.
     *
     * @return Integer number of words in WordList.
     */
    public int getAmount() {
        return this.wordsAmount;
    }
    
    
    //</editor-fold>

    /**
     * Prints all the in game words.
     * @return In game words.
     */
    @Override
    public String toString() {
        return "WordList{" + "words=" + words + '}';
    }
}
