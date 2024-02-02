public class Seminar
{
    public Seminar (String[] parts)
    {
        ID = Integer.parseInt(parts[0]);
        name  = parts[1];
        lect = new Presenter(parts[2]);
    }

    int ID;
    public int getID()
    {
        return ID;
    }

    String name;
    public String getName()
    {
        return name;
    }

    Presenter lect;
    public String getLectName()
    {
        return lect.getName();
    }

    int sessCt = 0;

    public boolean canAdd(int time)
    {
        return (!lect.isBusy(time) && sessCt < 2);
    }

    Session[] sessions = new Session[5];

    public boolean hasSess (int time)
    {
        return (sessions[time] != null);
    }

    public void addSess(int time, int roomNum)
    {
        sessions[time] = new Session(roomNum);
        sessCt++;
    }

    int chosen = 0;

    public void addChoice()
    {
        chosen++;
    }

    public int getPrefCt()
    {
        return chosen;
    }

    public int getRoom(int time)
    {
        return sessions[time].getRoom();
    }

    public boolean hasSpace(int time)
    {
        return sessions[time].hasSpace();
    }

    public void addStu(int time, String name)
    {
        sessions[time].addStudent(name);
    }

    public String toString (int time)
    {
        String build = name + "\n";
        build += sessions[time];
        return build;
    }
}