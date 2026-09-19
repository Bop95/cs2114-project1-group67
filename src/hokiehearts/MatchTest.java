package hokiehearts;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Match class and its methods
 * 
 * @author Joey Gentlesk
 * @version Sep 19, 2026
 */
public class MatchTest
    extends TestCase
{
    private Person p1;
    private Person p2;
    private Preference sharedPreference;
    private Preference differentPreference1;
    private Preference differentPreference2;
    private Match m1;

    /**
     * Creates fresh tests objects for each test
     */
    public void setUp()
    {

        sharedPreference = new Preference("Interest", "Music");
        differentPreference1 = new Preference("Interest", "Hiking");
        differentPreference2 = new Preference("Hobby", "Knitting");
        p1 = new Person("John", "john@vt.edu", 18);
        p1.setPreferences(sharedPreference);
        p1.setPreferences(differentPreference1);
        p2 = new Person("Sarag", "sarah@vt.edu", 19);
        p2.setPreferences(sharedPreference);
        p2.setPreferences(differentPreference2);
        m1 = new Match(p1, p2);

    }


    /**
     * Tests the method calcualteCompatibility
     */
    public void testCalculateCompatibility()
    {
        assertEquals(50.0, m1.calculateCompatibility(), 0.01);
    }


    /**
     * Tests the get method for the first person
     */
    public void testGetPerson1()
    {
        assertEquals(p1, m1.getPerson1());
    }


    /**
     * Tests the get method for the second person
     */
    public void testGetPerson2()
    {
        assertEquals(p2, m1.getPerson2());
    }


    /**
     * Tests the get method for the second person
     */
    public void testGetCompatibilityScore()
    {
        assertEquals(0.0, m1.getCompatabilityScore(), 0.01);
    }

}
