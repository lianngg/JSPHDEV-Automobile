package driver;

import util.FileIO;
import model.Automobile;
import adapter.BuildAuto;

/**
 * 
 * @author hsuantzl
 * Instantiate a Ford Wagon ZTW object and write it to a file.
 * 
 */
public class Driver {
    
    public static void main(String[] args) {
            
        // Build automobile with the BuildAuto API
        System.out.println("[test] Build automobile with the BuildAuto API.");
        BuildAuto buildAuto = new BuildAuto();
        
        // Build automobile Focus Wagon ZTW
        System.out.println("[test] Building Focus Wagon ZTW...");
        buildAuto.buildAuto("FocusWagonZTW.txt");
        
        // Build automobile Model S
        System.out.println("[test] Building Model S...");
        buildAuto.buildAuto("TeslaModelS.txt");
        
        System.out.println("[test] Print Focus Wagon ZTW");
        buildAuto.printAuto("Focus Wagon ZTW");
        
        System.out.println("\n[test] Print Model S");
        buildAuto.printAuto("Model S");
        
        // Fix filename error
        System.out.println("\n[test] Test filename error using FocusWagonZTW.txt.txt");
        buildAuto.buildAuto("FocusWagonZTW.txt.txt");
        
        // Fix filename error
        System.out.println("\n[test] Test filename error using FocusWagonZTW");
        buildAuto.buildAuto("FocusWagonZTW");
            
        // Select options
        System.out.println("\n[test] Select options: Power Moonroof present, Transmission manual.");
        FileIO io = new FileIO();
        Automobile automobile = io.buildAutoObject("FocusWagonZTW.txt");
        automobile.setOptionChoice("Power Moonroof", "present");
        automobile.setOptionChoice("Transmission", "manual");
        automobile.print();
        
    }
}
