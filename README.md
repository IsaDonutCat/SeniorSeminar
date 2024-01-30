# SeniorSeminar
Repository for Q3 Senior Seminar coding assignment. 
# The ranking system is as follows:
- The first student to submit gets all their choices, in the order they submitted them. So the first slot has their first choice, etc.
- The second student to submit first gets their choices searched to see if they have any matches with the first student. If they do, they are put in the same seminar. Any choices that aren't a match are attempted to fit into the empty slots. If the presenter of a seminar is already occupied at that time, or they already have two sessions, the next unique choice is put in that slot. If there's no fit, they are assigned to an empty seminar at that time. 

# January 18th, 2024
- someone please get git and homebrew to cooperate i'm going insane

# January 22nd, 2024
- Reorganizing the system to instead rank the seminars on how many students want them, to optimize choices.
- Wrote code for student, seminar, and presenter classes.
    - seminar asks whether the presenter is busy, since the presenter can have multiple classes
    - student constructor is overloaded to account for those who didn't select
    - a boolean inside student makes it easy to see if there's a choice or not - returns -1 if there isn't. 
    - wrote an equals() method inside student to hopefully be able to use indexOf() from arraylist (thanks javaDoc) \

# January 24th, 2024

# January 26th, 2024
- Was not in class

# January 30th, 2024
- Created a ranking system
- Changed Seminar to be a unique instance for each session
- Began writing an assigning function but did not want to access last row and column for some reason.