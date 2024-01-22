public class Presenter
{
    private String name;
    private boolean[] isBusy = new boolean[5]; //default of boolean is false
    private Seminar[] semis = new Seminar[5];

    public Presenter (String name)
    {
        this.name = name;
    }

    public boolean isBusy (int ind)
    {
        return isBusy[ind];
    }

    public void assign(Seminar semi, int ind)
    {
        isBusy[ind] = true;
        semis[ind] = semi;
    }

    public void printSched()
    {
        System.out.println (name);
        for (int i = 0; i < 5; i++)
        {
            if (semis[i] != null) //if there's something there
            {
                System.out.println("Session " + (i+1) + ": " + semis[i].getName() + " (Room " + semis[i].getRoom(i) + ")");
            }
        }
    }


}