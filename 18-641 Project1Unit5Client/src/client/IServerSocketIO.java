package client;

import java.util.List;

import model.Automobile;

/**
 * 
 * @author hsuantzl
 * This interface enables client to obtain information and data from the server
 */
public interface IServerSocketIO {
    Automobile getAutomobileObject(String modelName);
    List<String> getAutomobileList();
}
