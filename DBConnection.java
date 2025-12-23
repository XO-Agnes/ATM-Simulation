import java.sql.*;
public class DBConnection 
{
    public static Connection getConnection() throws Exception
    {
        String url = "jdbc:mysql://localhost:3306/atm_db";
        String user = "root";
        String password = "2704";
        return DriverManager.getConnection(url,user,password);
    }
}
