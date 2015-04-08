package test;

import static org.junit.Assert.*;
import model.Automotive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.FileIO;
/**
 * 
 * @author hsuantzl
 *
 */
public class AutomotiveTest {
    Automotive automotive;
    Automotive newAutomotive;
    @Before
    public void setUp() throws Exception {
        FileIO io = new FileIO();
            
        // Read data from text file and store into an object
        automotive = io.buildAutoObject("FocusWagonZTW.txt");
        // Serialization and deSerialization
        newAutomotive = io.serializeAuto(automotive);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAutomotiveBaseprice() {
        assertTrue(Math.abs(18445-automotive.getBaseprice()) < 0.0001);
    }
    
    /* Test the object built from text file */
    
    @Test
    public void testOptionSetName() {
        assertTrue(automotive.findOptionSet("Color") != null);
        assertTrue(automotive.findOptionSet("Transmission") != null);
        assertTrue(automotive.findOptionSet("Brakes") != null);
        assertTrue(automotive.findOptionSet("Side Impact Air Bags") != null);
        assertTrue(automotive.findOptionSet("Power Moonroof") != null);
    }
    @Test
    public void testAutomotiveOptions() {
        assertEquals(automotive.findOptionSet("Color"), automotive.findOption("Fort Knox Gold Clearcoat Metallic"));
    }
    
    /* Test the object built from deSerialization */
    
    @Test
    public void testAutomotiveBasepriceAfterSerialization() {
        assertTrue(Math.abs(18445-newAutomotive.getBaseprice()) < 0.0001);
    }
    
    @Test
    public void testOptionSetNameAfterSerialization() {
        assertTrue(newAutomotive.findOptionSet("Color") != null);
        assertTrue(newAutomotive.findOptionSet("Transmission") != null);
        assertTrue(newAutomotive.findOptionSet("Brakes") != null);
        assertTrue(newAutomotive.findOptionSet("Side Impact Air Bags") != null);
        assertTrue(newAutomotive.findOptionSet("Power Moonroof") != null);
    }
    @Test
    public void testAutomotiveOptionsAfterSerialization() {
        assertEquals(newAutomotive.findOptionSet("Color"), newAutomotive.findOption("Fort Knox Gold Clearcoat Metallic"));
    }
    
}
