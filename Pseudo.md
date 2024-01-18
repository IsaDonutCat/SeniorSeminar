# Pseudo code for Senior Seminar

**Specifications:**
- Each student attends 5 seminars
- Sessions cannot run more than Twice
- There are 5 classrooms per time slot. (25 seminars in total). Each classroom can hold up to 16 students. 
- Students cannot repeat a session. 

# CSV Format:
**students.csv:**
- email (- the @countryday.net part)
- Full Name
- 1st seminar pick ID
- 2nd seminar pick ID
- 3rd seminar pick ID
- 4th seminar pick ID
- 5th seminar pick ID
**seminars.csv:**
- seminar ID
- seminar name
- presenter name



# Planning:
**Polymorphism**
- Person class
- Occupied at this time? bool[5]
- name
- extends student
    - int[] choices
        - 1st Choice ID
        - 2nd Choice ID
        - 3rd Choice ID
        - 4th Choice ID
        - 5th Choice ID

    - String[] Seminars
        - 1st Seminar Name 
        - 2nd Seminar Name
        - 3rd Seminar Name
        - 4th Seminar Name
        - 5th Seminar Name
 
    - public boolean needsAssign(return !(occ[1]&&occ[2]&&occ[3]&&[occ]4&&[occ]5))
    - public void assignSession ()
- extends Presenter
    - if occupied[x] = true -> already something is occupied && want to schedule at slot [y], set isBooked = true (no more new sessions can be booked)
**Classes:**
*- Tester*
    - Reads in the files, creates everything in ArrayLists
*- Student*
    - Name
    - Email name

    - 1st Choice ID
    - 2nd Choice ID
    - 3rd Choice ID
    - 4th Choice ID
    - 5th Choice ID

    - 1st Seminar ID
    - 2nd Seminar ID
    - 3rd Seminar ID
    - 4th Seminar ID
    - 5th Seminar ID
    
    - Two constructors. one with everything and one for those who haven't selected any. for them, their seminar stuff is -1
*- Seminar*
    - Presenter
    - ID
    - Session Ct (to avoid going over)
    - Students attending(ArrayList)
    - isFull() boolean.
*- Presenter*
    - isOccupied() = boolean checks if they already have 2 presentations or if they have a different presentation at the same time slot. 
    - SeminarList. the ones they give. 
    - Slot 1, Slot 2, Slot 3, Slot 4, Slot 5 taken up or not. 
    - PresentationList. should always be ArrayList.size() 2 

**Organization:**
- Ignore the students' selections of the string, since there is one referencing the seminars' IDs
- 
