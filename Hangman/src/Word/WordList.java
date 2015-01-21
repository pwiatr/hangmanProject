/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Word;
import java.util.ArrayList;

/**
 * A customized container for Word objects.
 * @author P
 */
public class WordList {
    /**
     * An ArrayList containing Word objects.
     */
    private ArrayList<Word> words;
    /**
     * The amount of words in a WordList.
     */
    private int wordsAmount;
    
    public WordList(){
        this.words = new ArrayList();
        
        wordsAmount = 0;
    }
    
    /**
     * Adds a single/many Word object(s) to a WordList object.
     * @param aWords One/multiple Word objects.
     * @return True if added any word, false otherwise.
     */
    public boolean addWords(Word... aWords){
        boolean added = false;
        for(Word aWord : aWords){
            this.words.add(aWord);
            this.wordsAmount++;
            added = true;
        }
        return added;
    }
    
    /**
     * Checks the amount of guessed words.
     * @return Integer number of guessed words amount.
     */
    public int checkGuessedAmount(){
        int guessed = 0;
        
        for(Word aWord: words){
            if(aWord.isGuessed())
                guessed++;
        }
        
        return guessed;
    }
    
    /**
     * The amount of words in a WordList.
     * @return Integer number of words in WordList.
     */
    public int getAmount(){
        return this.wordsAmount;
    }
    
     /**
     * Gets a word on specified index.
     * @param index The index of a word on a list.
     * @return Particular word as Word object, otherwise null.
     */
    public Word getWord(int index){
        if(index<this.wordsAmount){
            return words.get(index);
        }
        else {
            // There isn't a word with such index
            return null;
        }
    }
    
    /**
     * Gets a word name at specified index.
     * @param index The index of a word on a list.
     * @return String representation of a word name, otherwise null.
     */
    public String getWordName(int index){
        if(index<this.wordsAmount){
            return words.get(index).getWord();
        }
        else {
            // There isn't a word with such index
            return null;
        }
    }
    
   
    
    
    
    
}
