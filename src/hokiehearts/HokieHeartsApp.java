package hokiehearts;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the console version of the Hokie Hearts MVP.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class HokieHeartsApp
{
    private HokieHearts system;
    private Scanner scanner;


    /**
     * Starts Hokie Hearts.
     *
     * @param args command-line arguments are not used
     */
    public static void main(String[] args)
    {
        HokieHeartsApp app = new HokieHeartsApp(new Scanner(System.in));
        app.run();
    }


    /**
     * Creates the console application.
     *
     * @param scanner input used by the application
     */
    public HokieHeartsApp(Scanner scanner)
    {
        system = new HokieHearts();
        this.scanner = scanner;
    }


    /**
     * Displays the menu until the user exits.
     */
    public void run()
    {
        boolean running = true;
        System.out.println("Welcome to Hokie Hearts!");

        while (running)
        {
            showMenu();
            String choice = scanner.nextLine().trim();

            if (choice.equals("1"))
            {
                createProfile();
            }
            else if (choice.equals("2"))
            {
                addPreference();
            }
            else if (choice.equals("3"))
            {
                listProfiles();
            }
            else if (choice.equals("4"))
            {
                compareProfiles();
            }
            else if (choice.equals("5"))
            {
                findBestMatch();
            }
            else if (choice.equals("6"))
            {
                running = false;
                System.out.println("Thanks for using Hokie Hearts!");
            }
            else
            {
                System.out.println("Please enter a number from 1 through 6.");
            }
        }
    }


    private void showMenu()
    {
        System.out.println();
        System.out.println("1. Create profile");
        System.out.println("2. Add preference");
        System.out.println("3. View profiles");
        System.out.println("4. Compare two profiles");
        System.out.println("5. Find my best match");
        System.out.println("6. No luck?... Try again next week, dont worry "
            + "your "
            + "Hokie Heart "
            + "is still "
            + "out there! 🦃❤️\n"
            + "");
        System.out.print("Choose an option: ");
    }


    private void createProfile()
    {
        String name = readName();
        int age = readPositiveAge();
        String email = readVTEmail();
        Person person = new Person(name, age, email);

        if (system.addPerson(person))
        {
            System.out.println("Profile created for " + name + ".");
        }
        else
        {
            System.out.println(
                "Profile not created. Use a unique @vt.edu email.");
        }
    }


    private String readName()
    {
        while (true)
        {
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty())
            {
                return name;
            }
            System.out.println("A name is required.");
        }
    }


    private int readPositiveAge()
    {
        while (true)
        {
            System.out.print("Age: ");
            try
            {
                int age = Integer.parseInt(scanner.nextLine().trim());
                if (age > 0)
                {
                    return age;
                }
                System.out.println("Age must be positive.");
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Age must be a whole number.");
            }
        }
    }


    private String readVTEmail()
    {
        while (true)
        {
            System.out.print("VT email: ");
            String email = scanner.nextLine().trim();
            if (system.isValidVTEmail(email))
            {
                return email;
            }
            System.out.println("Enter a valid @vt.edu email.");
        }
    }


    private void addPreference()
    {
        Person person = askForPerson("Profile VT email: ");
        if (person == null)
        {
            return;
        }

        System.out.print("Preference category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Preference value: ");
        String value = scanner.nextLine().trim();

        if (category.isEmpty() || value.isEmpty())
        {
            System.out.println("Category and value are required.");
            return;
        }

        person.addPreference(new Preference(category, value));
        System.out.println("Preference added.");
    }


    private void listProfiles()
    {
        ArrayList<Person> people = system.getPeople();
        if (people.isEmpty())
        {
            System.out.println("No profiles have been created.");
            return;
        }

        for (Person person : people)
        {
            System.out.println(person.getName() + " (" + person.getEmail()
                + ") - " + person.getPreferences());
        }
    }


    private void compareProfiles()
    {
        Person first = askForPerson("First VT email: ");
        if (first == null)
        {
            return;
        }
        Person second = askForPerson("Second VT email: ");
        if (second == null)
        {
            return;
        }

        Match match = system.createMatch(first, second);
        if (match == null)
        {
            System.out.println("Choose two different stored profiles.");
            return;
        }

        System.out.printf("Compatibility: %.1f%%%n",
            match.getCompatibilityScore());
        System.out.println("Shared preferences: "
            + match.getSharedPreferences());
        Suggestion suggestion = new Suggestion(match);
        System.out.println("Date idea: " + suggestion.getSuggestion());
    }


    private Person askForPerson(String prompt)
    {
        System.out.print(prompt);
        Person person = system.findPerson(scanner.nextLine().trim());
        if (person == null)
        {
            System.out.println("Profile not found.");
        }
        return person;
    }


    private void findBestMatch()
    {
        Person person = askForPerson("Your VT email: ");
        if (person == null)
        {
            return;
        }

        ArrayList<Match> bestMatches = system.findBestMatches(person);
        if (bestMatches.isEmpty())
        {
            System.out.println("No other profiles are available yet.");
            return;
        }

        System.out.println("Best match result:");
        for (Match match : bestMatches)
        {
            Person bestPerson = match.getSecondPerson();
            System.out.println(bestPerson.getName() + " ("
                + bestPerson.getEmail() + ")");
            System.out.printf("Compatibility: %.1f%%%n",
                match.getCompatibilityScore());
            System.out.println("Shared preferences: "
                + match.getSharedPreferences());
            System.out.println("Date idea: "
                + new Suggestion(match).getSuggestion());
        }
    }
}
