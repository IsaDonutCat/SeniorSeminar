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
    public static int[][] totalSched = new int[5][5];
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
            while(fileR.hasNextLine())
            {
                parts = fileR.nextLine().split(",");
                //System.out.println(parts[0]);
                if (parts.length > 2)
                {
                    seniors.add(new Student(parts));
                }
                else
                {
                    seniors.add(new Student(parts[0], parts[1].strip()));
                    //System.out.println(parts[1].strip());
                }
                senNames.add(parts[1].toLowerCase().strip()); // parallel for search function
            }

            fileR.close();

            // began reading file for seminars

            File semiFile = new File(args[1]);
            fileR = new Scanner(semiFile);

            while (fileR.hasNextLine())
            {
                parts = fileR.nextLine().split(",");
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
                    System.out.println("Slot " + (r + 1) + ":");
                    for (int c = 0; c < 5; c++)
                        {
                            System.out.println(semis.get(totalSched[r][c]).toString(r));
                        }
                    System.out.println("\n\n");
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
                    semis.get(senior.getChoice(i) - 1).addChoice();
                }
            }
        }

        ArrayList<Integer> temp = new ArrayList<Integer>();
        int inser;
        for (Seminar sem : semis)
        {
            inser = temp.size();
            while(inser > 0 && semis.get(inser-1).getPrefCt() < sem.getPrefCt())
            {
                inser--;
            }
            temp.add(inser, sem.getID());
        }

        int tempctr = 0;
        
        for (int c = 0; c < 5; c++)
        {
            for (int r = 0; r < 5; r++)
            {
                if(!semis.get(temp.get(tempctr)).canAdd(r))
                {
                   tempctr++;
                   //System.out.println("temp: " + semis.get(temp.get(tempctr)).getName());
                }
                totalSched[r][c] = semis.get(temp.get(tempctr)).getID();
                //System.out.println(totalSched[r][c]);
                semis.get(temp.get(tempctr)).addSess(r,c);
            }
        }
        temp.clear(); // seminars are no longer needed after this
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
                        //System.out.println("trying to get semi @ " + st + "in " + a + " ID " + totalSched[st][a]);
                        if(semis.get(totalSched[st][a]).hasSpace(st) && semis.get(totalSched[st][a]).getID() == stu1.getChoice(st) && stu1.canAdd(st, semis.get(totalSched[st][a]).getID())) //if the seminar has space at the student wants it
                        {
                            //System.out.println("trying to add student");
                            semis.get(totalSched[st][a]).addStu(st, stu1.getName());
                            stu1.assignSemi(st, semis.get(totalSched[st][a]));
                            stu1.addedChoice(st);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void addLeft()
    {
        int sem;
            for (Student stu2 : seniors)
            {
                //System.out.println(stu2.getName() + stu2.hasEverything());
                if(!stu2.hasEverything())
                {
                    //System.out.println("doesnt have everything");
                    for (int t = 0; t < 5; t++)
                    {
                        sem = 0;
                        while (!stu2.isBusy(t))
                        {
                            
                            //System.out.println(stu2.getName() + " is not busy @ " + t);
                            //System.out.println(sem);
                            if (semis.get(totalSched[t][sem]).hasSpace(t))
                            {
                                //System.out.println("has space");
                                if (stu2.canAdd(t, sem, semis.get(totalSched[t][sem]).getName()))
                                {
                                    stu2.assignSemi(t, semis.get(totalSched[t][sem]));
                                    //System.out.println("trying to add student");
                                    semis.get(totalSched[t][sem]).addStu(t, stu2.getName());
                                    break;
                                }
                            }
                            if (sem == 4)
                            {
                                //System.out.print("error: overflow");
                                    return;
                            }
                            sem++;
                        }
                    }
                }
            }
        }
}
