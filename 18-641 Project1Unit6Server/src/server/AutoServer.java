package server;

import java.util.Properties;

import model.Automobile;

/**
 * 
 * @author hsuantzl
 * This is a interface for server to provide automobile services.
 *
 */

public interface AutoServer {
    public Automobile acceptClientPropertiesObject(Properties properties);
    public String listAutomobiles();
}
