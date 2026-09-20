// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- tuetranminh
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
package hokiehearts;

import student.TestCase;

/**
 * Tests the HokieHearts class.
 *
 * @author Tue Tran
 * @version 2026.09.20
 */
public class HokieHeartsTest extends TestCase {

    private HokieHearts system;
    private Person alex;
    private Person jamie;

    /**
     * Creates a fresh system and two valid users before every test.
     */
    public void setUp() {
        system = new HokieHearts();
        alex = new Person("Alex", 20, "alex@vt.edu");
        jamie = new Person("Jamie", 21, "jamie@vt.edu");
    }


    /**
     * A valid person is stored and can be found again.
     */
    public void testAddPersonValid() {
        system.addPerson(alex);
        assertEquals(alex, system.findPerson("alex@vt.edu"));
    }


    /**
     * Adding null does not crash and stores nothing.
     */
    public void testAddPersonNull() {
        system.addPerson(null);
        assertNull(system.findPerson("alex@vt.edu"));
    }


    /**
     * A second person with the same email is rejected; the original stays.
     */
    public void testAddPersonDuplicateEmail() {
        system.addPerson(alex);
        Person impostor = new Person("Fake", 30, "alex@vt.edu");
        system.addPerson(impostor);
        assertEquals(alex, system.findPerson("alex@vt.edu"));
    }


    /**
     * Removing a stored person returns true and the person is gone.
     */
    public void testRemovePersonStored() {
        system.addPerson(alex);
        assertTrue(system.removePerson(alex));
    }


    /**
     * Removing someone who was never added returns false.
     */
    public void testRemovePersonUnknown() {
        system.addPerson(alex);
        assertFalse(system.removePerson(jamie));
    }


    /**
     * Email lookup ignores capitalization.
     */
    public void testFindPersonIgnoresCase() {
        system.addPerson(alex);
        assertEquals(alex, system.findPerson("ALEX@VT.EDU"));
    }


    /**
     * An email nobody has returns null.
     */
    public void testFindPersonUnknownEmail() {
        system.addPerson(alex);
        assertNull(system.findPerson("nobody@vt.edu"));
    }


    /**
     * A null email returns null instead of crashing.
     */
    public void testFindPersonNullEmail() {
        system.addPerson(alex);
        assertNull(system.findPerson(null));
    }


    /**
     * Two different valid users produce a Match.
     */
    public void testCreateMatchValid() {
        system.addPerson(alex);
        system.addPerson(jamie);
        assertNotNull(system.createMatch(alex, jamie));
    }


    /**
     * A normal VT email is accepted, in any capitalization.
     */
    public void testIsValidVTEmailAccepts() {
        assertTrue(system.isValidVTEmail("alex@vt.edu"));
    }


    /**
     * Non-VT domains are rejected.
     */
    public void testIsValidVTEmailRejectsOtherDomain() {
        assertFalse(system.isValidVTEmail("alex@gmail.com"));
    }


    /**
     * A bare domain with no username in front is rejected.
     */
    public void testIsValidVTEmailRejectsEmptyUsername() {
        assertFalse(system.isValidVTEmail("@vt.edu"));
    }
}