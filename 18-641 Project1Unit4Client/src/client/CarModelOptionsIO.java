package client;

import java.util.Properties;
import util.FileIO;
/**
 * 
 * @author hsuantzl
 * Clients are able to read properties files and send to the server.
 *
 */
public class CarModelOptionsIO {
    public Properties readPropertiesFile(String filename) {
        FileIO io = new FileIO();
        Properties properties = io.readPropertiesFile(filename);
        return properties;    
    }
}
