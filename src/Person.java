import java.util.ArrayList;

public class Person implements Comparable
{
    int luckyNumber;
    String firstName;
    String lastName;
    ArrayList<String> room = new ArrayList<String>();
    ArrayList<String> noRoom = new ArrayList<String>();


    public Person(String name[]) {
        this.firstName = name[0];
        this.lastName = name[1];
    }

    public Person(String fileString)
    {
        if(fileString.length() > 0) {
            String[] tokens = fileString.split(":");

            String[] nameArray = tokens[0].split(" ");

            firstName = nameArray[0];
            lastName = nameArray[1];

            String[] rooming = tokens[1].split("@");

            String[] toRoomWith = rooming[0].split(",");
            String[] doNotRoomWith = rooming[1].split(",");

            for(int i = 0; i < toRoomWith.length; i++) room.add(toRoomWith[i].trim());

            for(int i = 0; i < doNotRoomWith.length; i++) noRoom.add(doNotRoomWith[i].trim());
        }

    }

    public int individualHappy(Room r) {
        int totalHappy = 0;


            String good = this.roomList();
            String bad = this.noRoomList();

            String[] goodName = good.split(",");
            String[] badName = bad.split(",");

            for(Person q : r.roomies) // See how they feel about every other person in the room.
            {
                for(int i = 0; i < badName.length; i++) if(q.getName().equalsIgnoreCase(badName[i].trim())) return -999;
                for(int i = 0; i < goodName.length; i++)
                {
                    if(q.getName().equalsIgnoreCase(goodName[i].trim()))
                    {
                        if(i == 0) totalHappy = totalHappy + 4;
                        else if (i == 1) totalHappy = totalHappy + 3;
                        else if (i == 2) totalHappy = totalHappy + 2;
                        else totalHappy++;
                    }
                }
            }

        return totalHappy;
    }

    public Person(Person p)
    {
        this.luckyNumber = p.luckyNumber;
        this.firstName = p.firstName;

        for(String s : p.room) this.room.add(s);
        for(String s : p.noRoom) this.noRoom.add(s);

    }

    public String getName()
    {
        return firstName + " " + lastName;
    }

    public String toString()
    {
        return firstName + " " + lastName;
    }

    public String roomList()
    {
        String roomWith = "";
        for(String roobbie : room) roomWith = roomWith + roobbie + ", ";

        return roomWith;
    }

    public String noRoomList()
    {
        String notRoomWith = "";
        for(String roobbie : noRoom) notRoomWith = notRoomWith + roobbie + ", ";

        return notRoomWith;
    }

    public void setLuckyNumber(int x)
    {
        luckyNumber = x;
    }

    public int getLuckyNumber() {
        return luckyNumber;
    }

    public int compareTo(Object o)
    {
        Person p = (Person)o;

        if(p.getLuckyNumber() > this.getLuckyNumber()) return -1;
        else return 1;
    }

    public boolean equals(Person p) {
        return p.getName().equals(this.getName());

    }
}
