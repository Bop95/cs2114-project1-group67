package hokiehearts;

import student.TestCase;

/**
 * Tests the Person class.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.19
 */
public class PersonTest extends TestCase
{
    private Person person;
    private Preference music;
    private Preference hiking;


    /**
     * Creates fresh test objects before each test.
     */
    public void setUp()
    {
        person = new Person("Alex", 20, "alex@vt.edu");
        music = new Preference("Interest", "Music");
        hiking = new Preference("Interest", "Hiking");
    }


    /**
     * Tests the constructor and basic getters.
     */
    public void testConstructorAndGetters()
    {
        assertEquals("Alex", person.getName());
        assertEquals(20, person.getAge());
        assertEquals("alex@vt.edu", person.getEmail());
    }


    /**
     * Tests that a new person has no preferences.
     */
    public void testPreferencesStartEmpty()
    {
        assertTrue(person.getPreferences().isEmpty());
    }


    /**
     * Tests adding multiple preferences and preserving their order.
     */
    public void testAddPreferencesInOrder()
    {
        person.addPreference(music);
        person.addPreference(hiking);

        assertEquals(2, person.getPreferences().size());
        assertEquals(music, person.getPreferences().get(0));
        assertEquals(hiking, person.getPreferences().get(1));
    }


    /**
     * Tests that a null preference is not added.
     */
    public void testAddNullPreference()
    {
        person.addPreference(null);
        assertTrue(person.getPreferences().isEmpty());
    }


    /**
     * Tests finding stored and missing preferences.
     */
    public void testHasPreference()
    {
        person.addPreference(music);

        assertTrue(person.hasPreference(music));
        assertFalse(person.hasPreference(hiking));
    }


    /**
     * Tests that null is never reported as a saved preference.
     */
    public void testHasNullPreference()
    {
        assertFalse(person.hasPreference(null));
    }
}
