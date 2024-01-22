import java.util.ArrayList;

public class Seminar
{
    private Presenter lect;
    private String name;
    private int srCt = 0;
    private int[] rooms = {-1,-1,-1,-1,-1};
    private int prefs = 0;

    public Seminar (String name, String presenter)
    {
        lect = new Presenter(presenter);
        this.name = name;
    }

    public void addPref()
    {
        prefs++;
    }

    public int getPref()
    {
        return prefs;
    }

    public boolean extraSess()
    {
        return (prefs%16 == 0 || prefs%16 >7);
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
        return (srCt < 16);
    } 

    public void addStudent()
    {
        srCt++;
    }
}