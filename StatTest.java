

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StatTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StatTest
{
    Statistics test;
    /**
     * Default constructor for test class StatTest
     */
    public StatTest()
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
    public void checkObjectCreation()
    {
        Statistics statisti2 = new Statistics();
    }

    

    @Test
    public void avgReviewTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(1.3043068539986162, statisti1.averageReview(), 0.1);
    }

    @Test
    public void numberOfAllListingsTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(53904, statisti1.numberOfAllListings());
    }

    @Test
    public void availableNeighbourhoodTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals("Harrow", statisti1.availableNeighbourhood());
    }

    @Test
    public void numberOfEntireHomesTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(27175, statisti1.numberOfEntireHomes());
    }

    @Test
    public void bestReiviewNeighbourhoodTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals("Westminster", statisti1.bestReiviewNeighbourhood());
    }

    @Test
    public void expensiveNeighbourhoodTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals("Richmond upon Thames", statisti1.expensiveNeighbourhood());
    }

    @Test
    public void worstReiviewNeighbourhoodTest()
    {
        Statistics statisti2 = new Statistics();
        assertEquals("Bexley", statisti2.worstReiviewNeighbourhood());
    }

    @Test
    public void neighbourhoodWithLeastHomesTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals("Islington", statisti1.neighbourhoodWithLeastHomes());
    }

    @Test
    public void countPropertiesTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(266, statisti1.countProperties("Harrow"));
    }

    @Test
    public void averageReviewPerNeighbourhood()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(1.0205357142857143, statisti1.averageReviewPerNeighbourhood("Harrow"), 0.1);
    }

    @Test
    public void availabilityPerNeighbourhoodTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(240, statisti1.availabilityPerNeighbourhood("Harrow"));
    }

    @Test
    public void numberOfListingsWithScoresTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(168, statisti1.numberOfListingsWithScores("Harrow"));
    }

    @Test
    public void pricesPerNieghbourhoodTest()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(135, statisti1.pricesPerNieghbourhood("Harrow"));
    }
}
