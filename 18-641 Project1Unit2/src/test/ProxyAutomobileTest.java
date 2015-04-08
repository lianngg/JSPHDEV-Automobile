package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import adapter.BuildAuto;
/**
 * 
 * @author hsuantzl
 *
 */
public class ProxyAutomobileTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBuildAutoAPI() {
        BuildAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FocusWagonZTW.txt");
        assertEquals("Ford", buildAuto.getInstace("Focus Wagon ZTW").getMake());
        assertEquals("Focus Wagon ZTW", buildAuto.getInstace("Focus Wagon ZTW").getModel());
        assertTrue(Double.compare(18445.0d, buildAuto.getInstace("Focus Wagon ZTW").getBaseprice()) == 0);
    }
    
    @Test
    public void testUpdateOptionSetName() {
        BuildAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FocusWagonZTW.txt");
        assertTrue(buildAuto.getInstace("Focus Wagon ZTW").findOptionSet("Color") != null);
        buildAuto.updateOptionSetName("Focus Wagon ZTW", "Color", "Colour");
        assertEquals(null, buildAuto.getInstace("Focus Wagon ZTW").findOptionSet("Color"));
        assertTrue(buildAuto.getInstace("Focus Wagon ZTW").findOptionSet("Colour") != null);
    }
    
    @Test
    public void testUpdateOptionPrice() {
        BuildAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FocusWagonZTW.txt");
        assertTrue(buildAuto.getInstace("Focus Wagon ZTW").toString().contains("manual: $-815.0"));
        buildAuto.updateOptionPrice("Focus Wagon ZTW", "Transmission", "manual", 100);
        assertTrue(buildAuto.getInstace("Focus Wagon ZTW").toString().contains("manual: $100.0"));
    }
    @Test
    public void testFixNegativePrice() {
        BuildAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FocusWagonZTW_wrong_price.txt");
        assertTrue(Double.compare(18445.0d, buildAuto.getInstace("Focus Wagon ZTW").getTotalPrice()) == 0);
    }  
    
    @Test
    public void testSetOptionChoice() {
        BuildAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FocusWagonZTW.txt");
        buildAuto.getInstace("Focus Wagon ZTW").setOptionChoice("Color", "Pitch Black Clearcoat");
        buildAuto.getInstace("Focus Wagon ZTW").setOptionChoice("Brakes", "ABS with Advance Trac");
        assertEquals("Pitch Black Clearcoat", buildAuto.getInstace("Focus Wagon ZTW").getOptionChoice("Color"));
        assertEquals("ABS with Advance Trac", buildAuto.getInstace("Focus Wagon ZTW").getOptionChoice("Brakes"));
    }  
    
    @Test
    public void testTotalPrice() {
        BuildAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FocusWagonZTW.txt");
        // $18445 + $0 + $1625 + $350 = $20420
        buildAuto.getInstace("Focus Wagon ZTW").setOptionChoice("Color", "Pitch Black Clearcoat");
        buildAuto.getInstace("Focus Wagon ZTW").setOptionChoice("Brakes", "ABS with Advance Trac");
        buildAuto.getInstace("Focus Wagon ZTW").setOptionChoice("Side Impact Air Bags", "present");
        assertTrue(Double.compare(20420.0d, buildAuto.getInstace("Focus Wagon ZTW").getTotalPrice()) == 0);
    } 

}
