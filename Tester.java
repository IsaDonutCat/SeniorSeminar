import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Tester
{

    /**
     * Main method
     * Should call and initialize everything
     * @param args
     */
    public static ArrayList<Student> seniors = new ArrayList<Student>();
    public static ArrayList<Seminar> seminars = new ArrayList<Seminar>();
    public static ArrayList<String> stuNames = new ArrayList<String>();
    public static ArrayList<Presenter> lecturers = new ArrayList<Presenter>();
    public static ArrayList<String> lectNames = new ArrayList<String>();
    public static int[][] sessIDs = {{-1,-1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1}};
    public static void main (String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("java Tester students.csv seminars.csv");
            return;
        }

        try
        {
            File stuFile = new File(args[0]);
            Scanner fileIn =  new Scanner(stuFile);

            String[] studentIn;
            while (fileIn.hasNextLine())
            {
                studentIn = fileIn.nextLine().split(",");
                seniors.add(new Student(studentIn));
                stuNames.add(studentIn[1]);
                System.out.println(studentIn[1]);
            }
            fileIn.close();

            File semiFile = new File(args[1]);
            fileIn = new Scanner(semiFile);
            String[] semiIn;
            while (fileIn.hasNextLine())
            {
                semiIn = fileIn.nextLine().split(",");
                seminars.add(new Seminar(semiIn));
                if (!lectNames.contains(semiIn[2]))
                {
                    lectNames.add(semiIn[2]);
                    lecturers.add(new Presenter(semiIn[2]));
                }
            }

            fileIn.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File could not be found.");
            return;
        }

        countPrefs();
        assignSessions();

        /*for (int o = 0; o < 5; o++)
        {
            for(int p = 0; p < 5; p++)
            {
                System.out.print(sessIDs[o][p] + "\t");
            }
            System.out.println();
        }*/
        addLeft();
        Scanner inptr = new Scanner(System.in);
        String nextLine;
        do
        {
            System.out.println("FIND STUDENT");
            System.out.println("FIND PRESENTER");
            System.out.println("PRINT MASTER ROSTER");

            nextLine = inptr.nextLine().toLowerCase();

            if (nextLine.contains("student"))
            {
                System.out.print("ENTER STUDENT'S NAME:");
                nextLine = inptr.nextLine().strip();
                if (ignoreCaseStu(nextLine) == -1)
                    System.out.println("STUDENT COULD NOT BE FOUND");
                else
                    System.out.println(seniors.get(ignoreCaseStu(nextLine)));
            }
            else if (nextLine.contains("presenter"))
            {
                System.out.print("ENTER PRESENTER'S NAME:");
                nextLine = inptr.nextLine().strip();
                if (ignoreCaseLect(nextLine) == -1)
                    System.out.println("PRESENTER COULD NOT BE FOUND");
                else
                    System.out.println(lecturers.get(ignoreCaseLect(nextLine)));
            }
            else if (nextLine.contains("roster"))
            {
                for (int t = 0; t < 5; t++)
                {
                    for (int u = 0; u < 5; u++)
                    {
                        System.out.println(seminars.get(sessIDs[t][u]).getSess(t));
                    }
                }
            }
        }
        while (!nextLine.equalsIgnoreCase("exit"));
        inptr.close();
    }

    public static int ignoreCaseStu(String name)
    {
        int len = stuNames.size();
        for (int v = 0; v < len; v++)
        {
            System.out.println(stuNames.get(v));
            if (stuNames.get(v).equalsIgnoreCase(name))
            {
                return v;
            }
        }

        return -1;
    }

    public static int ignoreCaseLect(String name)
    {
        int len = lectNames.size();
        for (int v = 0; v < len; v++)
        {
            if (lectNames.get(v).equalsIgnoreCase(name))
            {
                return v;
            }
        }

        return -1;
    }
    /**tallies up the prefs of all the students */
    public static void countPrefs()
    {
        for (Student f : seniors)
        {
            if (f.hadPrefs())
            {
                for (int g = 0; g < 5; g++)
                {
                    seminars.get(f.getPref(g)).addPref();
                }
            }
        }
    }

    public static void assignSessions()
    {
        ArrayList<Seminar> sorted = new ArrayList<Seminar>();
        int sortEnd;
        for (Seminar h : seminars)
        {
            sortEnd = sorted.size();
            while (sortEnd != 0)
            {
                if (h.getPrefCt() <= sorted.get(sortEnd-1).getPrefCt())
                {
                    break;
                }
                else
                {
                    sortEnd--;
                }
            }
            sorted.add(sortEnd, h);
        }
        int time;
        int room;
        int curs;
        for (int l = 0; l < 25; l++)
        {
            //System.out.println(l);
            time = bestTime(sorted.get(0).getId());
            room = findEmptyRoom(time);
            sessIDs[time][room]= sorted.get(0).getId();
            lecturers.get(lectNames.indexOf(sorted.get(0 ).getLectName())).assign(time, sorted.get(0).getName(), room);
            sorted.get(0).addSession(time, room);


            curs = 0;
            while (curs < sorted.size() && sorted.get(0).canAddStudent(time))
            {
                //System.out.println("in adding student while loop");
                if (seniors.get(curs).canAdd(time, sorted.get(0).getId()) && seniors.get(curs).hasPrefFor(sorted.get(0).getId()))
                {
                    ///System.out.println("added student");
                    seniors.get(curs).assignSession(time, room, sorted.get(0));
                    sorted.get(0).getSess(time).addStudent(seniors.get(curs));
                }
                curs++;
            }

            if (!sorted.get(0).canAddSession())
            {   
                //System.out.println(sorted.get(0).getName());
                sorted.remove(0);
            }
        }
    }

    public static int bestTime(int semiID)
    {
        int time = -1;
        for (int i = 0; i < 5; i++)
        {
            //System.out.println(lecturers.get(lectNames.indexOf(seminars.get(semiID).getLectName())).isBusy(i));
            if (findEmptyRoom(i) != -1 && !lecturers.get(lectNames.indexOf(seminars.get(semiID).getLectName())).isBusy(i))
            {
                time = i;
                break;
            }
        }
        int minConflicts = Integer.MAX_VALUE;
        int temp;
        int sessCt;
        for (int m = 0; m < 5; m++)
        {
            temp = 0;
            sessCt = 0;
            for (int n = 0; n < 5; n++)
            {   
                
                if (sessIDs[m][n] == -1)
                {
                    break;
                }
                sessCt++;
                temp += seminars.get(sessIDs[m][n]).getSess(m).alsoWants(semiID);
            }
            if (temp < minConflicts && sessCt < 5 && !lecturers.get(lectNames.indexOf(seminars.get(semiID).getLectName())).isBusy(m))
            {
                minConflicts = temp;
                time = m;
            }
        }
        return time;
    }

    public static int findEmptyRoom(int time)
    {
        for (int i = 0; i < 5; i++)
        {
            if (sessIDs[time][i] == -1)
                return i;
        }
        return -1;
    }

    public static int[] findSess(int searchVal)
    {
        for (int j = 0; j < 5; j++)
        {
            for (int k = 0; k < 5; k++)
            {
                if (sessIDs[j][k] == searchVal)
                {   
                        int[] temp = {j,k};
                        return temp;
                }
            }
        }
        int[] temp = {-1,-1};
        return temp;
    }

    public static void addLeft()
    {
        for (Student q : seniors)
        {
            if (q.hasEmpty())
            {
                for (int r = 0; r < 5; r++)
                {
                    if (!q.hasSess(r))
                    {
                        for (int s = 0; s < 5; s++)
                        {
                            if (seminars.get(sessIDs[r][s]).canAddStudent(r) && q.canAdd(r, sessIDs[r][s]))
                            {
                                seminars.get(sessIDs[r][s]).addStudent(r, q);
                                q.assignSession(r, s, seminars.get(sessIDs[r][s]));
                            }
                        }
                    }
                }
            }
        }
    }
}
