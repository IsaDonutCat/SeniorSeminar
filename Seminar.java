import java.util.ArrayList;

public class Seminar
{
    private Presenter lect;
    private String name;
    private boolean[] occupied = new boolean[5];
    private int srCt = 0;

    public Seminar (String name, String presenter)
    {
        lect = new Presenter(presenter);
        this.name = name;
    }

    public boolean canAssign(int slotTime)
    {
        if (lect.isBusy(slotTime))
            return false;
        else
            occupied[slotTime] = true;
    }

    public boolean hasRoom()
    {
        return (srCt < 16);
    } 

    public void addStudent()
    {
        srCt++;
    }
}