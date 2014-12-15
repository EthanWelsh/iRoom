iRoom
=====

Automatic roommate selection programming. Finds an optimal configuration of people to be placed in rooms based off of a person's rooming preferences. 

See DOCUMENTATION for more information. 

### Tour Preferences List
![img](/images/a.png)

The file is consisted of several lines, each line representing a person and their respective likes and dislikes. The first name on the line contains the name of the person whose name this entry pertains
to. After a colon (:), the persons whom this individual would like to room with are found, each name separated by a comma (,) in order of preference. Finally, after an ampersand (@), is listed the names
of the people with whom this individual will not room with (once again separated by commas).

![img](/images/b.png)

The file must be structured exactly as listed above. Forgetting a ‘:’ or ‘@‘ will cause the application to crash or work incorrectly.
!If an individual does not have any preference with whom they’d like to room with, then you may place an ‘X’ on that line to indicate as such. Likewise, if a person has no one they would not consider rooming with, an X may be placed on that line as well.

![img](/images/c.png)

### Scoring

In order to place people into rooms, the application computes the ‘score’ of each possible rooming configuration according to a person’s requests (as described in the tour preferences list).
!Rooms are scored according to preference such that the person listed first a person’s “likes” is the most preferred person.
!The first three people on a person’s preference list are assigned a higher value. After that, everyone on the list is seen as equally desirable. An individual will never be placed in a room with someone on their “not” list.:

![img](/images/d.png)

!After an individual’s score is computed for a room, it is added to everyone else’s score in a room in order to form a composite score for a room.

![img](/images/e.png)

### Running

After filling in the Tour Preference List, and making sure that the formatting is correct, simply double click the iRoom.jar file. At once, you should be prompted with a dialog asking you where your Tour Preferences List can be found. Select the file and click open to proceed. You’ll see a message saying that the file has been found shortly after.
!Next you will receive another file selection dialogue, this time asking you where it would like you store the results and what you would like to name the file. Type in whatever you’d like to name the output of the application into the Save As field, then select the destination and click Save.

![img](/images/f.png)

!Next, you’ll receive an option asking you if you’d like to generate the room sizes automatically or manually. If you choose the option to create rooms automatically on the right, you’ll be asked to enter in the maximum room size. With 58 people, if you enter the maximum room size as 4, the program will create fifteen rooms, thirteen of which will contain 4 people, and two of which will contain 3 people.
!If you choose the “manual” option on the left, then you’ll be asked how many rooms (and of what sizes) you’d like to add. In order to add a room, type the capacity of the room you’d like to separated by a space (‘ ‘). So if I wanted to manually generate the 15 rooms to accommodate 58 people, I could enter the following:

![img](/images/g.png)

After choosing the rooms and sizes, the program will run and write the finished result to the location you previously specified. The program varies drastically according to the number of persons and sizes of rooms that you choose, but for your purposes, you can expect the program to be complete after approximately 30 seconds.