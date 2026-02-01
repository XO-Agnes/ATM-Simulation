import java.util.*;
import java.sql.*;

public class ATM
{
    int bal;
    String accNo;
    String userName;

    boolean register(String acc,String name,int pin,int bal)
    {
        try
        {
            Connection con=DBConnection.getConnection();
            String sql="INSERT INTO users VALUES(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,acc);
            ps.setString(2,name);
            ps.setInt(3,pin);
            ps.setInt(4,bal);

            ps.executeUpdate();
            con.close();
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Account already exists or Error occured.");
            return false;
        }
    }

    boolean verifyPin(int pin) {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT 1 FROM users WHERE account_no = ? AND pin = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, accNo);
        ps.setInt(2, pin);

        ResultSet rs = ps.executeQuery();
        boolean result = rs.next();

        con.close();
        return result;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    boolean login(String acc,int pin)
    {
        try 
        {
            Connection con=DBConnection.getConnection();

            String sql="SELECT name,balance FROM users WHERE account_no=? AND pin=?";
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1, acc);
            ps.setInt(2,pin);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next())
            {
                userName=rs.getString("name");
                bal=rs.getInt("balance");
                accNo=acc;
                con.close();
                return true;
            }
            con.close();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    void checkBalance()
    {
        try
        {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT balance FROM users WHERE account_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, accNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                bal = rs.getInt("balance"); // refresh cache
                System.out.println("Current Balance: " + bal);
            }

            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    boolean withdraw(int w)
    {
        if (w<=0)
        {
             System.out.println("Invalid Amount");
             return false;
        }
           
        if(w>bal)
        {
            System.out.println("Insufficient Balance");
            return false;
        }
            
        try 
        {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE users SET balance = balance - ? WHERE account_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, w);
            ps.setString(2, accNo);

            ps.executeUpdate();
            con.close();

            bal -= w;
            return true;

        } 

        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
        
    }

    boolean deposit(int d) 
    {
        if (d <= 0) 
        {
            System.out.println("Invalid Amount");
            return false;
        }

        try 
        {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE users SET balance = balance + ? WHERE account_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, d);
            ps.setString(2, accNo);

            ps.executeUpdate();
            con.close();

            bal += d;  // keep Java in sync
            return true;

        } 

        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner (System.in);
        ATM atm=new ATM();
        while (true)
        {
            System.out.println("Choose the task to be performed:");
            System.out.println("1: New Account Creation");
            System.out.println("2: Login to Existing Account");
            System.out.println("3: Exit");
            int t=sc.nextInt();
            sc.nextLine();
            if (t==1)
            {
                System.out.println("Entering setup....");
                System.out.println();
                System.out.println("Enter your name ");
                String name=sc.nextLine();
                System.out.println("Enter account number");
                String accno=sc.next();
                System.out.println("Enter the intial balance");
                int bal=sc.nextInt();
                System.out.println("Please set a four digit PIN (only numericals 0-9)");
                int PIN=sc.nextInt();
                System.out.println();
                if(atm.register(accno, name, PIN, bal))
                {
                    System.out.println("Account Creation Successful,\nExiting setup....");
                    System.out.println();
                }
                else
                {
                    System.out.println("Account Creation Unsuccessful,\nExiting setup....");
                    System.out.println();
                }
            }
            
            else if(t==2)
            {
                boolean c;
                System.out.println("Login");
                System.out.print("Account Number:");
                String an=sc.next();
                System.out.print("PIN:");
                int pin=sc.nextInt(); 
                System.out.println();
                if(atm.login(an, pin))
                {
                    System.out.println("PIN verified.\nAccess granted.");
                    System.out.println();
                    
                    while (true)
                    {
                        System.out.println("Enter the operation to be performed:");
                        System.out.println("1: Check Balance");
                        System.out.println("2: Deposit Money");
                        System.out.println("3: Withdraw Money");
                        System.out.println("4: Exit");
                        System.out.println();
                        int ch=sc.nextInt();
                        System.out.println();
                        if (ch==1)
                            atm.checkBalance();
                        else if(ch==3)
                        {
                            System.out.println("Enter the amount to be withdrawn");
                            int wt=sc.nextInt();
                            c=atm.withdraw(wt);
                            if (c==true)
                                System.out.println("Withdrawal Successful");
                            else
                                System.out.println("Withdrawal Unsuccessful");
                        }
                        else if(ch==2)
                        {
                            System.out.println("Enter the amount to be deposited");
                            int de=sc.nextInt();
                            c=atm.deposit(de);
                            if (c==true)
                                System.out.println("Deposit Successful");
                            else
                                System.out.println("Deposit Unsuccessful");
                        }
                        else if(ch==4)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid Operation.\nTry again.");
                        }
                    }
                }
                else
                    System.out.println("Incorrect PIN.\nAccess Denied.\nTry again after some time.");
            }    
            
            else if(t==3)
            {
                System.out.println("Thank you for using our services.\nHave a nice day.");
                break;
            }
            else
                System.out.println("Invalid Task");
        }
        sc.close();
    }
}