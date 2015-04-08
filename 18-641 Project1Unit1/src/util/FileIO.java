package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Automotive;

/**
 * 
 * @author hsuantzl
 * Read text file and return an Automotive object. OptionSet's default size is 10.
 * The first line of input file is "Model name"
 * The second line is "Base Price"
 * The remaining lines are the option sets
 * Therefore, the format should be like:
   Model name=Focus Wagon ZTW
   Base Price=18445
   Color=Gold:0,Grey:0
   Transmission=automatic:0,manual:-815
   Brakes=Standard:0,ABS:400
 * 
 */
public class FileIO {
    /* Read a file and build an instance of Automotive class */
    public Automotive buildAutoObject(String filename) {
        Automotive automotive = new Automotive(10);
        
        // Use BufferedReader to read the text file.
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if(parts[0].equals("Model name")) {
                    automotive.setName(parts[1]);
                }
                else if(parts[0].equals("Base Price")) {
                    automotive.setBaseprice(Double.parseDouble(parts[1]));
                }
                else {
                    // rawOptionData is many property:price pairs
                    String[] rawOptionData = parts[1].split(",");
                    String optionName = parts[0];
                    automotive.setOptionSet(optionName, rawOptionData.length);
                    for(int i = 0; i < rawOptionData.length; i++) {
                        String[] optionData = rawOptionData[i].split(":");
                        String name = optionData[0];
                        double price = Double.parseDouble(optionData[1]);
                        automotive.setOption(optionName, name, price);
                    }
                }
            }
            br.close(); // After completely reading the file, close BufferedReader.
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        return automotive;
    }
    public Automotive serializeAuto(Automotive automotive) {
        Automotive newAutomotive = null;
        try {
            // Write the object into a file.
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("automotive.dat"));
            out.writeObject(automotive);
            out.close();
            
            // Read the file and restore into an object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("automotive.dat"));
            newAutomotive = (Automotive) in.readObject();
            in.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return newAutomotive;
    }
}
