import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber
{
    private static final int MAX_ATTEMPTS=10;
    private static final Random RANDOM =new Random();

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String name=sc.next();
        boolean playAgain;

        do{
            int numberToGuess=RANDOM.nextInt(100)+1;
            int attempts=0;
            boolean hasGuessedCorrectly = false;

            System.out.println("Guess The Number(Between 1 and 100):");

            while(attempts<MAX_ATTEMPTS && !hasGuessedCorrectly)
            {
                int userGuess=sc.nextInt();
                attempts++;
                if(userGuess==numberToGuess)
                {
                    System.out.println("Congratulations! You've guessed the number");
                    hasGuessedCorrectly=true;
                }
                else if(userGuess<numberToGuess)
                {
                    System.out.println("Higher!Try Again");
                }
                else{
                    System.out.println("Lower! Try Again");
                }
            }

            if(!hasGuessedCorrectly)
            {
                System.out.println("Sorry,you've used all attempts.The Number was:" + numberToGuess);
            }

            saveRes(name,attempts,hasGuessedCorrectly);

            System.out.println("Play Again ? yes/No");
            playAgain=sc.next().equalsIgnoreCase("YES");
        }while(playAgain);

        sc.close();

        }


     private static void saveRes(String username,int attempts,boolean hasGuessedCorrectly)
       {
        int score =hasGuessedCorrectly? MAX_ATTEMPTS-attempts+1 : 0;
        String sql="INSERT INTO game_results(username,score,attempts) VALUES ( , , )";
        try(Connection conn= DatabaseConnector.connect(); PreparedStatement pstmt=conn.prepareStatement(sql))
        {
            pstmt.setString(1,username);
            pstmt.setInt(2,score);
            pstmt.setInt(3,attempts);
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

       } 
    }
