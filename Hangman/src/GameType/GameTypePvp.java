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
public class GameTypePvp extends GameType{

    public GameTypePvp() {
       super(2,10,"PVP");
    }
    
    
    @Override
    public final int changeScore(Player[] players, int playerIndex, boolean raise) {
        if(playerIndex < playersAmount && raise)
            players[playerIndex].raiseScore();
        if(playerIndex == 1) return 0;
        else return 1;
    }
    

    
}
