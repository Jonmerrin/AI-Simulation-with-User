
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RoomTest
{
    /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
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
    public void testAddItems()
    {
        Room room1 = new Room();
        Item item1 = new Item();
        item1.setName("anvil");
        room1.addItem(item1);
        assertNotNull(room1.checkItem("anvil"));
        assertNull(room1.checkItem("bogus item"));
        item1.setName("balloon");
        assertNotNull(room1.checkItem("balloon"));
        assertNull(room1.checkItem("anvil"));
        assertNull(room1.checkItem("bogus item"));

    }


    @Test
    public void testToString()
    {
        Room R = new Room();
        assertEquals("[Roo]", R.toString());
        Room R2 = new Room("Kitchen");
        assertEquals("[Kit]", R2.toString());
    }


}




