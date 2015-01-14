/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.Engine;

/**
 * Word class represents a word that can be guessed throughout the game.
 * @author P
 */
public class Word {
    /**
     * A particular word.
     */
    private String word;
    
    /**
     * The indexes of letters that has been guessed in a specific word.
     */
    private boolean[] guessedIndexes;
    
    /**
     * True if the Word was guessed, false otherwise.
     */
    private boolean guessed;

    public Word(String value){
        setWord(value);
        guessed = false;
        this.guessedIndexes = new boolean[word.length()];
    }
    
    /**
     * Looks for a specified letter in a word and notes found ones.
     * @param letter A letter that's used for checking.
     * @return True if the guessed letter was in the word, false otherwise.
     */
     public boolean checkLetter(char letter){
        int index = 0;
        boolean check = false;
        
        while( (index = word.indexOf(letter,index)) != -1){
            guessedIndexes[index] = true;
            index++;
            check = true;
        }
        
        checkWord(); // Checks the entire word after every letter check.
        return check;
    }
     
     /**
      * Checks whether a word has been guessed.
      * @return True for a guessed word, false otherwise.
      */
     private boolean checkWord(){
         for(boolean letter : this.guessedIndexes){
             if(letter == false)
                 return false;
         }
         return this.guessed = true;
     }
     
     /**
      * Sets trimmed word variable to a given value
      * @param value A walue that will be set to a word.
      */
    private void setWord(String value){
        value = value.trim();
        this.word = value;
    }
    
    /**
     * The word literal used in a Word object.
     * @return String representation of a word.
     */
    public String getWord(){
        return this.word;
    }
    
    /**
     * Informs about a word having been guessed or not.
     * @return True if guessed, false otherwise.
     */
    public boolean isGuessed(){
        return this.guessed;
    }
    
    @Override
    public String toString(){
        String text = getWord() + "\n";
        for(int i = 0; i < this.guessedIndexes.length ; i++){
            text += i + ": " + guessedIndexes[i] + "\n";
        }
        return text;
    }
     
}
