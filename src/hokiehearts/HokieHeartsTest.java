package hokiehearts;

import student.TestCase;

/**
 * Tests the HokieHearts class.
 *
 * @author Hokie Hearts Team
 * @version 2026.09.20
 */
public class HokieHeartsTest extends TestCase
{
    private HokieHearts system;
    private Person alex;
    private Person blake;


    /**
     * Creates an empty system and two valid people.
     */
    public void setUp()
    {
        system = new HokieHearts();
        alex = new Person("Alex", 20, "alex@vt.edu");
        blake = new Person("Blake", 21, "blake@vt.edu");
    }


    /**
     * Tests valid and invalid VT email addresses.
     */
    public void testIsValidVTEmail()
    {
        assertTrue(system.isValidVTEmail("alex@vt.edu"));
        assertTrue(system.isValidVTEmail(" ALEX@VT.EDU "));
        assertFalse(system.isValidVTEmail("alex@gmail.com"));
        assertFalse(system.isValidVTEmail("@vt.edu"));
        assertFalse(system.isValidVTEmail("alex@@vt.edu"));
        assertFalse(system.isValidVTEmail(null));
    }


    /**
     * Tests adding people and rejecting invalid or duplicate people.
     */
    public void testAddPerson()
    {
        assertTrue(system.addPerson(alex));
        assertFalse(system.addPerson(alex));
        assertFalse(system.addPerson(null));
        assertFalse(system.addPerson(new Person("", 20, "empty@vt.edu")));
        assertFalse(system.addPerson(new Person("Taylor", 0, "t@vt.edu")));
        assertFalse(system.addPerson(
            new Person("Taylor", 20, "taylor@gmail.com")));
        assertEquals(1, system.getPeople().size());
    }


    /**
     * Tests finding known and unknown people.
     */
    public void testFindPerson()
    {
        system.addPerson(alex);
        assertEquals(alex, system.findPerson(" ALEX@VT.EDU "));
        assertNull(system.findPerson("missing@vt.edu"));
        assertNull(system.findPerson(null));
    }


    /**
     * Tests removing known, unknown, and null people.
     */
    public void testRemovePerson()
    {
        system.addPerson(alex);
        assertTrue(system.removePerson(alex));
        assertFalse(system.removePerson(alex));
        assertFalse(system.removePerson(null));
    }


    /**
     * Tests valid and invalid match creation.
     */
    public void testCreateMatch()
    {
        system.addPerson(alex);
        system.addPerson(blake);

        assertNotNull(system.createMatch(alex, blake));
        assertNull(system.createMatch(alex, alex));
        assertNull(system.createMatch(alex, null));
        assertNull(system.createMatch(
            alex,
            new Person("Casey", 22, "casey@vt.edu")));
    }


    /**
     * Tests finding one highest-scoring match.
     */
    public void testFindBestMatches()
    {
        Person casey = new Person("Casey", 22, "casey@vt.edu");
        Person jordan = new Person("Jordan", 20, "jordan@vt.edu");

        addPreference(alex, "Music");
        addPreference(alex, "Hiking");
        addPreference(alex, "Pizza");
        addPreference(alex, "Reading");

        addPreference(blake, "Music");
        addPreference(blake, "Hiking");
        addPreference(blake, "Pizza");
        addPreference(blake, "Basketball");

        addPreference(casey, "Music");
        addPreference(casey, "Gaming");

        addPreference(jordan, "Music");
        addPreference(jordan, "Hiking");
        addPreference(jordan, "Pizza");
        addPreference(jordan, "Reading");

        system.addPerson(alex);
        system.addPerson(blake);
        system.addPerson(casey);
        system.addPerson(jordan);

        assertEquals(1, system.findBestMatches(alex).size());
        assertEquals(
            jordan,
            system.findBestMatches(alex).get(0).getSecondPerson());
        assertEquals(
            100.0,
            system.findBestMatches(alex).get(0).getCompatibilityScore(),
            0.01);
    }


    /**
     * Tests tied best matches and invalid input.
     */
    public void testFindBestMatchesTiesAndInvalidInput()
    {
        Person casey = new Person("Casey", 22, "casey@vt.edu");
        addPreference(alex, "Music");
        addPreference(blake, "Music");
        addPreference(casey, "Music");
        system.addPerson(alex);
        system.addPerson(blake);
        system.addPerson(casey);

        assertEquals(2, system.findBestMatches(alex).size());
        assertTrue(system.findBestMatches(null).isEmpty());
        assertTrue(system.findBestMatches(
            new Person("Unknown", 20, "unknown@vt.edu")).isEmpty());

        HokieHearts onePersonSystem = new HokieHearts();
        onePersonSystem.addPerson(alex);
        assertTrue(onePersonSystem.findBestMatches(alex).isEmpty());
    }


    private void addPreference(Person person, String value)
    {
        person.addPreference(new Preference("Interest", value));
    }
}
