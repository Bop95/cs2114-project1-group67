package hokiehearts;

import java.util.ArrayList;

/**
 * Represents a person using the Hokie Hearts application.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.19
 */
public class Person
{
    private String name;
    private int age;
    private String email;
    private ArrayList<Preference> preferences;


    /**
     * Creates a new person with no saved preferences.
     *
     * @param name the person's name
     * @param age the person's age
     * @param email the person's email address
     */
    public Person(String name, int age, String email)
    {
        this.name = name;
        this.age = age;
        this.email = email;
        preferences = new ArrayList<Preference>();
    }


    /**
     * Gets the person's name.
     *
     * @return the person's name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Gets the person's age.
     *
     * @return the person's age
     */
    public int getAge()
    {
        return age;
    }


    /**
     * Gets the person's email address.
     *
     * @return the person's email address
     */
    public String getEmail()
    {
        return email;
    }


    /**
     * Gets the person's preferences in the order they were added.
     *
     * @return the person's preferences
     */
    public ArrayList<Preference> getPreferences()
    {
        return preferences;
    }


    /**
     * Adds a non-null preference.
     *
     * @param preference the preference to add
     */
    public void addPreference(Preference preference)
    {
        if (preference != null)
        {
            preferences.add(preference);
        }
    }


    /**
     * Checks whether this person has a preference.
     *
     * @param preference the preference to find
     * @return true when the preference is stored; false otherwise
     */
    public boolean hasPreference(Preference preference)
    {
        return preference != null && preferences.contains(preference);
    }
}
