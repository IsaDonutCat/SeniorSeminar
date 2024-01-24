import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Tester
{
    public static ArrayList<Student> seniors = new ArrayList<Student>();
    public static ArrayList<Seminar> semis = new ArrayList<Seminar>();

    public static void main (String[] args)
    {
        try
        {
            if (args.length != 2) //test to make sure two arguments exist
            {
                System.out.println("java Tester students.csv seminars.csv");
                return;
            }

            //load in the students
            File rawStudents = new File(args[0]);
            Scanner inptr = new Scanner(rawStudents);
            String[] row;

            while (inptr.hasNextLine())
            {
                row = inptr.nextLine().split(",");
                if (row.length == 7) // add the students who have choices
                    seniors.add(new Student(row[0], row[1], Integer.parseInt(row[2]), Integer.parseInt(row[3]), Integer.parseInt(row[4]), Integer.parseInt(row[5]), Integer.parseInt(row[6])));
                else //the students who didn't choose
                    seniors.add(new Student(row[0], row[1]));
            }

            inptr.close(); //close reading of this file

            //load in the seminars
            File rawSeminars = new File(args[1]);
            inptr = new Scanner(rawSeminars);

            while (inptr.hasNextLine())
            {
                    row = inptr.nextLine().split(",");
                    semis.add(new Seminar(row[0],row[1]));
            }

            inptr.close(); //close scanner for good, has finished loading. 

            for (Student x : seniors) //tallys up prefs for all sessions. 
            {
                if (x.hasChosen())
                {
                    for (int j = 0; j < 5; j++)
                    {
                        semis.get(x.getChoiceID(j)).addPref();
                    }
                }
            }


        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: File could not be found");
            e.printStackTrace();
            return;   
        }
    }

    public static void sortSemis()
    {
        
    }
}