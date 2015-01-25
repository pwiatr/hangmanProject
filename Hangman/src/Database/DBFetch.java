package Database;

import GUI.GamePopup;
import GUI.GameScoresPanel.FieldType;
import GUI.InfoPopup;
import GameDifficulty.GameDifficulty;
import Word.Word;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Takes care of fetching important data from the database.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class DBFetch extends DBConnect {
    
    /**
     * A popup used for presenting error messages.
     */
     private static InfoPopup newPopup = new InfoPopup(); 
     /**
      * An error message used throughout the methods.
      */
     private static String errMessage = "Problem połączenia z bazą.";
    
     /**
      * Fetches all categories names from database.
      * @return Array of strings with names of categories.
      */
    public static String[] fetchCategories(){
       try{
            ResultSet rs = getResult("SELECT Count(DISTINCT Category) "
                    + "FROM Words");
            
            int amount = 0;
            while(rs.next()){
                amount = rs.getInt(rs.getRow());
            }
            
            rs = getResult("SELECT DISTINCT Category FROM Words");

            String[] categories = new String[amount];
            
            int iter = 0;
            while(rs.next()){
                categories[iter] = rs.getString("Category");
                iter++;
            }
            
            return categories;
        }
        catch(Exception e){
            newPopup.showPopup(errMessage);
            return null;
        }
    }
    
    /**
     * Fetches all player names from scores tables.
     * @param fieldType The type of field to fetch scores from.
     * @return Array of strings containing the names.
     */
    public static String[] fetchNames(FieldType fieldType){
          try{
            String[] points;
            String letter = "";
            String what = "";
            String from = "";
            switch(fieldType){
                case EASY:
                {
                    letter = "E";
                    what = "EName";
                    from = "Scores";
                    break;
                }
                case MEDIUM:{
                    letter = "M";
                    what = "MName";
                    from = "MScores";
                    break;
                }
                case HARD:{
                    letter = "H";
                    what = "HName";
                    from = "HScores";
                    break;
                }
            }
            

            ResultSet rs = getResult("SELECT "+what+" FROM "+from + 
                    " ORDER BY "+letter+"Points DESC");
            
            int amount = 0;
            while(rs.next()){
                amount++;
            }
            
            points = new String[amount];
            
            rs = getResult("SELECT "+what+" FROM "+from 
                    + " ORDER BY "+letter+"Points DESC");
            
            
            int iter = 0;
            while(rs.next()){
                points[iter] = rs.getString(what);
                iter++;
            }
          
            return points;
        }
        catch(Exception e){
            newPopup.showPopup(errMessage);
            String[] empty = {"---"};
            return empty;
        }
    }
    
    /**
     * Fetches all players points from scores tables.
     * @param fieldType The type of field to fetch scores from.
     * @return Array of strings containing the points.
     */
    public static String[] fetchPoints(FieldType fieldType){
        try{
            String[] points;
            String what = "";
            String from = "";
            switch(fieldType){
                case EASY:
                {
                    what = "EPoints";
                    from = "Scores";
                    break;
                }
                case MEDIUM:{
                    what = "MPoints";
                    from = "MScores";
                    break;
                }
                case HARD:{
                    what = "HPoints";
                    from = "HScores";
                    break;
                }
            }
            
            ResultSet rs = getResult("SELECT "+what+" FROM "+from + 
                    " ORDER BY "+what+" DESC");
            
            int amount = 0;
            while(rs.next()){
                amount++;
            }
            
            points = new String[amount];
            
            rs = getResult("SELECT "+what+" FROM "+from + 
                    " ORDER BY "+what+" DESC");
            
            
            int iter = 0;
            while(rs.next()){
                points[iter] = rs.getString(what);
                iter++;
            }
          
            return points;
        }
        catch(Exception e){
            newPopup.showPopup(errMessage);
            String[] empty = {"---"};
            return empty;
        }
    }
    
    /**
     * Fetches all the words with a specified category from database.
     * @param category The category to fetch the words from.
     * @return Array of Word objects with words.
     */
    public static Word[] fetchWords(String category){
         try{
            ResultSet rs = getResult("SELECT Count(*) FROM Words WHERE "
                    + "Category='"+category+"'");
            
            int amount = 0;
            while(rs.next()){
                amount = rs.getInt(rs.getRow());
            }
            
            rs = getResult("SELECT * FROM Words "
                             + "WHERE Category='"+category+"'");

            Word[] aWord = new Word[amount];
            
            int iter = 0;
            while(rs.next()){
                aWord[iter] = new Word(rs.getString("Word"));
                iter++;
            }
            
            return aWord;
        }
        catch(Exception e){
            newPopup.showPopup(errMessage);
            return null;
        }
    }
    
    /**
     * Fetches all the words.
     * @return Array of Word objects with words.
     */
    public static Word[] fetchWords(){
         try{
            ResultSet rs = getResult("SELECT Count(*) FROM Words ");
            
            int amount = 0;
            while(rs.next()){
                amount = rs.getInt(rs.getRow());
            }
            
            rs = getResult("SELECT * FROM Words");

            Word[] aWord = new Word[amount];
            
            int iter = 0;
            while(rs.next()){
                aWord[iter] = new Word(rs.getString("Word"));
                iter++;
            }
            
            return aWord;
        }
        catch(Exception e){
            newPopup.showPopup("Problem połączenia z bazą.");
            return null;
        }
    }
     
    /**
     * Fetches the amount of words in a specified category.
     * Category "Wszystko" represents all words in database.
     * @param category The category to fetch the words from.
     * @return int value containing the amount of words.
     */
    public static int fetchWordsAmount(String category){
         try{
             String queryCategory = "";
             if(category.equalsIgnoreCase("Wszystko")){
                 queryCategory = "";
             }
             else {
                 queryCategory = "WHERE Category='"+category+"'";
             }
            ResultSet rs = getResult("SELECT Count(*) FROM Words "
                    + queryCategory );
            
            int amount = 0;
            while(rs.next()){
                amount = rs.getInt(rs.getRow());
            }
            
            return amount;
         }
        catch(Exception e){
            newPopup.showPopup("Problem połączenia z bazą.");
            return 0;
        }
    }
    
    /**
     * Helper method for getting data from database.
     * @param sqlQuery A sql query to submit on a database.
     * @return ResultSet containing the data from processed statement.
     * @throws SQLException Thrown when there are problems with database.
     */
    private static ResultSet getResult(String sqlQuery) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        return ps.executeQuery();
    }
    
}
