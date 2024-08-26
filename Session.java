import java.util.ArrayList;

public class Session 
{

    int roomNum;
    public Session(int room)
    {
        roomNum = room;
    }

    ArrayList<Student> attendees = new ArrayList<Student>();

    public void addStudent(Student stuName)
    {
        //System.out.println(stuName);
        attendees.add(stuName);
    }

    public int alsoWants(int semi)
    {
        int ans = 0;
        for (Student stu : attendees)
        {
            if (stu.hasPrefFor(semi))
                ans++;
        }

        return ans;
    }

    public int getStuCt()
    {
        return attendees.size();
    }

    public int getRoom()
    {
        return roomNum;
    }

    public String toString()
    {
        String build = "Room " + (roomNum + 101) + "\n";

        for (Student stu : attendees)
        {
            build += stu.getName() + "\n";
        }
        return build;
    }
}
