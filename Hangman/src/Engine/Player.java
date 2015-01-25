package Engine;

/**
 * The class holds the players with their information.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class Player {
    /**
     * Player name;
     */
    private String playerName;
    /**
     * Player's score.
     */
    private int score;
    
    /**
     * Constructs a new player with score set to 0.
     */
    public Player(){
        score = 0;
    }
    
    /**
     * Constructs a new player with specified name and score set to 0.
     * @param name The player's name.
     */
    public Player(String name){
        score = 0;
        playerName = name;
    }
    
    /**
     * Raises the player's score.
     */
    public void raiseScore(){
        score +=1;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    /**
     * Returns player's score as integer value.
     * @return Score as integer.
     */
    public int getScore(){
        return score;
    }

    /**
     * Returns the player's name.
     * @return Player name as a String.
     */
    public String getName(){
        return playerName;
    }
    
    /**
     * Sets player's name.
     * @param name Player name to be set.
     */
    public void setName(String name){
        playerName = name;
    }
    //</editor-fold>
    
    /**
     * Returns information about the player (name and score).
     * @return String information about the player.
     */
    @Override
    public String toString() {
        return "Player{" + "playerName=" + playerName + ", score=" + score + '}';
    }
    
    
}
