
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InteractionsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class InteractionsTest
{
    /**
     * Default constructor for test class InteractionsTest
     */
    public InteractionsTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }



    @Test
    public void testCompare()
    {
        Mover mover1 = new Mover("Bob");
        Mover mover2 = new Mover("Jimmy");
//         Interactions interact1 = new Interactions(mover1);
//         interact1.compare(mover1, mover2);
    }

    @Test
    public void testInteract()
    {
        Mover mover1 = new Mover();
        Mover mover2 = new Mover();
//         Interactions interact1 = new Interactions(mover1);
//         interact1.compare(mover1, mover2);
//         interact1.interact(mover1, mover2);
    }

    @Test
    public void testRandomInteract()
    {
        Mover mover1 = new Mover("random");
        Mover mover2 = new Mover("random");
//         Interactions interact1 = new Interactions(mover1);
//         Interactions interact2 = new Interactions(mover2);
//         interact1.compare(mover1, mover2);
//         interact1.interact(mover1, mover2);
//         interact2.compare(mover2, mover1);
//         interact2.interact(mover2, mover1);
    }
}



