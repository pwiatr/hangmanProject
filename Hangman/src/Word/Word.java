package Word;

/**
 * Word class represents a word that can be guessed throughout the game.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
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

    /**
     * Constructs a new Word object provided a text it should contain.
     * @param value The text that is a word.
     */
    public Word(String value){
        setWord(value);
        guessed = false;
        this.guessedIndexes = new boolean[word.length()];
    }
    
    /**
     * Looks for a specified letter in a word and marks the found ones.
     * @param letter A letter that's being checked.
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
      * The word and it's guessed indexes.
      * @return String representation of Word object.
      */
    @Override
    public String toString(){
        String text = getWord() + "\n";
        for(int i = 0; i < this.guessedIndexes.length ; i++){
            text += i + ": " + guessedIndexes[i] + "\n";
        }
        return text;
    }
    
     //<editor-fold defaultstate="collapsed" desc="Getters Setters">
     
     
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
    //</editor-fold>
     
}
