import java.util.ArrayList;
import java.util.List;

public class MyAccount
{
    private String accountNumber;
    private String personName;
    private double accountBalance;
    private List<String> accountTransactions;

    public MyAccount(String accountNumber,String personName,double accountBalance)
    {
        this.accountNumber=accountNumber;
        this.personName=personName;
        this.accountBalance=accountBalance;
        this.accountTransactions = new ArrayList<>();

    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getName(){
        return personName;
    }

    public double getBalance()
    {
        return accountBalance;
    }

    public void deposit(double amount)
    {
        if(amount>0)
        {
            accountBalance+=amount;
            accountTransactions.add("Deposited Rs." + amount);

        }
        else
        {
            System.out.println("Deposit amount must be positive");

        }
    }

    public void withdraw(double amount)
    {
        if(amount>0)
        {
            if(accountBalance>=amount)
            {
                accountBalance-=amount;
                accountTransactions.add("Withdrawn Rs." + amount);
            }
            else{
                System.out.println("Insufficient Balance in the Account");
            }
        }
        else{
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    public void displayTransactions()
    {
        System.out.println("Transactions for account"+ accountNumber+ ":");
        for(String tra:accountTransactions)
        {
            System.out.println(tra);
        }
    }


}