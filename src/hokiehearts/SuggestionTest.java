package hokiehearts;

import student.TestCase;

/**
 * Tests the Suggestion stretch feature.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class SuggestionTest extends TestCase
{
    /**
     * Tests a suggestion based on a shared interest.
     */
    public void testSharedPreferenceSuggestion()
    {
        Person first = new Person("Alex", 20, "alex@vt.edu");
        Person second = new Person("Blake", 21, "blake@vt.edu");
        first.addPreference(new Preference("Interest", "Music"));
        second.addPreference(new Preference("Interest", "Music"));

        Suggestion suggestion = new Suggestion(new Match(first, second));
        assertEquals(
            "Go to a live music event together.",
            suggestion.getSuggestion());
    }


    /**
     * Tests the fallback when no preferences are shared.
     */
    public void testNoSharedPreferenceSuggestion()
    {
        Person first = new Person("Alex", 20, "alex@vt.edu");
        Person second = new Person("Blake", 21, "blake@vt.edu");

        Suggestion suggestion = new Suggestion(new Match(first, second));
        assertEquals(
            "Meet for coffee on campus and get to know each other.",
            suggestion.getSuggestion());
    }


    /**
     * Tests rejection of a null match.
     */
    public void testNullMatch()
    {
        Exception exception = null;
        try
        {
            new Suggestion(null);
        }
        catch (IllegalArgumentException error)
        {
            exception = error;
        }
        assertNotNull(exception);
    }
}
