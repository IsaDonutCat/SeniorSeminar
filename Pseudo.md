# Pseudo code for Senior Seminar

**Specifications:**
- Each student attends 5 seminars
- Sessions cannot run more than Twice
- There are 5 classrooms per time slot. (25 seminars in total). Each classroom can hold up to 16 students. 
- Students cannot repeat a session. 

# To-Do next time:
- **Reorganize the csvs**
    - Split them into two, one seminar and one student
    - remove the string seminar choices from the students

# Planning:
**Classes:**
*= Tester*
    - Reads in the files, creates everything in ArrayLists
    - Needs to check to make sure that a 
*- Student*
    - Name
    - Email
    - 2nd Email
    - Student ID
    - Submit Time

    - 1st Choice
    - 2nd Choice
    - 3rd Choice
    - 4th Choice
    - 5th Choice

    - 1st Seminar
    - 2nd Seminar
    - 3rd Seminar
    - 4th Seminar
    - 5th Seminar

    
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