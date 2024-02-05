public class Student
{
    public Student(String[] parts)
    {
        email = parts[0].strip();
        name = parts[1].strip();
        madeChoices = true;

        for (int k = 0; k < 5; k++)
        {
            choices[k] = Integer.parseInt(parts[k+2]);
        }
    }

    public Student(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    String name;
    public String getName()
    {
        return name;
    }

    String email;
    public String getEmail()
    {
        return email + "@countryday.net";
    }

    boolean madeChoices = false;
    public boolean madeChoices()
    {
        return madeChoices;
    }

    int[] choices = new int[5];
    public int getChoice (int rank)
    {
        return choices[rank];
    }

    public void addedChoice(int rank)
    {
        choices[rank] = -1;
    }

    Seminar[] asSemis = new Seminar[5];
    public void assignSemi(int time, Seminar newSemi)
    {
        asSemis[time] = newSemi;
        semiIDs[time] = newSemi.getID();
    }

    public boolean isBusy(int time)
    {
        return asSemis[time] != null;
    }
    
    public boolean hasEverything()
    {
        for (int i = 0; i < 5; i++)
        {
            if (asSemis == null)
                return false;
        }
        return true;
    }

    int[] semiIDs = new int[5];

    public boolean canAdd(int time, int ID)
    {
        //System.out.println(asSemis[time] != null && semiIDs[0] != ID && semiIDs[1] != ID && semiIDs[2] != ID && semiIDs[3] != ID && semiIDs[4] != ID);
        return asSemis[time] == null && semiIDs[0] != ID && semiIDs[1] != ID && semiIDs[2] != ID && semiIDs[3] != ID && semiIDs[4] != ID;
    }
    
    public String toString()
    {
        String build = "";
        build += name.toUpperCase();
        build += "Email: " + this.email;
        for (int s = 0; s < 5; s++)
            build += "Session 1:" +  asSemis[s].getName() + " in " + asSemis[s].getRoom(s);
        return build;
    }
}
