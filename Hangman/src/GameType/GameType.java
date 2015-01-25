package GameType;

import Engine.Player;

/**
 * Abstract class containing information about the game type.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public abstract class GameType {
    /**
     * The amount of game players.
     */
    private final int playersAmount;
    /**
     * The amount of game words.
     */
    private final int wordsAmount;
    /**
     * The name of the game type.
     */
    private final String gameTypeName;
    
    /**
     * Constructs new GameType object with specified amount of players,
     * words and the game type name.
     * @param pA The amount of players.
     * @param wA The amount of words.
     * @param gTypeName The name of the game type.
     */
    public GameType(int pA, int wA, String gTypeName){
        playersAmount = pA;
        wordsAmount = wA;
        gameTypeName = gTypeName;
    }

    /**
     * Gets the game type name.
     * @return The game type name string.
     */
    public String getGameTypeName() {
        return gameTypeName;
    }

    /**
     * Gets the amount of players.
     * @return The game players amount.
     */
    public int getPlayersAmount() {
        return playersAmount;
    }

    /**
     * Gets the amount of used words.
     * @return The game words amount.
     */
    public int getWordsAmount() {
        return wordsAmount;
    }
    
    
    /**
     * Method used for raising a score provided a variable.
     * @param scoreVariable The score variable to raise.
     * @return Raised score.
     */
    public int raiseScore(int scoreVariable) {
        return scoreVariable + 1;
    }
    
    /**
     * Method used for changing the score of a player.
     * @param players Array containing Player objects.
     * @param playerIndex The index of player whose score is about to be changed.
     * @param raise True if the score should be raised.
     * @return The number of player.
     */
    public int changeScore(Player[] players,int playerIndex, boolean raise) {
        if(raise) 
            players[0].raiseScore();
        return 0;
    }
    
}
