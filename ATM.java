import java.util.*;
public class ATM
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner (System.in);
        System.out.println("Setup Incomplete,\nEntering setup....");
        System.out.println();
        System.out.println("Enter your name ");
        String name=sc.nextLine();
        System.out.println("Enter account number");
        String accno=sc.next();
        System.out.println("Please set a four digit PIN (only numericals 0-9)");
        int PIN=sc.nextInt();
        System.out.println();
        System.out.println("PIN set successfully.\nPlease do not forget your PIN.");
        System.out.println();
        System.out.println("Setup Complete,\nExiting setup....");
        System.out.println();
        System.out.println("Login");
        System.out.print("Account Number:");
        String an=sc.next();
        
        System.out.print("PIN:");
        int pin=sc.nextInt();
        System.out.println();
        if (pin==PIN)
        {
            System.out.println("PIN verified.\nAccess granted.");
            System.out.println();
            System.out.println("Enter the action to be performed:");
            System.out.println("1: Check Balance");
            System.out.println("2: Deposit Money");
            System.out.println("3: Withdraw Money");
            System.out.println("4: Exit");
            System.out.println();
        }
        else
        {
            System.out.println("Incorrect PIN.\nAccess Denied.\nTry again after some time.");
        }
        sc.close();
    }
}