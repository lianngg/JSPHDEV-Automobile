package exception;

import java.io.File;

import adapter.BuildAuto;
import model.Automobile;
/**
 * 
 * @author hsuantzl
 *
 */
public class FixExceptionsHelper {
    public void fix0(int errno) {
        System.exit(1);
    }
    public void fix1(int errno) {
        System.exit(1);
    }
    public void fix2(int errno) {
        System.exit(1);
    }
    
    /* Fix filename which is without extension or with duplicated extension 
     * Assume default the filename is "modelName.txt". 
     */
    public void fix3(int errno, String filename) {
        File f = new File(filename);
        if(!f.exists() || f.isDirectory()) {
            String[] part = filename.split("\\.");
            if(part.length >= 2)
                filename = part[0];
            f = new File(filename);
        }
        if(!f.exists() || f.isDirectory()) {
            filename = filename+".txt";
            f = new File(filename);
        }
        
        // The error can be fixed
        if(f.exists() && !f.isDirectory()) {
            System.out.println("Fix filename: " + filename);
            BuildAuto buildAuto = new BuildAuto();
            buildAuto.buildAuto(filename);
        }
        else {
            System.out.println("Fix fail.");
            System.exit(1);
        }
    }
    // Fix negative base price by setting it to positive
    public void fix4(int errno, Automobile automobile) {
        double price = automobile.getBaseprice();
        automobile.setBaseprice(-1*price);
        System.out.println("Fixed errno " + errno);
    }
}
