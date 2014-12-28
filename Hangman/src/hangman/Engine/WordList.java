/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.Engine;
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
     * Adds a single/many Word objects to a WordList object.
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
    
    public int checkGuessed(){
        int guessed = 0;
        
        for(Word aWord: words){
            if(aWord.isGuessed())
                guessed++;
        }
        
        return guessed;
    }
    
    public int getAmount(){
        return this.wordsAmount;
    }
    
    public Word getWord(int index){
        if(index<this.wordsAmount){
            return words.get(index);
        }
        else {
            // There isn't a word with such index
            return null;
        }
    }
    
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
