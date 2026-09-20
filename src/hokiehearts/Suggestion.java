package hokiehearts;

/**
 * Creates a date or conversation idea for a match.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class Suggestion
{
    private Match match;
    private String suggestion;


    /**
     * Creates a suggestion for a valid match.
     *
     * @param match the match receiving a suggestion
     * @throws IllegalArgumentException when match is null
     */
    public Suggestion(Match match)
    {
        if (match == null)
        {
            throw new IllegalArgumentException("Match cannot be null.");
        }
        this.match = match;
        suggestion = generateSuggestion();
    }


    /**
     * Generates an idea from the first shared preference.
     *
     * @return a date or conversation idea
     */
    public String generateSuggestion()
    {
        if (match.getSharedPreferences().isEmpty())
        {
            return "Meet for coffee on campus and get to know each other.";
        }

        Preference shared = match.getSharedPreferences().get(0);
        String value = shared.getValue();
        String normalized = value == null ? "" : value.trim().toLowerCase();

        if (normalized.contains("music"))
        {
            return "Go to a live music event together.";
        }
        if (normalized.contains("hik") || normalized.contains("outdoor"))
        {
            return "Take a hike together near campus.";
        }
        if (normalized.contains("pizza") || normalized.contains("food")
            || normalized.contains("cook"))
        {
            return "Try a local restaurant together.";
        }
        if (normalized.contains("sport") || normalized.contains("football")
            || normalized.contains("basketball"))
        {
            return "Attend a Hokies sporting event together.";
        }
        if (normalized.contains("study") || normalized.contains("library")
            || normalized.contains("reading"))
        {
            return "Meet for a coffee and study session.";
        }
        return "Talk about your shared interest in " + value + ".";
    }


    /**
     * Gets the generated suggestion.
     *
     * @return the suggestion
     */
    public String getSuggestion()
    {
        return suggestion;
    }
}
