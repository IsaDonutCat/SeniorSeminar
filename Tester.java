import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Tester
{
    public static ArrayList<Student> seniors = new ArrayList<Student>();
    public static ArrayList<Seminar> semis = new ArrayList<Seminar>();
    public static ArrayList<Seminar> sortedSemis = new ArrayList<Seminar>(); 
    public static Seminar[][] totalSched = new Seminar[5][5];
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
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: File could not be found");
            e.printStackTrace();
            return;   
        }

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

        sortSemis();
        assignSemis();
    }

    public static void sortSemis()
    {
        int index;
        for (Seminar seme : semis)
        {
            index = sortedSemis.size();

            while (index != 0 && seme.getPref() > sortedSemis.get(index-1).getPref())
            {
                index--;
            }

            sortedSemis.add(index, seme);
        }
        semis.clear();
        /*for (Seminar uke : sortedSemis)
        {
            System.out.println(uke.getName() + " " + uke.getPref());
        }*/
    }

    public static void assignSemis()
    {
        int numWant;
        int coords[] = getEmpty();
        System.out.println("coords" + coords[0] + " " + coords[1]);
        for (Seminar uke : sortedSemis)
        {
            numWant = uke.sessCt();
            numWant = Math.min(5,numWant); //know this will not be the case but just in case
            System.out.println(uke.getName());

            while (numWant > 0 && coords[0] >= 0 && uke.canAssign(coords[0]))
            {
                totalSched[coords[0]][coords[1]] = uke;
                uke.assign(coords[0],coords[1]);
                System.out.println("assigned @ " + coords[0] + " " + coords[1]);
                numWant--;
                coords = getEmpty();
                System.out.println("coords" + coords[0] + " " + coords[1]);
            }
        }

        for(int rowCt = 0; rowCt < 5; rowCt++)//for (Seminar[] rowS : totalSched)
        {
            System.out.println(rowCt);
            for(int colCt = 0; colCt < 5; colCt++)//for (Seminar colS : rowS)
                System.out.println(totalSched[rowCt][colCt].getName());
            
            System.out.println("\n\n\n");
        }

        sortedSemis.clear();
    }

    public static int[] getEmpty() //return int[2] of (row, column) for index
    {   
        int[] ans = {-1,-1};

        for (int co = 0; co < 5; co++)
        {
            for (int ro = 0; ro < 5; ro++)
            {
                if(totalSched[ro][co] == null)
                {
                    ans[0] = ro;
                    ans[1] = co;
                    
                    return ans;
                }
                System.out.println("checked" + ro + " " + co);
            }
        }

        return ans;
    } 
}