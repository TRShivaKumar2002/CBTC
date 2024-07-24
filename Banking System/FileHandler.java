import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandler
{
    private static final String FILENAME="bank-account-data.txt";

    public Map<String,MyAccount> loadAccounts()
    {
        Map<String,MyAccount> accounts=new HashMap<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(FILENAME)))
        {
            String line;
            while((line = reader.readLine())!=null)
            {
                String[] parts=line.split(",");
                String accountNumber=parts[0];
                String name=parts[1];
                double balance=Double.parseDouble(parts[2]);
                MyAccount account=new MyAccount(accountNumber,name,balance);
                accounts.put(accountNumber,account);

            }
        }catch(IOException | NumberFormatException e)
        {
            e.printStackTrace();
        }

        return accounts;

    }

    public void saveAccounts(Map<String,MyAccount> accounts)
    {
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(FILENAME)))
        {
            for(MyAccount account:accounts.values())
            {
               writer.write(account.getAccountNumber()+","+account.getName()+","+account.getBalance() );
               writer.newLine(); 
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}