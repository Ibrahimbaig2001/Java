import java.sql.Connection;
public class DatabaseTest {
    public static void main(String[] args){
        try{
            Connection connection = DBConnection.getConnection();
            if(connection != null){
                System.out.println("Connection successful!");
                connection.close();
            }

        } catch(Exception e){
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
