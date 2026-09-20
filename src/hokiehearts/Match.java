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
     * @param firstPerson the first person
     * @param secondPerson the second person
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


    /** @return the first person */
    public Person getFirstPerson()
    {
        return firstPerson;
    }


    /** @return the first person, using the original team method name */
    public Person getPerson1()
    {
        return getFirstPerson();
    }


    /** @return the second person */
    public Person getSecondPerson()
    {
        return secondPerson;
    }


    /** @return the second person, using the original team method name */
    public Person getPerson2()
    {
        return getSecondPerson();
    }


    /** @return the unique preferences shared by both people */
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


    /** @return shared preferences divided by all unique preferences */
    public double calculateCompatibility()
    {
        ArrayList<Preference> unique = new ArrayList<Preference>();
        addUnique(unique, firstPerson.getPreferences());
        addUnique(unique, secondPerson.getPreferences());
        if (unique.isEmpty())
        {
            compatibilityScore = 0.0;
        }
        else
        {
            compatibilityScore = sharedPreferences.size() * 100.0
                / unique.size();
        }
        return compatibilityScore;
    }


    /** @return the compatibility score */
    public double getCompatibilityScore()
    {
        return compatibilityScore;
    }


    /** @return the score, using the original team's method spelling */
    public double getCompatabilityScore()
    {
        return getCompatibilityScore();
    }


    /** @return the shared preferences */
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
