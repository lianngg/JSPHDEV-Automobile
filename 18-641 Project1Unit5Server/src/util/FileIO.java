package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import exception.AutoException;
import exception.ErrorCodes;
import model.Automobile;

/**
 * 
 * @author hsuantzl
 * Read text file and return an Automobile object.
 * The first line of input file is "Make"
 * The second line of input file is "Model"
 * The third line is "Base Price"
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
    /* Read a file and build an instance of Automobile class */
    public Automobile buildAutoObject(String filename) {
        Automobile automotive = new Automobile();
        
        // Use BufferedReader to read the text file.
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if(parts[0].equals("Make")) {
                    automotive.setMake(parts[1]);
                }
                else if(parts[0].equals("Model")) {
                    automotive.setModel(parts[1]);
                }
                else if(parts[0].equals("Base Price")) {
                    automotive.setBaseprice(Double.parseDouble(parts[1]));
                }
                else {  // Option sets
                    // Raw OptionSet data is many "property:price" pairs
                    if(parts.length < 2)
                        throw new AutoException(ErrorCodes.MISSING_OPTION);
                    String[] rawOptionData = parts[1].split(",");
                    String optionName = parts[0];
                    if(rawOptionData.length == 0)
                        throw new AutoException(ErrorCodes.MISSING_OPTION);
                    automotive.setOptionSet(optionName, rawOptionData.length);
                    for(int i = 0; i < rawOptionData.length; i++) {
                        String[] optionData = rawOptionData[i].split(":");
                        String name = optionData[0];
                        double price = Double.parseDouble(optionData[1]);
                        automotive.setOption(optionName, name, price);
                    }
                }
            }
            
        } catch (FileNotFoundException e) {
            try {
                throw new AutoException(ErrorCodes.FILENAME_ERROR);
            } catch (AutoException e1) {
                e1.print();
                log(e1.toString());
                e1.setErrorInfo(filename);
                e1.fix(e1, automotive);
                return null;
            }
        } catch (IOException e) {
            log(e.toString());
            e.printStackTrace();
            System.exit(1);
        } catch (AutoException e) {
            e.print();
            log(e.toString());
            e.fix(e, automotive);
        } finally { // After completely reading the file, close BufferedReader.
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return automotive;
    }
    
    /* Store the object into a file and read it back */
    public Automobile serializeAuto(Automobile automotive) {
        Automobile newAutomotive = null;
        try {
            // Write the object into a file.
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("automotive.dat"));
            out.writeObject(automotive);
            out.close();
            
            // Read the file and restore into an object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("automotive.dat"));
            newAutomotive = (Automobile) in.readObject();
            in.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return newAutomotive;
    }
    
    /* Write logs to file which contains current time stamps and error messages */
    public void log(String message) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String timeStamp = dateFormat.format(date);
            bw.write("[" + timeStamp + "] " + message + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Properties readPropertiesFile(String filename) {
        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(filename);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }    
        return properties;
        
    }
    public Automobile buildAutoFromProperties(Properties properties) {
        Automobile automobile = new Automobile();
        
            String carMake = properties.getProperty("CarMake"); // Return literal "null" if not found.
            if(!carMake.equals(null)) {
                automobile.setMake(carMake);
                automobile.setModel(properties.getProperty("CarModel"));
                automobile.setBaseprice(Double.parseDouble(properties.getProperty("BasePrice")));
    
                for(int i = 1; i <= 5; i++) {
                    automobile.setOptionSet(properties.getProperty("Option"+i), 2);
                    String name = properties.getProperty("OptionValue"+i+"a");
                    double price = Double.parseDouble(properties.getProperty("OptionPrice"+i+"a"));
                    automobile.setOption(properties.getProperty("Option"+i), name, price);
                    name = properties.getProperty("OptionValue"+i+"b");
                    price = Double.parseDouble(properties.getProperty("OptionPrice"+i+"b"));
                    automobile.setOption(properties.getProperty("Option"+i), name, price);
                }
            }

        

        return automobile;
    }
}
