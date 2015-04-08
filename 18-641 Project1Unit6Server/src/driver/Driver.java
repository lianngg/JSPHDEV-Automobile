package driver;

import java.sql.SQLException;
import adapter.BuildAuto;
import util.DBUtils;

/**
 * 
 * @author hsuantzl
 * This Driver class test the new functionality of buildAuto
 * which interact with the database
 */
public class Driver {
    public static void main(String[] args) {
        DBUtils db = new DBUtils();
        try {
            db.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BuildAuto buildAuto = new BuildAuto();
        // Build Focus Wagon ZTW
        buildAuto.buildAuto("FocusWagonZTW.txt", "txt");
        // Build Model S
        buildAuto.buildAuto("TeslaModelS.txt", "txt");
        // Print them out
        System.out.println("[Driver] Printing Focus Wagon ZTW");
        buildAuto.printAuto("Focus Wagon ZTW");
        System.out.println("\n[Driver] Printing Model S");
        buildAuto.printAuto("Model S");
        
        // Update Model S
        System.out.println("\n[Driver] Updating Model S");
        buildAuto.updateOptionName("Model S", "Color", "Blue Metallic", "Blue");
        buildAuto.updateOptionPrice("Model S", "Parking Sensors", "present", 4000d);
        // Print Model S to check whether it updates the database
        System.out.println("\n[Driver] Printing Model S");
        buildAuto.printAuto("Model S");
        
        // Delete Focus Wagon ZTW
        System.out.println("\n[Driver] Deleting Focus Wagon ZTW");
        buildAuto.deleteAutomobile("Focus Wagon ZTW");
        
        // Print Focus Wagon ZTW to check whether it exists in the database
        System.out.println("[Driver] Printing Focus Wagon ZTW");
        buildAuto.printAuto("Focus Wagon ZTW");
    }
}
