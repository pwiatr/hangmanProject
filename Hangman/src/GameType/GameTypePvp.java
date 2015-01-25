/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameType;

import Engine.Player;

/**
 * Player versus player game type.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class GameTypePvp extends GameType{

    /**
     * Constructs a new GameTypePvp with set name and the amount of players 
     * and words.
     */
    public GameTypePvp() {
       super(2,10,"PVP");
    }
    
    /**
     * Method used for changing the score of a player.
     * @param players Array containing Player objects.
     * @param playerIndex The index of player whose score is about to be changed.
     * @param raise True if the score should be raised.
     * @return The number of player.
     */
    @Override
    public int changeScore(Player[] players, int playerIndex, boolean raise) {
        if(playerIndex < this.getPlayersAmount() && raise)
            players[playerIndex].raiseScore();
        if(playerIndex == 1) return 0;
        else return 1;
    }
    

    
}
