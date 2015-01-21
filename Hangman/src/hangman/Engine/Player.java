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
    private String playerName;
    private int score;
    
    public Player(){
        score = 0;
    }
    
    public Player(String name){
        score = 0;
        playerName = name;
    }
    
    public void raiseScore(){
        score +=1;
    }
    
    public int getScore(){
        return score;
    }

    public String getName(){
        return playerName;
    }
    
    public void setName(String name){
        playerName = name;
    }
    
    @Override
    public String toString() {
        return "Player{" + "playerName=" + playerName + ", score=" + score + '}';
    }
    
    
}
