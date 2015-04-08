package driver;

import scale.IEditOptions;
import adapter.BuildAuto;

/**
 * 
 * @author hsuantzl
 * Instantiate a Ford Wagon ZTW object and edit its properties with multi-threads.
 * 
 */
public class Driver {
    
    public static void main(String[] args) {
            
        // Instantiating BuildAuto
        BuildAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FocusWagonZTW.txt");
        
        // Create two threads of EditOptions that will modify the static LinkedHashMap Automobile
        IEditOptions editThread1 = new BuildAuto();   
        IEditOptions editThread2 = new BuildAuto();

        try {
            System.out.println("Update Power Moonroof: present to $0");
            editThread1.editOptionValue("Focus Wagon ZTW", "Power Moonroof", "present", 0d);
            Thread.sleep(100);
            System.out.println("Update Power Moonroof: present to $5566");
            editThread2.editOptionValue("Focus Wagon ZTW", "Power Moonroof", "present", 5566d);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // Test to ensure that two threads altering same property does not cause data corruption
        //buildAuto.printAuto("Focus Wagon ZTW");
        
        
    }
}
