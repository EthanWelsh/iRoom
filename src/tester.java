import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class tester
{
    static ArrayList<Person> roster = new ArrayList<Person>();


    public static void main(String args[]) throws FileNotFoundException
    {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        chooser.setDialogTitle("Where is your results file?");
        System.out.println(chooser.getSelectedFile());

        JFileChooser chooser1 = new JFileChooser();
        chooser1.setDialogTitle("Where would you like to save the output? (please end file in '.txt')");
        chooser1.showSaveDialog(null);



        PrintWriter fOut = new PrintWriter(new FileOutputStream(chooser1.getSelectedFile(), false));






        Scanner s = new Scanner(chooser.getSelectedFile());

        while(s.hasNext())
        { // Read every line from the file...
            String line = s.nextLine();
            if(line.length() > 0)
            {
                Person p = new Person(line); // Make it into a person object...
                roster.add(p); // And add it to our roster.

            }
        }

        //for(Person p : roster) System.out.println(p);
        //System.out.println("There are " + roster.size() + " people going on tour.");

        Boolean automatic = true;
        Building building = new Building();
        String capString = "";

        if(automatic)
        {
            int maxRoomSize = Integer.parseInt(JOptionPane.showInputDialog("What would you like to be the maximum room size?"));



            int numberOfRooms = roster.size() / maxRoomSize;
            if((roster.size() % maxRoomSize) != 0) numberOfRooms++;

            //System.out.println(roster.size() + " members and " + numberOfRooms + " rooms.");

            for(int i = 0; i < numberOfRooms; i++) building.addRoom(new Room(0));

            int j = 0;

            for(int i = 0; i < roster.size(); i++)
            {
                building.getRoom(j % numberOfRooms).incrementCapacity();
                j++;
            }

            System.out.print("Generated " + numberOfRooms + " rooms, with the respective capacities: ");
            fOut.print("Generated " + numberOfRooms + " rooms, with the respective capacities: ");

            for(Room r : building.getBuilding()) {
                capString = capString + r.getCapacity() + " ";
            }

            System.out.println(capString);
            fOut.println(capString);


        }

        else
        {

            ArrayList<Room> b = building.getBuilding();

            // manual room creation.
            while(true)
            {
                String roomString = JOptionPane.showInputDialog("How many groups would you like to add? \nEnter in the rooms by size, separated by spaces:\n For example: '5 5 5' would give three rooms, each with a capacity of 5.");


                //System.out.println(roomString);
                String roomsBySize[] = roomString.split(" ");

                int buildingCapacity = 0;

                for(String room : roomsBySize)
                {
                    int numOfPeopleInRoom = Integer.parseInt(room.trim());

                    b.add(new Room(numOfPeopleInRoom));

                    buildingCapacity = buildingCapacity + numOfPeopleInRoom;
                }

                if(buildingCapacity < roster.size()) System.out.println("The total capacity of all the rooms that you entered is " + buildingCapacity + ", which isn't large enough to house all " + roster.size() + " people on your roster.");
                else {
                    capString = roomString;
                    break;
                }
            }

        }

        Building bestWestern = building;

        for(int q = 0; q < 5; q++)
        {

            int[] rosterNumbers = new int[roster.size()];
            for(int i = 0; i < rosterNumbers.length; i++) rosterNumbers[i] = i;


            building = makeBuilding(rosterNumbers, capString);





            long old = System.currentTimeMillis();

            boolean hasChanged = true;

            while(hasChanged == true)
            {

                System.out.println("!");

                hasChanged = false;

                for(int roomIndexA = 0; roomIndexA < building.numOfRooms(); roomIndexA++ )
                {   // For every room in the building
                    for(int personIndexA = 0; personIndexA < (building.getRoom(roomIndexA).getCapacityR()); personIndexA++)
                    { // And every person in that room
                        for(int roomIndexB = 0; roomIndexB < building.numOfRooms(); roomIndexB++ )
                        {   // For every room in the building
                            for(int personIndexB = 0; personIndexB < (building.getRoom(roomIndexB).getCapacityR()); personIndexB++)
                            {

                                int oldHappy = building.happy();


                                building.switchPeep(roomIndexA,personIndexA,roomIndexB,personIndexB);
                                int newHappy = building.happy();

                                if(oldHappy > newHappy) building.switchPeep(roomIndexA,personIndexA,roomIndexB,personIndexB);
                                else if (newHappy > oldHappy)
                                {
                                    System.out.println(newHappy);
                                    hasChanged = true;
                                }

                            }
                        }
                    }
                }
            }

            System.out.println((System.currentTimeMillis() - old) / 1000 + " seconds");
            System.out.println(((System.currentTimeMillis() - old)/1000)/60 + " minutes");
            System.out.println((((System.currentTimeMillis() - old)/1000)/60)/60 + " hours");


            if(building.happy() > bestWestern.happy()) bestWestern = building;

        }


        bestWestern.print();
        System.out.println("Total Score of: " + bestWestern.happy());


        fOut.println(bestWestern.toString());
        fOut.println("Total Score of: " + bestWestern.happy());
        fOut.close();

    }






    public static Building makeBuilding(int[] rosterNumbers, String roomStr)
    {
        ArrayList<Room> building = new ArrayList<Room>();
        String split[] = roomStr.split(" ");

        for(String s : split) building.add(new Room(Integer.parseInt(s)));


        shuffleArray(rosterNumbers);

        int it = 0;
        for(Person p : roster)
        {
            p.setLuckyNumber(rosterNumbers[it]);
            it++;
        }

        Collections.sort(roster);

        for(Person p : roster) // For every person in the roster
        {
            int highestRoomScore = -1;
            int highestRoomIndex = 0;
            int i = 0;

            for(Room r : building) // Go to each room in the building
            {
                if(r.happyScore(p) > highestRoomScore)
                {
                    highestRoomScore = r.happyScore(p);
                    highestRoomIndex = i;
                }

                i++;
            }

            if(building.get(highestRoomIndex).happyScore(p) >= 0) building.get(highestRoomIndex).addPerson(p);
        }

        return new Building(building);

    }


    static void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for(int j = 0; j < 5; j++) {
            for (int i = ar.length - 1; i > 0; i--)
            {
                int index = rnd.nextInt(i + 1);
                // Simple swap
                int a = ar[index];
                ar[index] = ar[i];
                ar[i] = a;
            }
        }
    }

}
