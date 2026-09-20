package hokiehearts;

/**
 * Represents one category and value used for matching people.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class Preference
{
    private String category;
    private String value;


    /**
     * Creates a preference.
     *
     * @param category the preference category
     * @param value the preference value
     */
    public Preference(String category, String value)
    {
        this.category = category;
        this.value = value;
    }


    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory()
    {
        return category;
    }


    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue()
    {
        return value;
    }


    /**
     * Compares preferences by category and value, ignoring capitalization.
     *
     * @param other the object to compare
     * @return true when both fields match
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof Preference))
        {
            return false;
        }

        Preference preference = (Preference)other;
        return sameText(category, preference.category)
            && sameText(value, preference.value);
    }


    /**
     * Produces a hash code consistent with equals.
     *
     * @return the hash code
     */
    @Override
    public int hashCode()
    {
        int categoryHash = normalized(category).hashCode();
        int valueHash = normalized(value).hashCode();
        return 31 * categoryHash + valueHash;
    }


    /**
     * Displays a readable category and value.
     *
     * @return the preference text
     */
    @Override
    public String toString()
    {
        return category + ": " + value;
    }


    private boolean sameText(String first, String second)
    {
        return normalized(first).equals(normalized(second));
    }


    private String normalized(String text)
    {
        if (text == null)
        {
            return "";
        }
        return text.trim().toLowerCase();
    }
}
