package hokiehearts;

import student.TestCase;

/**
 * Tests the Preference class.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class PreferenceTest extends TestCase
{
    /**
     * Tests construction and getters.
     */
    public void testConstructorAndGetters()
    {
        Preference preference = new Preference("Interest", "Hiking");
        assertEquals("Interest", preference.getCategory());
        assertEquals("Hiking", preference.getValue());
    }


    /**
     * Tests equality and its edge cases.
     */
    public void testEquals()
    {
        Preference first = new Preference("Interest", "Hiking");
        Preference same = new Preference("interest", " hiking ");
        Preference different = new Preference("Interest", "Music");

        assertTrue(first.equals(first));
        assertTrue(first.equals(same));
        assertFalse(first.equals(different));
        assertFalse(first.equals(null));
        assertFalse(first.equals("Hiking"));
        assertEquals(first.hashCode(), same.hashCode());
    }


    /**
     * Tests the readable representation.
     */
    public void testToString()
    {
        Preference preference = new Preference("Interest", "Hiking");
        assertEquals("Interest: Hiking", preference.toString());
    }
}
