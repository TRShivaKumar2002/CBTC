import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Banking
{
    private Map<String,MyAccount> accounts;
    private FileHandler fileHandler;

    public Banking()
    {
        this.accounts=new HashMap<>();
        this.fileHandler=new FileHandler();
        loadAccounts();
    }

    private void loadAccounts()
    {
        Map<String,MyAccount> loadedAccounts=fileHandler.loadAccounts();
        if(loadedAccounts!=null)
        {
            this.accounts=loadedAccounts;

        }
    }

    public void saveAccounts()
    {
        fileHandler.saveAccounts(accounts);
    }

    public boolean createAccount(String accountNumber,String name,double initialBalance)
    {
        if(accounts.containsKey(accountNumber))
        {
            System.out.println("Account already exist");
            return false;
        }

        if(initialBalance<0)
        {
            System.out.println("Initial Balance cannot be negative");
            return false;
        }

        MyAccount account=new MyAccount(accountNumber,name,initialBalance);
        accounts.put(accountNumber,account);
        saveAccounts();

        System.out.println("Account created successfully for"+ name +"with account number"+ accountNumber +".");

        return true;
    }

    public void deposit(String accountNumber, double amount) {
        MyAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            saveAccounts();
            System.out.println("Rs." + amount + " deposited successfully to account " + accountNumber + ".");
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        MyAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            saveAccounts();
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        MyAccount fromAccount = accounts.get(fromAccountNumber);
        MyAccount toAccount = accounts.get(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                saveAccounts();
                System.out.println("Rs." + amount + " transferred successfully from account " + fromAccountNumber +
                        " to account " + toAccountNumber + ".");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("One or both accounts do not exist.");
        }
    }

    public void displayBalance(String accountNumber) {
        MyAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Balance for account " + accountNumber + ": Rs." + account.getBalance());
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void displayTransactions(String accountNumber) {
        MyAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.displayTransactions();
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public static void main(String[] args) {
        Banking bank = new Banking();

     bank.createAccount("123456", "Shiva Kumar T R", 1000);
        bank.createAccount("789012", "Shiva Sankari T R", 500);

        bank.deposit("123456", 500);
        bank.withdraw("789012", 200);

        bank.transfer("123456", "789012", 300);

        bank.displayBalance("123456");
        bank.displayBalance("789012");

        bank.displayTransactions("123456");
        bank.displayTransactions("789012");

        
    }


}