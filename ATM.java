import java.util.*;
public class ATM
{
    int bal=50000;

    void checkBalance()
    {
        System.out.println("Current Balance:"+bal);
    }

    boolean withdraw(int w)
    {
        if (w<=0)
            System.out.println("Invalid Amount");
        else if(w>bal)
            System.out.println("Insufficient Balance");
        else
        {
            bal-=w;
            return true;
        }
        return false;
    }

    boolean deposit(int d)
    {
        if (d<=0)
        {
            System.out.println("Invalid Amount");
            return false;
        }
        else
        {
            bal+=d;
            return true;
        }
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner (System.in);
        ATM atm=new ATM();
        boolean c;
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
                    System.out.println("Thank you for using our services.\nHave a nice day.");
                    break;
                }
                else
                {
                    System.out.println("Invalid Operation.\nTry again.");
                }
            }
        }
        else
        {
            System.out.println("Incorrect PIN.\nAccess Denied.\nTry again after some time.");
        }
        sc.close();
    }
}