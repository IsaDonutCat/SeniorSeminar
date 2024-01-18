import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Tester
{
    public static ArrayList<Student> seniors = new ArrayList<Student>();
    public static ArrayList<Seminar> semis = new ArrayList<Seminar>();

    public static void main (String[] args)
    {
        try
        {
            if (args.length != 2)
            {
                System.out.println("java Tester students.csv seminars.csv");
                return;
            }

            File rawStudents = new File(args[0]);
            Scanner inptr = new Scanner(rawStudents);
            String[] row;

            while (inptr.hasNextLine())
            {
                row = inptr.nextLine().split(",");
                if (row.length == 7)
                    seniors.add(new Student(row[0], row[1], row[2], row[3], row[4], row[5], row[6]));
                else
                    seniors.add(new Student(row[0], row[1]));
            }

            inptr.close();

            File rawSeminars = new File(args[1]);
            inptr = new Scanner(rawSeminars);

            while (inptr.hasNextLine())
            {
                
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: File could not be found");
            e.printStackTrace();
            return;   
        }
    }
}