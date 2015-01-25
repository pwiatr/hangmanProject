package GameDifficulty;

/**
 * The difficulty of a game.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class GameDifficulty {
    /**
     * The name of a difficulty.
     */
    private final String difficultyName;
    
    /**
     * The jump caused by failed guess (easy - 1, medium - 2, hard - 3)
     */
    private final int difficultyJump;
    
    /**
     * Constructs GameDifficulty object with specified name and jump.
     * @param diffName The name of a difficulty.
     * @param diffJump The amount of hangman jump.
     */
    public GameDifficulty(String diffName, int diffJump){
        difficultyName = diffName;
        difficultyJump = diffJump;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters Setters">
    /**
     * Gets the string representation of difficulty name.
     * @return String contaning the difficulty name.
     */
    public String getDifficultyName() {
        return difficultyName;
    }

    /**
     * Gets the difficulty jump amount.
     * @return Difficulty jump.
     */
    public int getDifficultyJump() {
        return difficultyJump;
    }
    //</editor-fold>
    
}
