import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class checker
{
    public static void main (String args[]) throws FileNotFoundException {

        Scanner s = new Scanner(new File("/Users/welshej/Desktop/Tour/Roster.txt"));

        ArrayList<Person> roster = new ArrayList<Person>();

        while(s.hasNext()) roster.add(new Person(s.nextLine().split(" ")));

        ArrayList<String> pop = new ArrayList<String>();


        for(Person p : roster)
        {
            if(pop.contains(p.getName()) == false) pop.add(p.getName());
        }



        Collections.sort(pop);

        for(int i = 1; i < pop.size(); i++) System.out.println(i + ". " + pop.get(i));

        /*Scanner scan = new Scanner(new File("/Users/welshej/Desktop/Tour/Tour Preference List.txt"));
        ArrayList<Person> incomp = new ArrayList<Person>();



        while(scan.hasNext())
        {
            String line = scan.nextLine();
            if(line.length() > 0)
            {
                Person p = new Person(line);
                incomp.add(p);
            }
        }

        System.out.println();
        System.out.println();
        for(Person p : incomp) System.out.println(p);


     */







    }


}
