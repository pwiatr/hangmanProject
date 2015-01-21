/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.Engine;

import Word.WordList;
import Word.Word;
import GameDifficulty.GameDifficulty;
import GameType.GameType;
import static hangman.Engine.GameLogic.GameStatus.*;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author P
 */
public class GameLogic {

    public enum GameStatus {

        /**
         * Wrong guess, mark an error.
         */
        WORSEN,
        /**
         * Totally reset the game (guess counter, points, eveyrhting).
         */
        RESET,
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
         * Nothing happens or the letter has been guessed.
         */
        NONE
    }

    /**
     * The step in hanging process.
     */
    private int hangmanStep = 0;

    private boolean failedAWord = false;

    /**
     * Enumeration variable with the actual game situation.
     */
    private GameStatus hangmanStatus;
    private GameDifficulty gameDifficulty;
    private GameType gameType;
    private final GameWordLogic gameWordLogic;
   
    private int gameWordsDone = 0;
    private int gameWordsGuessed = 0;

    private Player[] players;
    public int whichPlayer = 0;
    
    public GameLogic(GameType gType, GameDifficulty gDiff, Player[] gPlayers) {
        setType(gType);
        setDifficulty(gDiff);
        setPlayers(gPlayers);
        
        gameWordLogic = new GameWordLogic();
        
        System.out.println(toString());
    }

    public void NewGame(/*Word ... words */) {
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
                    gameWordLogic.setWordIndex(gameWordLogic.getWordIndex()+1);
                    isGameFinished();
                } else if (gameWordLogic.isGuessedLetter() == false) {
                    if (hangmanStep > 11) { // Too much errors - fail!
                        changeGameStatus(FAIL);
                        gameWordsDone += 1;
                        failedAWord = true;
                        isGameFinished();
                    } else {
                        changeGameStatus(WORSEN);

                        if (hangmanStep > 11) { // Too much errors = fail
                            changeGameStatus(FAIL);
                            failedAWord = true;
                            gameWordsDone += 1;
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
            case "MULTIPLE": {
                if(gameWordsDone == wordsAmount){
                    if (gameWordsGuessed == wordsAmount) {
                        changeGameStatus(WINGAME);
                        return true;
                    }
                }
                else if (failedAWord) 
                {
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
                    return true;
                }
                else if(gameWordsGuessed == wordsAmount) { 
                    changeGameStatus(WINGAME);
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
    public int getHangmanStep() {
        return hangmanStep;
    }

    public GameStatus getHangmanStatus() {
        return hangmanStatus;
    }
    
    public Player[] getPlayers() {
        return players;
    }
        
    public GameStatus getGameStatus() {
        return this.hangmanStatus;
    }
    
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
