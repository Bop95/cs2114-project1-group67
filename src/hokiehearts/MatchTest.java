package hokiehearts;

import student.TestCase;

/**
 * Tests the Match class.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class MatchTest extends TestCase
{
    private Person alex;
    private Person blake;


    /**
     * Creates two people whose union has five preferences and whose
     * intersection has three preferences.
     */
    public void setUp()
    {
        alex = new Person("Alex", 20, "alex@vt.edu");
        blake = new Person("Blake", 21, "blake@vt.edu");

        alex.addPreference(new Preference("Interest", "Music"));
        alex.addPreference(new Preference("Interest", "Hiking"));
        alex.addPreference(new Preference("Food", "Pizza"));
        alex.addPreference(new Preference("Study", "Library"));

        blake.addPreference(new Preference("Interest", "Music"));
        blake.addPreference(new Preference("Interest", "Hiking"));
        blake.addPreference(new Preference("Food", "Pizza"));
        blake.addPreference(new Preference("Sport", "Basketball"));
    }


    /**
     * Tests people and shared preferences.
     */
    public void testConstructorAndSharedPreferences()
    {
        Match match = new Match(alex, blake);

        assertEquals(alex, match.getFirstPerson());
        assertEquals(blake, match.getSecondPerson());
        assertEquals(3, match.findSharedPreferences().size());
        assertEquals(3, match.getSharedPreferences().size());
        assertTrue(match.getSharedPreferences().contains(
            new Preference("Interest", "Music")));
    }


    /**
     * Tests the required three-out-of-five calculation.
     */
    public void testCalculateCompatibility()
    {
        Match match = new Match(alex, blake);
        assertEquals(60.0, match.calculateCompatibility(), 0.01);
        assertEquals(60.0, match.getCompatibilityScore(), 0.01);
    }


    /**
     * Tests people with no preferences.
     */
    public void testNoPreferences()
    {
        Person first = new Person("A", 18, "a@vt.edu");
        Person second = new Person("B", 19, "b@vt.edu");
        Match match = new Match(first, second);

        assertTrue(match.getSharedPreferences().isEmpty());
        assertEquals(0.0, match.getCompatibilityScore(), 0.01);
    }


    /**
     * Tests null constructor arguments.
     */
    public void testNullPeople()
    {
        Exception exception = null;
        try
        {
            new Match(null, blake);
        }
        catch (IllegalArgumentException error)
        {
            exception = error;
        }
        assertNotNull(exception);
    }
}
