/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.Engine;

/**
 *
 * @author P
 */
public class Player {
    private final String playerName;
    private int score;
    
    public Player(String name){
        playerName = name;
        score = 0;
    }
    
    public void raiseScore(){
        score +=1;
    }
    
    public int getScore(){
        return score;
    }
}
