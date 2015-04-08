package driver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.Automotive;
import util.FileIO;

/**
 * 
 * @author hsuantzl
 * Instantiate a Ford Wagon ZTW object and write it to a file.
 * 
 */
public class Driver {
    
    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("FocusWagonZTW_result.txt"));
            FileIO io = new FileIO();
            
            // Read data from text file and store into an object
            Automotive automotive = io.buildAutoObject("FocusWagonZTW.txt");
            System.out.println("Before serialization:\n");
            bw.write("Before serialization:\n");
            automotive.print();
            bw.write(automotive.toString());

            // Serialization and deSerialization
            Automotive newAutomotive = io.serializeAuto(automotive);
            System.out.println("After serialization:\n");
            bw.write("After serialization:\n");
            newAutomotive.print();
            bw.write(newAutomotive.toString());
            
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
