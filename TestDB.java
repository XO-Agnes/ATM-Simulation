import java.sql.*;
public class TestDB 
{
    public static void main(String[] args)
    {
        try 
        {
            String url="jdbc:mysql://localhost:3306/atm_db";
            String user="root";
            String password="2704";

            Connection con=DriverManager.getConnection(url,user,password);
            System.out.println("Connected to MySQL successfully!");

            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT account_no,name,balance FROM users");

            System.out.println("Accounts in database:");
            while(rs.next())
            {
                String acc=rs.getString("account_no");
                String name=rs.getString("name");
                int bal=rs.getInt("balance");
                System.out.println(acc+"|"+name+"|"+bal);
            }
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
