import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tester
{
    public static ArrayList<Student> seniors = new ArrayList<Student>();
    public static ArrayList<String> senNames = new ArrayList<String>();
    public static ArrayList<Seminar> semis = new ArrayList<Seminar>();
    public static Seminar[][] totalSched = new Seminar[5][5];
    public static void main (String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("java Tester students.csv seminars.csv");
            return;
        }

        try 
        {
            String[] parts; // for text.split
            //begin reading file for students
            File stuFile = new File(args[0]);
            Scanner fileR = new Scanner(stuFile);
            while(fileR.hasNext())
            {
                parts = fileR.next().split(",");
                if (parts.length > 2)
                {
                    seniors.add(new Student(parts));
                }
                else
                {
                    seniors.add(new Student(parts[0], parts[1]));
                }
                senNames.add(parts[1]); // parallel for search function
            }

            fileR.close();

            // began reading file for seminars

            File semiFile = new File(args[1]);
            fileR = new Scanner(semiFile);

            while (fileR.hasNext())
            {
                parts = fileR.next().split(",");
                semis.add(new Seminar(parts));
            }

            fileR.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File could not be found");
            return;
        }

        sortSemis();
        assignSemis();
    }

    public static void sortSemis() //sorts semis to be greatest to least
    {
        for (Student senior : seniors)
        {
            if (senior.madeChoices())
            {
                for (int i = 0; i < 5; i++)
                {

                }
            }
        }

        ArrayList<Seminar> temp = new ArrayList<Seminar>();
        int inser;
        for (Seminar sem : semis)
        {
            inser = temp.size();
            while(inser > 0 && semis.get(inser-1).getPrefCt() < sem.getPrefCt())
            {
                inser--;
            }
            temp.add(inser, sem);
        }

        semis.clear(); //clears current unsorted
        semis = temp; //sets semis to unsorted
        temp.clear(); //clearas temp sorting array list;
    };

    public static void assignSemis()
    {
        for (int r = 0; r < 5; r++)
        {
            for (int c = 0; c < 5; c++)
            {
                if (semis.get(0).canAdd(r))
                {
                    totalSched[r][c] = semis.get(0);
                    semis.get(0).addSess(r,c);
                }
                else
                    semis.remove(0);
            }
        }
        semis.clear(); // seminars are no longer needed after this
    }
}