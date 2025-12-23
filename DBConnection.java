import java.sql.*;
import 
java.util.*;
import java.io.FileInputStream;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("db.properties");
        props.load(fis);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}
