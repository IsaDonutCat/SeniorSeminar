public class Student
{
    private boolean hasChosen;
    private int[] choices = new int[5];
    private String name;
    private String email;
    private Seminar[] semis = new Seminar[5];
    private boolean[] isBusy = new boolean[5];

    public Student (String inEmail, String inName, int inChoice1, int inChoice2, int inChoice3, int inChoice4, int inChoice5)
    {
        hasChosen = true; //this student has made the 5 choices

        name = inName;
        email = inEmail;

        //minus 1 accounts for human counting -> array list indexes
        choices[0] = inChoice1 - 1;
        choices[1] = inChoice2 - 1;
        choices[2] = inChoice3 - 1;
        choices[3] = inChoice4 - 1;
        choices[4] = inChoice5 - 1;
    }

    public Student (String inEmail, String inName)
    {
        hasChosen = false; //this student has made no choices

        name = inName;
        email = inEmail;
    }

    public int getChoiceID (int ind)
    {
        return choices[ind];
    }

    public boolean assignChoice (int ind, Seminar inSemi)
    {
        if (isBusy[ind])
            return false;

        semis[ind] = inSemi;
        isBusy[ind] = true;
        return true;
    }

    public void printSched()
    {
        System.out.println(name + "'s Schedule");
        System.out.println(email + "@countryday.net");
        System.out.println("Session 1: " + semis[0].getName() + "(Room " + semis[0].getRoom(0) + ")");
        System.out.println("Session 2: " + semis[1].getName() + "(Room " + semis[1].getRoom(1) + ")");
        System.out.println("Session 3: " + semis[2].getName() + "(Room " + semis[2].getRoom(2) + ")");
        System.out.println("Session 4: " + semis[3].getName() + "(Room " + semis[3].getRoom(3) + ")");
        System.out.println("Session 5: " + semis[4].getName() + "(Room " + semis[4].getRoom(4) + ")");
    }

    public boolean equals(String compName)
    {
        return compName.equals(name);
    }
}