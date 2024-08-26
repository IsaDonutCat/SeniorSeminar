import java.util.ArrayList;

public class Student
{
    String name;
    String email;
    int[] roomNums = { -1, -1, -1, -1, -1};
    String[] sessionNames = new String[5];
    int[] semiPrefs = { -1, -1, -1, -1, -1};
    boolean hadPrefs;
    ArrayList<Integer> sessIDs = new ArrayList<Integer>();
    /**
     * Student constructor
     * sets email, name and if there are prefs 
     * it sets them but otherwise sets hadPrefs to false
     * @param infoParts
     */

    public Student(String[] infoParts)
    {
        email = infoParts[0].strip() + "@countryday.net";
        name = infoParts[1].strip();
        if (infoParts.length >= 7)
        {
            hadPrefs = true;
            for (int a = 0; a < 5; a++)
            {
                semiPrefs[a] = Integer.parseInt(infoParts[2+a].strip()) - 1;
            }
        }
        else
        {
            hadPrefs = false;
        }
    }
    

    /**
     * Getters
     * @return
     */
    public boolean hadPrefs()
    {
        return hadPrefs;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public int getPref(int index)
    {
        return semiPrefs[index];
    }

    public boolean hasPrefFor(int value)
    {
        if (hadPrefs() && !sessIDs.contains(value))
        {
            for (int i : semiPrefs)
            {
                if (i == value)
                    return true;
            }

        }
        return false;
    }

    /**
     * overloaded method. 
     * one has
     * @param prefAt
     * and one doesnt. the prefAt just means that seminar was one the student wanted
     * @param time
     * @param room
     * @param newSession
     */

    public void assignSession(int time, int room, Seminar newSession)
    {
        sessIDs.add(newSession.getId());
        roomNums[time] = room + 101;
        sessionNames[time] = newSession.getName();
    }

    /**
     * tests to see if the seminar (using ID) is one the person wanted
     * @param semiId
     * @return boolean
     */
    public boolean hasPref(int semiId)
    {
        for (int b : semiPrefs)
        {
            if (semiId == b)
            {return true;}
        }
        return false;
    }

    /**
     * so basically the roomNum hasn't been assigned, meaning there's no session there
     * @param time
     * @return
     */
    public boolean canAdd(int time, int semId)
    {
        return !sessIDs.contains(semId) && (roomNums[time] == -1);
    }

    public String toString()
    {
        String build = "";
        build += name.toUpperCase();
        build += "\n" + email;

        for (int c = 0; c < 5; c++)
        {
            build += "\n" + sessionNames[c] + " in Room" + roomNums[c];
        }

        build += "\n";

        return build;
    }

    public boolean hasEmpty()
    {
        for (int i = 0; i < 5; i++)
        {
            if(roomNums[i] == -1)
                return true;
        }
        return false;
    }

    public boolean hasSess(int time)
    {
        return (roomNums[time] != -1);
    }
}
