public class Presenter
{
    private boolean[] isBusy = {false, false, false, false, false}; //default of boolean is false
    private int[] roomNum = {-1,-1,-1,-1,-1};
    private String[] presenting = new String[5];
    private String name;

    public Presenter(String name)
    {
        this.name = name;
    }
    public boolean isBusy (int ind)
    {
        return isBusy[ind];
    }

    public void assign(int ind, String seminar, int roomNum)
    {
        isBusy[ind] = true;
        presenting[ind] = seminar;
        this.roomNum[ind] = roomNum + 101;
    }

    public String toString()
    {
        String build = "";
        build += name.toUpperCase();
        for (int i = 0; i < 5; i++)
        {
            if (roomNum[i] != -1)
            {
                build += "\n" + presenting[i].toUpperCase();
                build += " in " + roomNum + " in time slot " + i;
            }
        }

        return build;
    };
}
