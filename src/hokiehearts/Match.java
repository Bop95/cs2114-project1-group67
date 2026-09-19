package hokiehearts;

import java.util.ArrayList;

// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Joey Gentlesk (jgentlesk)
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
/**
 * Makes a match between two people who use the Hokie Hearts application
 * 
 * @author Joey Gentlesk
 * @version Sep 19, 2026
 */
public class Match
{
    private Person person1;
    private Person person2;
    private double compatibilityScore;

    /**
     * Creates the match between two people/person objects
     * 
     * @param onePerson
     *            the first person in the match
     * @param anotherPerson
     *            the second person in the match
     */
    public Match(Person onePerson, Person anotherPerson)
    {
        person1 = onePerson;
        person2 = anotherPerson;
        compatibilityScore = 0;
    }


    /**
     * calculates the amount the two person objects are compatible based on
     * their shared preferences
     * 
     * @return the percent compatibility
     */
    public double calculateCompatibility()
    {
        ArrayList<Preference> person1Preferences = person1.getPreferences();
        ArrayList<Preference> person2Preferences = person2.getPreferences();
        int sharedPreferences = 0;
        int totalPreferences =
            (person1Preferences.size() + person2Preferences.size());
        for (int i = 0; i < person1Preferences.size(); i++)
        {
            for (int j = 0; j < person2Preferences.size(); j++)
            {
                if (person1Preferences.get(i).equals(person2Preferences.get(j)))
                {
                    sharedPreferences += 2;
                }
            }
        }
        compatibilityScore =
            (sharedPreferences / (double)totalPreferences) * 100;
        return compatibilityScore;
    }


    /**
     * A basic getter for the person1 variable
     * 
     * @return the first person in the match
     */
    public Person getPerson1()
    {
        return person1;
    }


    /**
     * A basic getter for the person2 variable
     * 
     * @return the second person in the match
     */
    public Person getPerson2()
    {
        return person2;
    }


    /**
     * A basic getter for the compatabilityScore variable
     * 
     * @return the compatibility score between the people
     */
    public double getCompatabilityScore()
    {
        return compatibilityScore;
    }

}
