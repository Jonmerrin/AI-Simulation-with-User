

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WorldTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WorldTest
{
    /**
     * Default constructor for test class WorldTest
     */
    public WorldTest()
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
    public void testGetRandomRoom()
    {
        World world1 = new World(1, 1, "Bob");
        Room room1 = new Room("George");
        world1.setRoom(0, 0, room1);
        assertNotNull(world1.getRandomRoom());
        Room room2 = world1.getRandomRoom();
        assertSame(room1, room2);
//         assertSame("George", room2.getRoomName());
    }


}



