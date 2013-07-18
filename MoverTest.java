

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MoverTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MoverTest
{
    /**
     * Default constructor for test class MoverTest
     */
    public MoverTest()
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
    public void test1()
    {
        Mover mover1 = new Mover();
        assertEquals("Alf", mover1.getName());
        Mover mover2 = new Mover("Ralph");
        assertEquals("Ralph", mover2.getName());
        Mover mover3 = new Mover("Fred", new World());
        assertNull(mover2.getWorld());
        assertNotNull(mover3.getWorld());
        World world1 = new World();
        mover2.setWorld(world1);
        assertSame(world1, mover2.getWorld());
    }





    @Test
    public void testGetCurrentLocation()
    {
        World world1 = new World(10, 10, "Jupiter");
        Mover mover1 = new Mover("random", world1);
        assertNotNull(mover1.getCurrentLocation());
    }
}





