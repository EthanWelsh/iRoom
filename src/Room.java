import java.util.ArrayList;

public class Room
{
    private int capacity;
    int population = 0;
    ArrayList<Person> roomies = new ArrayList<Person>();

    public Room()
    {

    }

    public Room(int capacity)
    {
        this.capacity = capacity;
    }

    public void addPerson(Person p)
    {
        roomies.add(p);
        population++;
    }

    public int happyScore()
    {
        int totalHappy = 0;

        for(Person p : roomies) // For every person in the room,
        {
            String good = p.roomList();
            String bad = p.noRoomList();

            String[] goodName = good.split(",");
            String[] badName = bad.split(",");

            for(Person q : roomies) // See how they feel about every other person in the room.
            {
                for(int i = 0; i < badName.length; i++) if(q.getName().equalsIgnoreCase(badName[i].trim())) return -999;
                for(int i = 0; i < goodName.length; i++) {
                    if(q.getName().equalsIgnoreCase(goodName[i].trim()))
                    {
                        if(i == 0) totalHappy = totalHappy + 4;
                        else if (i == 1) totalHappy = totalHappy + 3;
                        else if (i == 2) totalHappy = totalHappy + 2;
                        else totalHappy++;
                    }
                }
            }
        }

        return totalHappy;
    }

    public int happyScore(Person p)
    {
        if (population >= capacity) return -500; //TODO used to be population + 1

        ArrayList<Person> temp = new ArrayList<Person>();

        for(Person toCopy : roomies) temp.add(toCopy);


        temp.add(p);
        //System.out.println(roomies + ":" + temp);

        int totalHappy = 0;

        for(Person peep : temp) // For every person in the room,
        {
            String good = peep.roomList();
            String bad = peep.noRoomList();

            String[] goodName = good.split(",");
            String[] badName = bad.split(",");

            for(Person q : temp) // See how they feel about every other person in the room.
            {
                for(int i = 0; i < badName.length; i++)
                {
                    if(q.getName().equalsIgnoreCase(badName[i].trim())) return -1001;
                }
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
        }

        return totalHappy;
    }


    public String toString()
    {
        String toPrint = "[";

        for(Person p : roomies) toPrint = toPrint + " " + p.getName() + ",";

        toPrint = toPrint + "] " + happyScore();
        return toPrint;


    }

    public void incrementCapacity()
    {
        this.capacity++;
    }

    public Person getPerson(int i)
    {
        return roomies.get(i);
    }

    public void setPerson(int i, Person p)
    {
        if(i > roomies.size()) System.out.println("Can't put something into the " + i + " position of a size " + roomies.size() + " array");
        roomies.set(i, p);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCapacityR() {
        return roomies.size();
    }



}
