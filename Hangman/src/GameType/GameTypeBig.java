/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameType;

import Database.DBFetch;

/**
 * Big game type.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class GameTypeBig extends GameType{

    /**
     * Constructs a new GameTypeBig object with specified word amount.
     * @param gameCategory The chosen category used to get the amount of words.
     */
    public GameTypeBig(String gameCategory) {
        super(1,DBFetch.fetchWordsAmount(gameCategory),"BIG");
    }
    
}
