package Database;

import GUI.InfoPopup;
import java.sql.*;

/**
 * Takes care of connecting with the database.
 * @author Jakub Włodarz i Przemysław Pędziwiatr
 */
public class DBConnect {
    /**
     * Connection object used for connecting with database.
     * It's available in the entire application (thanks to static).
     */
    public static Connection conn= Connect_to_Db();
    
    
     
     
    /**
     * Tries to connect to the database and returns the connection.
     * @return Connection to database.
     */
    public static final Connection Connect_to_Db(){
        try{
            String host = "jdbc:mysql://22270.m.tld.pl/baza70_projekt";
            String uzytkownik = "admin70_projekt";
            String haslo = "Baza2015"; 
            Connection conn =DriverManager.getConnection(host,uzytkownik,haslo);
            return conn;
        }catch (Exception e){
             InfoPopup info = new InfoPopup();
             info.showPopup("Problem połączenia z bazą.");
             return null;
            }
    }
}
