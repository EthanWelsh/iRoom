import java.util.ArrayList;

public class Building
{
    private ArrayList<Room> building = new ArrayList<Room>();

    public Building() {

    }

    public Building(ArrayList<Room> rooms)
    {
        building = rooms;
    }


    public void addRoom(Room r)
    {
        building.add(r);
    }

    public Room getRoom(int i) {
        return building.get(i);
    }

    public ArrayList<Room> getBuilding() {
        return building;
    }

    public int happy()
    {
        int happyScore = 0;
        for(Room r : building)
        {
            int roomScore = r.happyScore();
            if(roomScore < 0) return -580;
            else happyScore = happyScore + r.happyScore();
        }

        return happyScore;

    }

    public void print() {
        for(Room r : building)
        {
            System.out.println("\n-------------"+r.happyScore()+"-------------");

            for(Person p : r.roomies) System.out.println(p);

            System.out.println("-------------"+r.happyScore()+"-------------\n");
        }
    }

    public String toString() {

        String x = "";

        for(Room r : building)
        {
            x = x + "\n-------------"+r.happyScore()+"-------------\n";

            for(Person p : r.roomies) x = x + p + " (" + p.individualHappy(r) + ")" + "\n";

            x = x + "-------------"+r.happyScore()+"-------------\n";
        }

        return x;
    }

    public void switchPeep(int roomIndexA, int personIndexA, int roomIndexB, int personIndexB)
    {
        Room roomA = building.get(roomIndexA);
        Room roomB = building.get(roomIndexB);

        Person personA = roomA.getPerson(personIndexA);
        Person personB = roomB.getPerson(personIndexB);

        roomA.setPerson(personIndexA, personB);
        roomB.setPerson(personIndexB, personA);
    }

    public int numOfRooms() {
        return building.size();
    }


}
