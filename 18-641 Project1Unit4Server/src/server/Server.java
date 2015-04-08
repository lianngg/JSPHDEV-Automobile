package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;


/**
 * 
 * @author hsuantzl
 * This is the Server class that provide services for users
 * Include: add automobile from the properties provided by users
 *          list all of the available automobiles
 *          send an automobile to user for them to configure the car locally
 */
public class Server implements Runnable {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    
    public Server(Socket socket) {
        this.socket = socket;
    }
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                String message = in.readLine();
                if(message == null)
                    continue;
                
                out = new PrintWriter(socket.getOutputStream(), true);
                switch(message) {
                    case "quit": 
                        out.println("Bye!");
                        System.out.println("Quit");
                        socket.close();
                        return;
                    case "send":
                        ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
                        Properties properties = (Properties)objectIS.readObject();
                        BuildCarModelOptions bulidCarModelOptions = new BuildCarModelOptions();
                        Automobile auto = bulidCarModelOptions.buildAutoOptions(properties);
                        System.out.println("Receive Automobile " + auto.getModel());
                        out.println("Automobile created successfully.");
                        break;
                    case "list":         
                        AutoServer autoServer = new BuildAuto();
                        out.println(autoServer.listAutomobiles());
                        out.println("");
                        System.out.println("Return list of Automobiles:\n" + autoServer.listAutomobiles());
                        break;
                    case "config":
                        String modelName = in.readLine();
                        BuildAuto buildAuto = new BuildAuto();
                        Automobile automobile = buildAuto.getInstace(modelName);
                        ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
                        objectOS.writeObject(automobile);
                        System.out.println("Return Automobile " + automobile.getModel());
                        break;
                }
            }
            
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + socket.getPort());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        

     }

    public static void main(String[] args) {
        System.out.println("Start server.");
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            while(true) {
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                Thread thread = new Thread(server);
                thread.start();
                System.out.println("Accept a client.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
