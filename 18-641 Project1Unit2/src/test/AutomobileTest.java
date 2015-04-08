package test;

import static org.junit.Assert.*;
import model.Automobile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.FileIO;
/**
 * 
 * @author hsuantzl
 *
 */
public class AutomobileTest {
    Automobile automobile;
    @Before
    public void setUp() throws Exception {
        FileIO io = new FileIO();
            
        // Read data from text file and store into an object
        automobile = io.buildAutoObject("FocusWagonZTW.txt");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAutomotiveBaseprice() {
        assertTrue(Double.compare(18445.0d, automobile.getBaseprice()) == 0);
    }
    
    /* Test the object built from text file */
    @Test
    public void testOptionSetName() {
        assertTrue(automobile.findOptionSet("Color") != null);
        assertTrue(automobile.findOptionSet("Transmission") != null);
        assertTrue(automobile.findOptionSet("Brakes") != null);
        assertTrue(automobile.findOptionSet("Side Impact Air Bags") != null);
        assertTrue(automobile.findOptionSet("Power Moonroof") != null);
    }
    @Test
    public void testAutomotiveOptions() {
        assertEquals(automobile.findOptionSet("Color"), automobile.findOption("Fort Knox Gold Clearcoat Metallic"));
    } 
}
