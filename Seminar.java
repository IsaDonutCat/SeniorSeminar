import java.util.ArrayList;

public class Seminar
{
    private Presenter lect;
    private String name;
    private int[] rooms = {-1,-1,-1,-1,-1};
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

    public void addPref()
    {
        prefs++;
    }

    public int sessCt()
    {
        if (prefs % 16 >= 8)
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
        rooms[slotTime] = roomNum;
    }

    public String getName()
    {
        return name;
    }

    public int getRoom(int ind)
    {
        return rooms[ind];
    }

    public boolean hasSpot()
    {
        return (attendees.size() < 16);
    } 

    public void addStudent(String stuName)
    {
        attendees.add(stuName);
    }
}