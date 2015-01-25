package Database;

import GUI.InfoPopup;
import GameDifficulty.GameDifficulty;
import GameType.GameType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Inserts the important data into the database.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class DBInsert extends DBConnect{
    
    /**
     * A popup used for presenting error messages.
     */
     private static InfoPopup newPopup = new InfoPopup(); 
     /**
      * An error message used throughout the methods.
      */
     private static String errMessage = "Problem połączenia z bazą.";
    
    
    public static void insertScores(String playerName, int points, 
            GameDifficulty gameDiff){
        try {
            String whichTable = "";
            String wherePoints = "";
            String whereName = "";
            switch(gameDiff.getDifficultyName()){
                case "EASY":
                {
                    whichTable = "Scores";
                    wherePoints = "EPoints";
                    whereName = "EName";
                    break;
                }
                case "MEDIUM":{
                    whichTable = "MScores";
                    wherePoints = "MPoints";
                    whereName = "MName";
                    break;
                }
                case "HARD":{
                    whichTable = "HScores";
                    wherePoints = "HPoints";
                    whereName = "HName";
                    break;
                }
            }
            // INSERT INTO `HScores`(`HPoints`, `HName`) VALUES ('10','Jarek')
            
            System.out.println(whichTable + wherePoints + whereName);
//            String insert = "INSERT INTO `HScores`(`HPoints`, `HName`) VALUES ('10','Jarek')";
            //                 INSERT INTO 'HScores'('HPoints','HName') VALUES ('1','Jarek')
                            // INSERT INTO `HScores`(`HPoints`,`HName`) VALUES (`0`,`Tomek`)
                String insert="INSERT INTO " 
                        + "\""+whichTable+"\"(\""+wherePoints+"\", \""+whereName+"\") "
                    + "VALUES ("+points+",\""+playerName+"\")";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.executeUpdate();
            
        }
        catch (Exception e) {
            System.out.println(e);
            errMessage = "Problem z dodaniem wyników do bazy.";
            newPopup.showPopup(errMessage);
        }

    };
    
}
