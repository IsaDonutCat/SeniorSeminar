import java.util.ArrayList;

public class Seminar
{
    private Presenter lect;
    private String name;
    private int room;
    private int prefs = 0;
    private ArrayList<String> attendees = new ArrayList<String>();

    public int getPref()
    {
        return prefs;
    }

    public Seminar (String name, String presenter)
    {
        lect = new Presenter(presenter);
        this.name = name;
    }

    public boolean equals (String nameComp)
    {
        return nameComp.equals(name);
    }

    public void addPref()
    {
        prefs++;
    }

    public int sessCt()
    {
        if (prefs % 16 >= 5)
            return prefs/16 + 1;
        else
            return prefs/16;
    }

    public boolean extraSess()
    {
        return (prefs % 16 == 0 || prefs % 16 > 7);
    }

    public boolean canAssign(int slotTime)
    {
        if (lect.isBusy(slotTime))
            return false;
        else
            return true;
    }

    public void assign(int slotTime, int roomNum)
    {
        lect.assign(this, slotTime);
        room = roomNum;
    }

    public String getName()
    {
        return name;
    }

    public int getRoom()
    {return room;}

    public boolean hasSpot()
    {
        return (attendees.size() < 16);
    } 

    public void addStudent(String stuName)
    {
        attendees.add(stuName);
    }

    public String prin(int roomNum)
    {
        if (attendees.isEmpty())
            return "This seminar does not exist or is not scheduled. ";
        String build = "";
        build = name + " in Room " + roomNum + "\n";
        build += "Presenter: "+lect.getName() + "\n";
        build += "Students:\n";

        for (String stu: attendees)
        {
            build += stu + "\n";
        }

        return build;
    }
}