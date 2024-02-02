import java.util.ArrayList;

public class Session 
{

    int roomNum;
    public Session(int room)
    {
        roomNum = room;
    }

    ArrayList<String> attendees = new ArrayList<String>();

    public void addStudent(String stuName)
    {
        attendees.add(stuName);
    }

    public boolean hasSpace()
    {
        return attendees.size() < 16;
    }

    public int getRoom()
    {
        return roomNum;
    }

    public String toString()
    {
        String build = "Room " + (roomNum + 101) + "\n";

        for (String stu : attendees)
        {
            build += "\t" + stu + "\n";
        }
        return build;
    }
}
