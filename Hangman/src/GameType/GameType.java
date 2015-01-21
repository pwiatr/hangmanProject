/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameType;

import hangman.Engine.Player;

/**
 *
 * @author P
 */
public abstract class GameType {
    int playersAmount;
    int wordsAmount;
    String gameTypeName;
    
    public GameType(int pA, int wA, String gTypeName){
        playersAmount = pA;
        wordsAmount = wA;
        gameTypeName = gTypeName;
    }

    public String getGameTypeName() {
        return gameTypeName;
    }

    public int getPlayersAmount() {
        return playersAmount;
    }

    public int getWordsAmount() {
        return wordsAmount;
    }
    
    
    public int raiseScore(int scoreVariable) {
        return scoreVariable + 1;
    }
    
    public int changeScore(Player[] players,int playerIndex, boolean raise) {
        if(raise) players[0].raiseScore();
        return 0;
    }
    
}
