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

    Seminar[] asSemis = new Seminar[5];
    public void assignSemi(int time, Seminar newSemi)
    {
        asSemis[time] = newSemi;
    }

    public boolean isBusy(int time)
    {
        return asSemis[time] != null;
    }

    public boolean hasEverything()
    {
        return !(asSemis[0]==null || asSemis[1] == null || asSemis[2] == null || asSemis[3] == null || asSemis[4] == null);
    }

    public String toString()
    {
        String build = "";
        build += name.toUpperCase();
        build += "Email: " + this.email;
        for (int s = 0; s < 5; s++)
            build += "\t Session 1:" +  asSemis[s].getName() + " in " + asSemis[s].getRoom(s);
        return build;
    }
}