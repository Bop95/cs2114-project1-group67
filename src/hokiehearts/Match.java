package hokiehearts;

import java.util.ArrayList;

/**
 * Compares two people and calculates their compatibility.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class Match
{
    private Person firstPerson;
    private Person secondPerson;
    private ArrayList<Preference> sharedPreferences;
    private double compatibilityScore;


    /**
     * Creates and calculates a match between two people.
     *
     * @param firstPerson the first person
     * @param secondPerson the second person
     * @throws IllegalArgumentException if either person is null
     */
    public Match(Person firstPerson, Person secondPerson)
    {
        if (firstPerson == null || secondPerson == null)
        {
            throw new IllegalArgumentException("People cannot be null.");
        }

        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
        sharedPreferences = findSharedPreferences();
        compatibilityScore = calculateCompatibility();
    }


    /**
     * Gets the first person.
     *
     * @return the first person
     */
    public Person getFirstPerson()
    {
        return firstPerson;
    }


    /**
     * Gets the second person.
     *
     * @return the second person
     */
    public Person getSecondPerson()
    {
        return secondPerson;
    }


    /**
     * Finds the unique preferences shared by both people.
     *
     * @return the shared preferences
     */
    public ArrayList<Preference> findSharedPreferences()
    {
        ArrayList<Preference> shared = new ArrayList<Preference>();
        for (Preference preference : firstPerson.getPreferences())
        {
            if (secondPerson.hasPreference(preference)
                && !shared.contains(preference))
            {
                shared.add(preference);
            }
        }
        return shared;
    }


    /**
     * Calculates shared preferences divided by all unique preferences.
     *
     * @return a percentage from 0 through 100
     */
    public double calculateCompatibility()
    {
        ArrayList<Preference> unique = new ArrayList<Preference>();
        addUnique(unique, firstPerson.getPreferences());
        addUnique(unique, secondPerson.getPreferences());

        if (unique.isEmpty())
        {
            return 0.0;
        }
        return sharedPreferences.size() * 100.0 / unique.size();
    }


    /**
     * Gets the calculated compatibility percentage.
     *
     * @return the compatibility score
     */
    public double getCompatibilityScore()
    {
        return compatibilityScore;
    }


    /**
     * Gets the shared preferences.
     *
     * @return the shared preferences
     */
    public ArrayList<Preference> getSharedPreferences()
    {
        return sharedPreferences;
    }


    private void addUnique(
        ArrayList<Preference> destination,
        ArrayList<Preference> source)
    {
        for (Preference preference : source)
        {
            if (!destination.contains(preference))
            {
                destination.add(preference);
            }
        }
    }
}
