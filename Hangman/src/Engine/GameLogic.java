/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Database.DBInsert;
import Word.WordList;
import Word.Word;
import GameDifficulty.GameDifficulty;
import GameType.GameType;
import static Engine.GameLogic.GameStatus.*;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 * This class takes care of fundamental game logic.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class GameLogic {

    /**
     * Enumeration with status that represents the situation in game.
     */
    public enum GameStatus {

        /**
         * Wrong guess, mark an error.
         */
        WORSEN,
        /**
         * Lost game - the word has not been guessed.
         */
        FAIL,
        /**
         * The word has been guessed.
         */
        GUESS,
        /**
         * The game has been won.
         */
        WINGAME,
        /**
         * Someone lost the game.
         */
        LOSEGAME,
        /**
         * Only PVP - the players guessed the same amount of times.
         */
        DRAW,
        /**
         * Totally reset the game (guess counter, points, eveyrhting).
         */
        RESET,
        /**
         * Nothing happens or the letter has been guessed.
         */
        NONE
    }

    /**
     * The step in hanging process.
     */
    private int hangmanStep = 0;

    /**
     * Indicates whetver a word has not been guessed.
     */
    private boolean failedAWord = false;

    /**
     * Enumeration variable with the actual game situation.
     */
    private GameStatus hangmanStatus;
    /**
     * The actual game difficulty object.
     */
    private GameDifficulty gameDifficulty;
    /**
     * The actual type of the game object.
     */
    private GameType gameType;
    /**
     * The actual logic that takes care of words.
     */
    private final GameWordLogic gameWordLogic;


   /**
    * The amount of words that has been taken care of in a single game.
    */
    private int gameWordsDone = 0;
    /**
     * The amount of guessed words in a single game.
     */
    private int gameWordsGuessed = 0;

    /**
     * In-game players.
     */
    private Player[] players;
    /**
     * Indicates which player is about to guess (essential in PVP game).
     */
    public int whichPlayer = 0;
    
    /**
     * Constructs a new game logic object.
     * @param gType The type of the game.
     * @param gDiff The difficulty of the game.
     * @param gPlayers The in-game players.
     * @param gameCategory The category of the game.
     */
    public GameLogic(GameType gType, GameDifficulty gDiff, 
            Player[] gPlayers, String gameCategory) {
        setType(gType);
        setDifficulty(gDiff);
        setPlayers(gPlayers);
        
        gameWordLogic = new GameWordLogic(gameCategory);
        
        System.out.println(toString());
    }

    /**
     * This method resets important stuff to get a new game going.
     */
    public void NewGame() {
        hangmanStatus = GameStatus.NONE;
        hangmanStep = 0;
        
        failedAWord = false;

        gameWordsDone = 0;
        gameWordsGuessed = 0;
    }

    /**
     * Method is used for raining the player/players score.
     * @param raise True means, that the score should be raised.
     */
    public void changeScore(boolean raise) {
        whichPlayer = gameType.changeScore(players, whichPlayer, raise);
    }

    /**
     * Main logic behind checking the entire word. The moment of winning,
     * failing, making a mistake and not doing anything is managed in this
     * method.
     */
    public void checkGuessResult() {
        if (!isGameFinished()) {
            if (gameWordsDone < gameType.getWordsAmount()) {
                if (gameWordLogic.getWordList().getWord(gameWordLogic.getWordIndex()).isGuessed()) { // Guessed a Word
                    changeGameStatus(GUESS);
                    gameWordsGuessed += 1;
                    gameWordsDone += 1;
                    gameWordLogic.setRandomIndex();
                    isGameFinished();
                } else if (gameWordLogic.isGuessedLetter() == false) {
                    if (hangmanStep > 11) { // Too much errors - fail!
                        changeGameStatus(FAIL);
                        gameWordsDone += 1;
                        gameWordLogic.setRandomIndex();
                        failedAWord = true;
                        isGameFinished();
                    } else {
                        changeGameStatus(WORSEN);
                        
                        if (hangmanStep >= 11) { // Too much errors = fail
                            changeGameStatus(FAIL);
                            failedAWord = true;
                            gameWordsDone += 1;
                            gameWordLogic.setRandomIndex();
                            isGameFinished();
                        }
                    }
                } else { // Nothing changed = a letter must have been guessed
                    gameWordLogic.setGuessedLetter(false);
                    changeGameStatus(NONE);
                }
            }
        }
    }

    /**
     * Helper method for checkGuessResult(). 
     * Informs about the actual state of the game.
     * @return Boolean value true if the game is finished, false otherwise.
     */
    private boolean isGameFinished() {

        int wordsAmount = gameType.getWordsAmount();
        
        switch (gameType.getGameTypeName()) {
            case "SINGLE": {
                if(gameWordsGuessed == wordsAmount){
                    changeGameStatus(WINGAME);
                    return true;
                }
                else if(gameWordsGuessed < gameWordsDone ){
                    changeGameStatus(LOSEGAME);
                    return true;
                }
                break;
            }
            case "PVP": {
                if (gameWordsDone == wordsAmount) {
                    if (players[0].getScore() == players[1].getScore()) {
                        changeGameStatus(DRAW);
                        return true;
                    } else {
                        changeGameStatus(WINGAME);
                        return true;
                    }
                }
                break;
            }
            case "BIG": {
                if (failedAWord) { // Failing means finishing the game
                    changeGameStatus(LOSEGAME);
                    DBInsert.insertScores(players[0].getName(),
                            players[0].getScore(),
                            gameDifficulty);
                    return true;
                }
                else if(gameWordsGuessed == wordsAmount) { 
                    changeGameStatus(WINGAME);
                    DBInsert.insertScores(players[0].getName(),
                            players[0].getScore(),
                            gameDifficulty);
                    return true;
                }
                break;
            }
        }

        return false;
    }

    /**
     * Changes the actual state of the game.
     * @param gStatus GameStatus enumeration name.
     */
    public void changeGameStatus(GameStatus gStatus) {

        switch (gStatus) {
            case WORSEN: {
                hangmanStep += gameDifficulty.getDifficultyJump();
                hangmanStatus = WORSEN;
                break;
            }
            case RESET: {
                hangmanStep = 0;
                hangmanStatus = RESET;
                break;
            }
            case FAIL: {
                hangmanStatus = FAIL;
                break;
            }
            case GUESS: {
                hangmanStatus = GUESS;
                break;
            }
            case WINGAME: {
                hangmanStatus = WINGAME;
                break;
            }
            case LOSEGAME: {
                hangmanStatus = LOSEGAME;
                break;
            }
            case DRAW: {
                hangmanStatus = DRAW;
                break;
            }
            case NONE: {
                hangmanStatus = NONE;
                break;
            }
        }

    }

    
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns GameType object reference.
     * @return GameType reference.
     */
    public GameType getGameType() {
        return gameType;
    }
    /**
     * Returns the actual step in the game.
     * @return Actual step.
     */
    public int getHangmanStep() {
        return hangmanStep;
    }
    /**
     * Returns GameStatus object reference.
     * @return GameStatus object.
     */
    public GameStatus getHangmanStatus() {
        return hangmanStatus;
    }
    /**
     * Returns the actual players array.
     * @return Player objects array.
     */
    public Player[] getPlayers() {
        return players;
    }
    /**
     * Returns the actual hangman status.
     * @return Hangman status as GameStatus object.
     */
    public GameStatus getGameStatus() {
        return this.hangmanStatus;
    }
    /**
     * Returns GameWordLogic object reference.
     * @return GameWordLogic object.
     */
     public GameWordLogic getWordLogicEngine(){
        return this.gameWordLogic;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets the players.
     * @param players Player[] object used to set the players.
     */
    private void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Sets the game difficulty and the step of guessing.
     * @param gameDiff Actual GameDifficulty object.
     */
    private void setDifficulty(GameDifficulty gameDiff) {
        this.gameDifficulty = gameDiff;
    }

    /**
     * Sets the game type and the amount of words to guess.
     * @param gameType Actual GameType object.
     */
    private void setType(GameType gameType) {
        this.gameType = gameType;
    }

    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overriden from Object">
    /**
     * Representaiton of the game state.
     * @return Game state as a string.
     */
    @Override
    public final String toString() {
        return "Game type: " + gameType.getGameTypeName()
                + ", game difficulty: " + gameDifficulty.getDifficultyName()
                + ", player amount: " + gameType.getPlayersAmount()
                + ", amount of words: " + gameType.getWordsAmount()
                + "";
    }  
    // </editor-fold>

}
