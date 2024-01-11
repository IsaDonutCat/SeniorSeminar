import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Tester
{
    public static void main (String[] args)
    {
        try
        {
            if (args.length != 1)
            {
                System.out.println("java Tester data.csv");
                return;
            }

            File rawData = new File(args[0]);
            Scanner inptr = new Scanner(rawData);

            while (inptr.hasNextLine())
            {
                System.out.println(inptr.nextLine());
            }

            inptr.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: File could not be found");
            e.printStackTrace();
            return;   
        }
    }
}