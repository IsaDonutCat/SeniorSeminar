import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tester
{
    public static ArrayList<Student> seniors = new ArrayList<Student>();
    public static ArrayList<String> senNames = new ArrayList<String>();
    public static ArrayList<Seminar> semID;
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
                senNames.add(parts[1].toLowerCase()); // parallel for search function
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
        addChoices(); // add the choices that students wanted
        addLeft(); //add the leftovers

        Scanner intro = new Scanner(System.in);
        String next;

        do
        {
            System.out.println("Actions: \n \t Find Student \n \t Print Master Roster \n \t Exit");
            next = intro.nextLine().toLowerCase();
            if (next.equals("find student"))
            {
                System.out.print("Please enter student name: ");
                next = intro.nextLine().toLowerCase();
                if (senNames.indexOf(next) < 0)
                    System.out.println("Error: Student not found");
                else
                    System.out.println(seniors.get(senNames.indexOf(next)));
            }
            else if (next.equals("print master roster"))
            {
                for (int r = 0; r < 5; r++)
                {
                    for (int )
                }
            }
            else if (!next.equals("exit"))
            {
                System.out.println("Error: Action not possible");
            }
        }
        while (!next.equals("exit"));
        intro.close();
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
        for (int c = 0; c < 5; c++)
        {
            for (int r = 0; r < 5; r++)
            {
                if (semis.get(0).canAdd(r))
                {
                    totalSched[r][c] = semis.get(0).getID();
                    semis.get(0).addSess(r,c);
                }
                else
                    semis.remove(0);
            }
        }
        semis.clear(); // seminars are no longer needed after this
    }

    public static void addChoices()
    {
        for (int st = 0; st < 5; st++) //cycle thru all 1st choices, then 2nd, etc etc
        {
            for (Student stu1 : seniors)
            {
                if (stu1.madeChoices())
                {
                    for (int a = 0; a < 5; a++)
                    {
                        if(totalSched[st][a].hasSpace(st) && totalSched[st][a].getID() == stu1.getChoice(st)) //if the seminar has space at the student wants it
                        {
                            totalSched[st][a].addStu(st, stu1.getName());
                            stu1.assignSemi(st, totalSched[st][a]);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void addLeft()
    {
        for (int st = 0; st < 5; st++)
        {
            for (Student stu2 : seniors)
            {
                if(!stu2.hasEverything())
                {
                    for (int t = 0; t < 5; t++)
                    {
                        if (!stu2.isBusy(t))
                        {
                            for (int sem = 4; sem == 0; sem--)
                            {
                                if (totalSched[t][sem].hasSpace(t))
                                {
                                    stu2.assignSemi(t, totalSched[t][sem]);
                                    totalSched[t][sem].addStu(t, stu2.getName());
                                    break;
                                }
                                if (sem == 0)
                                {
                                    System.out.print("error: overflow")
                                        return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}