public class Seminar
{
    String name;
    String lectName;
    int ID;
    Session[] sessions = new Session[5];
    int stuPrefs = 0;
    int sessCt;

    public Seminar(String[] parts)
    {
        ID = Integer.parseInt(parts[0]) - 1;
        name = parts[1];
        lectName = parts[2];
    }

    public int getId()
    {return ID;}

    public String getName()
    {return name;}

    public String getLectName()
    {return lectName;}

    public void addPref()
    {stuPrefs++;}

    public int getPrefCt()
    {return stuPrefs;}

    public void addSession(int time, int room)
    {
        sessions[time] = new Session(room);
        sessCt++;
    }

    public boolean canAddSession()
    {
        return (sessCt < stuPrefs / 8 && sessCt < 2);
    }

    public boolean canAddStudent(int time)
    {
        return sessions[time].getStuCt() < 16;
    }

    public Session getSess(int time)
    {
        return sessions[time];
    }

    public void addStudent(int time, Student stu)
    {
        sessions[time].addStudent(stu);
    }

    public String toString()
    {
        String build = name.toUpperCase();
        build += "\n" + lectName + "\n";
        for (int e = 0; e < 5; e++)
        {   
            if (sessions[e] != null)
            {
                build += sessions[e] + "\n";
            }
        }
        return build;
    }
}
