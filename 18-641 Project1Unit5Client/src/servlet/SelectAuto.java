package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.IServerSocketIO;
import client.SocketIO;

/**
 *
 * @author hsuantzl
 * The servlet is the index of the web service
 * A menu of all of the available automobiles in the server is provided
 * Users can select an automobile and click submit to configure it
 */

@WebServlet(name="GetAutomobile", urlPatterns={"/view"})
public class SelectAuto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        out.print("<html>"
                + "<head><title>Automobile Selection Menu</title></head>"
                + "<body>Please select an Automobile<br>" 
                + "<form method=\"get\" action=\"config\">"
                + "<select name=\"automobile\">");


        IServerSocketIO serverConnection = new SocketIO("127.0.0.1", 4444);
        List<String> list = serverConnection.getAutomobileList();
        for(int i = 0; i < list.size(); i++) {
            out.println("<option value=\""+list.get(i)+"\">"+list.get(i)+"</option>");
        }
        
        out.print("</select>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body></html>");
        out.close();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
