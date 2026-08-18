import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection{
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    
    static{
        try{
            Properties properties = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(input);
            if(input == null){
                throw new RuntimeException("Unable to find application.properties.");
            }
            properties.load(input);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.username");
            PASSWORD = properties.getProperty("db.password");
        } catch(Exception e){
            throw new RuntimeException("Failed to load database configuration: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch(SQLException e){
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }

    }

}