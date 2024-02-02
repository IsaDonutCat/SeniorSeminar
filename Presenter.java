public class Presenter
{
    private String name;
    private boolean[] isBusy = new boolean[5]; //default of boolean is false

    public Presenter (String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public boolean isBusy (int ind)
    {
        return isBusy[ind];
    }

    public void assign(int ind)
    {
        isBusy[ind] = true;
    }

}