package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.IServerSocketIO;
import client.SocketIO;
import model.Automobile;
import model.OptionSet;


/**
 * 
 * @author hsuantzl
 * This servlet configure a certain automobile selected by user previously
 * The doGet method show users a automobile configuration page
 * which let them choose their desired options
 * Once configuration form is submitted, the data will be passed to the doPost method
 * which setup the attributes required by JSP to show the result.  
 *
 */
@WebServlet(name="ConfigureAuto", urlPatterns={"/config"})
public class ConfigureAuto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
           
        // Get the automobile model selected by user in the servlet GetAutomobile
        String modelName = request.getParameter("automobile");
        if(modelName == null) {
            out.print("Nothing to be configured.");
            return;
        }
        // Ask the server for the Automobile object
        IServerSocketIO serverConnection = new SocketIO("127.0.0.1", 4444); 
        Automobile automobile = serverConnection.getAutomobileObject(modelName);
        
        // Print out a table UI for users to configure the automobile 
        out.print("<html>"
                + "<head><title>Automobile Configuration</title></head><body>"
                + "<form method=\"post\" action=\"config\">"
                + "<table border=\"1\" style=\"width:50%\">"
                + "<tr><td>Make/Model</td><td>"
                + automobile.getMake() + " " + automobile.getModel()
                + "</td></tr>");

        List<OptionSet> optionSets = automobile.getOpset();
        for(int i = 0; i < optionSets.size(); i++) {
            ArrayList<String> optionList = optionSets.get(i).getOptionNameAsListOfString();
            ArrayList<Double> optionPriceList = optionSets.get(i).getOptionPriceAsListOfDouble();
            out.println("<tr>"
                    + "<td>" + optionSets.get(i).getName() + "</td>"
                    + "<td><select name=\""+optionSets.get(i).getName()+"\">");
            for(int j = 0; j < optionList.size(); j++) {
                out.println("<option value=\""+optionList.get(j)+"\">"
                        +optionList.get(j)+" ($"+optionPriceList.get(j)+")"
                        +"</option>");
            }
            out.println("</select></td>");
        }
        
        out.print("</table>"
                + "<input type=\"hidden\" name=\"automobile\" value=\""+ automobile.getModel() +"\">"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body></html>");    
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the automobile model selected by user
        String modelName = request.getParameter("automobile");
        
        // Ask the server for the Automobile object
        IServerSocketIO serverConnection = new SocketIO("127.0.0.1", 4444); 
        Automobile automobile = serverConnection.getAutomobileObject(modelName);
        // Configure the automobile object according user's option choice
        List<OptionSet> optionSets = automobile.getOpset();
        for(int i = 0; i < optionSets.size(); i++) {
            OptionSet optionSet = optionSets.get(i);
            String optionName = request.getParameter(optionSet.getName());
            automobile.setOptionChoice(optionSet, optionName);
        }
        // Pass the automobile object to JSP and let it handle the request.
        request.setAttribute("automobile", automobile);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}
