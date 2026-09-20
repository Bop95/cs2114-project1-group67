// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- tuetranminh
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
package hokiehearts;

import java.util.ArrayList;

/**
 * Manages the users of the Hokie Hearts application. 
 *
 * @author Tue Tran
 * @version 2026.09.20
 */
public class HokieHearts {

    private ArrayList<Person> users;

    /**
     * Creates an empty Hokie Hearts system with no users.
     */
    public HokieHearts() {
        users = new ArrayList<Person>();
    }


    /**
     * Adds a user to the system. Null users and users whose email is already
     * taken are ignored.
     *
     * @param person the user to add
     */
    public void addPerson(Person person) {
        if (person == null || findPerson(person.getEmail()) != null) {
            return;
        }
        users.add(person);
    }


    /**
     * Removes a user from the system.
     *
     * @param person the user to remove
     * @return true if the user was found and removed.
     */
    public boolean removePerson(Person person) {
        return users.remove(person);
    }


    /**
     * Searches for a user by VT email. Capitalization is ignored, so
     * "alex@vt.edu" and "ALEX@VT.EDU" find the same user.
     *
     * @param email the email to search for
     * @return the matching user, or null if no user has that email
     */
    public Person findPerson(String email) {
        if (email == null) {
            return null;
        }
        for (Person p : users) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                return p;
            }
        }
        return null;
    }


    /**
     * Creates a match between two different users.
     *cal
     * @param p1 the first user
     * @param p2 the second user
     * @return a new Match, or null if either user is null or both are the
     *         same user
     */
    public Match createMatch(Person p1, Person p2) {
        if (p1 == null || p2 == null || p1 == p2) {
            return null;
        }
        return new Match(p1, p2);
    }


    /**
     * Checks whether an email is a valid Virginia Tech address, meaning it
     * ends with "@vt.edu" and has at least one character in front of it.
     *
     * @param email the email to check
     * @return true if the email is a valid VT address, false otherwise
     */
    public boolean isValidVTEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@vt.edu")
            && email.length() > "@vt.edu".length();
    }
}