/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameType;

/**
 *
 * @author P
 */
public class GameTypeMultiple extends GameType{

    public GameTypeMultiple() {
        super(1,10,"MULTIPLE");
    }
    
    public GameTypeMultiple(int wordsAmount) {
        super(1,wordsAmount,"MULTIPLE");
    }
    
    
    
}
