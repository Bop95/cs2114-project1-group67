package hokiehearts;

import java.util.ArrayList;

/**
 * Stores Hokie Hearts users and creates matches.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class HokieHearts
{
    private ArrayList<Person> people;


    /** Creates an empty Hokie Hearts system. */
    public HokieHearts()
    {
        people = new ArrayList<Person>();
    }


    /**
     * Adds a valid person whose email is not already registered.
     * @param person the person to add
     * @return true when the person is added
     */
    public boolean addPerson(Person person)
    {
        if (!isValidPerson(person) || findPerson(person.getEmail()) != null)
        {
            return false;
        }
        return people.add(person);
    }


    /**
     * Removes a person.
     * @param person the person to remove
     * @return true when the person was stored and removed
     */
    public boolean removePerson(Person person)
    {
        return person != null && people.remove(person);
    }


    /**
     * Finds a person by VT email, ignoring capitalization.
     * @param email the email to find
     * @return the matching person, or null when not found
     */
    public Person findPerson(String email)
    {
        if (email == null)
        {
            return null;
        }
        for (Person person : people)
        {
            if (person.getEmail().equalsIgnoreCase(email.trim()))
            {
                return person;
            }
        }
        return null;
    }


    /**
     * Creates a match between two different stored people.
     * @param firstPerson the first person
     * @param secondPerson the second person
     * @return the match, or null for invalid input
     */
    public Match createMatch(Person firstPerson, Person secondPerson)
    {
        if (firstPerson == null || secondPerson == null
            || firstPerson == secondPerson || !people.contains(firstPerson)
            || !people.contains(secondPerson))
        {
            return null;
        }
        return new Match(firstPerson, secondPerson);
    }


    /**
     * Finds every person tied for the selected person's highest score.
     * @param person the person looking for a match
     * @return the best matches
     */
    public ArrayList<Match> findBestMatches(Person person)
    {
        ArrayList<Match> bestMatches = new ArrayList<Match>();
        if (person == null || !people.contains(person))
        {
            return bestMatches;
        }

        double bestScore = -1.0;
        for (Person candidate : people)
        {
            if (candidate != person)
            {
                Match match = new Match(person, candidate);
                double score = match.getCompatibilityScore();
                if (score > bestScore)
                {
                    bestMatches.clear();
                    bestMatches.add(match);
                    bestScore = score;
                }
                else if (Double.compare(score, bestScore) == 0)
                {
                    bestMatches.add(match);
                }
            }
        }
        return bestMatches;
    }


    /**
     * Checks whether an address belongs to the vt.edu domain.
     * @param email the email to check
     * @return true for a valid VT email
     */
    public boolean isValidVTEmail(String email)
    {
        if (email == null)
        {
            return false;
        }
        String cleaned = email.trim().toLowerCase();
        int atIndex = cleaned.indexOf('@');
        return atIndex > 0 && atIndex == cleaned.lastIndexOf('@')
            && cleaned.endsWith("@vt.edu");
    }


    /** @return the stored people */
    public ArrayList<Person> getPeople()
    {
        return people;
    }


    private boolean isValidPerson(Person person)
    {
        return person != null && person.getName() != null
            && !person.getName().trim().isEmpty() && person.getAge() > 0
            && isValidVTEmail(person.getEmail());
    }
}
