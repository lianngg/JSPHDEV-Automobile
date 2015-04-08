package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.Automobile;

/**
 * 
 * @author hsuantzl
 * This class help client interact with the server
 * Client is able to list all of the available automobiles in the server
 * and obtain a certain automobile object from server given its model name
 * The methods are primarily used by the servlets
 */
public class SocketIO implements IServerSocketIO {
    private String host;
    private int port;
    
    public SocketIO(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public Automobile getAutomobileObject(String modelName) {
        Socket serverSocket = null;
        PrintWriter serverPrintWriter = null;
        Automobile automobile = null;
        try {
            // Connect to the server
            serverSocket = new Socket(host, port);
            serverPrintWriter = new PrintWriter(serverSocket.getOutputStream(), true);
            serverPrintWriter.println("config");
            serverPrintWriter.println(modelName);
            ObjectInputStream objectIS = new ObjectInputStream(serverSocket.getInputStream());
            automobile = (Automobile) objectIS.readObject();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return automobile;
    }
    
    public List<String> getAutomobileList() {
        Socket serverSocket = null;
        PrintWriter serverPrintWriter = null;
        BufferedReader in = null;
        List<String> list = new ArrayList<>();
        try {
            serverSocket = new Socket(host, port);
            serverPrintWriter = new PrintWriter(serverSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            serverPrintWriter.println("list");
            String line;
            while((line = in.readLine()) != null) {
                if(line.length() <= 0)  // Check if there is nothing to read
                    break;
                list.add(line);
            }
            in.close();
            serverPrintWriter.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Errors occur when getAutomobileList()");
            return list;
        }
        return list;
    }

}
