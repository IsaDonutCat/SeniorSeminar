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
- created a preference tallier for students' choices
  
# January 26th, 2024
- Was not in class

# January 30th, 2024
- Created a ranking system
- Changed Seminar to be a unique instance for each session
- Began writing an assigning function but did not want to access last row and column for some reason.

# January 31st, 2024
- Finished the sorting (it turns out that there were not enough sessions given)
- Finished assigning everyone to their seminars
- Starting to look into how to search (possibly a parallel string ArrayList to use indexOf for?);

# February 1st, 2024
- Can't get it to not assign students vertically
- **Just** realized that two sessions max, it's not you can have more than 2 sessions, but jsut you can have a presenter do more than 2
- ~hear me out i should rewrite this all~

# February 2nd, 2024
- I ended up rewriting it
- Made it a lot cleaner and better organized. getters' are right next to the variable theyre using, and just in general a lot more data is stored and better.
- Since i had a better idea of what to do i was able to do it.
- Added Session class bc seminars have sessions - could help keep track of stuff specific to a session.
- Have not tested it since vs code hates me on my home computer so i'm writing this all in github and hoping i did theoretical coding right
# February 3rd, 2024
- Updated Student and Tester classes to help avoid students getting assigned to the same seminars
# February 5th, 2024
- Fixed bugs that had not been accounted for in shifting totalSched to integers
- Fixed inability to add students to classes due to duplication
    - Made it so that only classes that more than 18 people chose could be added twice
    - Also shifted the leftover seminars to order by going 1st to last instead of last to 1st