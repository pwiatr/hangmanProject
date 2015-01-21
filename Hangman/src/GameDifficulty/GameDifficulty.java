/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameDifficulty;

/**
 *
 * @author P
 */
public class GameDifficulty {
    private final String difficultyName;
    
    /**
     * The jump caused by failed guess (easy - 1, medium - 2, hard - 3)
     */
    private final int difficultyJump;
    
    public GameDifficulty(String diffName, int diffJump){
        difficultyName = diffName;
        difficultyJump = diffJump;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public int getDifficultyJump() {
        return difficultyJump;
    }
    
    
}
