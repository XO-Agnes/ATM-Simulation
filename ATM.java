import java.util.*;
public class ATM
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner (System.in);
        System.out.println("Setup Incomplete,\nEntering setup....");
        System.out.println("Enter your name ");
        String name=sc.nextLine();
        System.out.println("Enter account number");
        String accno=sc.next();
        System.out.println("Please set a four digit PIN (only numericals 0-9)");
        int pin=sc.nextInt();
        System.out.println("PIN set successfully.\nPlease do not forget your PIN.");
        System.out.println("Setup Complete,\nExiting setup....");
        sc.close();
    }
}