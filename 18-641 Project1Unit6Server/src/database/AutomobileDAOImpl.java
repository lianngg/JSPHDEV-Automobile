package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import util.DBUtils;
import model.Automobile;
import model.OptionSet;

/**
 * 
 * @author hsuantzl
 * This class provides implementation of available functions
 * for accessing Automobiles table in the database
 * 
 */

public class AutomobileDAOImpl implements IAutomobileDAO {
    private DBUtils db = new DBUtils();
    
    public void save(Automobile automobile) {
        if(findAutoID(automobile.getModel()) != null)
            return;
        // Save data into Automobiles table
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().insertAutomobile())) {
            stmt.setString(1, automobile.getModel());
            stmt.setString(2, automobile.getMake());
            stmt.setDouble(3, automobile.getBaseprice());
            System.out.println("Executing: " + stmt.toString()
                    .substring(stmt.toString().indexOf(": ")+2));
            stmt.executeUpdate();
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Save an optionSet's name into OptionSets table
        IOptionSetsDAO optionSetsDAO = new OptionSetsDAOImpl();
        IOptionsDAO optionsDAO = new OptionsDAOImpl();
        for(OptionSet optionSet: automobile.getOpset()) {
            optionSetsDAO.save(automobile.getModel(), optionSet.getName());
            
            // Save an option and its price into AutoOptions table
            List<String> optionList = optionSet.getOptionNameAsListOfString();
            List<Double> optionValueList = optionSet.getOptionPriceAsListOfDouble();
            for(int i = 0; i < optionList.size(); i++) {
                String optionName = optionList.get(i);
                double value = optionValueList.get(i);
                optionsDAO.save(automobile.getModel(), optionSet.getName(), optionName, value);
            }
        }
    }
    
    public void update(String modelName, Automobile automobile) {
        delete(modelName);
        save(automobile);
    }
    
    public void delete(String modelName) {
        Integer autoID = findAutoID(modelName);
        if(autoID == null)
            return;
        // Delete data from Automobiles table
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().deleteAutomobile())) {
            stmt.setInt(1, autoID);
            System.out.println("Executing: " + stmt.toString()
                    .substring(stmt.toString().indexOf(": ")+2));
            stmt.executeUpdate();
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
    
    /* Find A_ID by modelName for internal use */
    private Integer findAutoID(String modelName) {
        Integer result = null;
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().getAutomobileID())) {
            stmt.setString(1, modelName);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                result = rs.getInt(1);
            }
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return result;
    }
    public Automobile findByModel(String modelName) {
        Automobile automobile = null;
        if(modelName == null || "".equals(modelName))
            return null;
        Integer autoID = findAutoID(modelName);
        if(autoID == null)
            return null;

        automobile = new Automobile();
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().queryAutomobile())) {
            stmt.setInt(1, autoID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                automobile.setModel(rs.getString(1));
                automobile.setMake(rs.getString(2));
                automobile.setBaseprice(rs.getDouble(3));
            }
            
            IOptionSetsDAO optionSetsDAO = new OptionSetsDAOImpl();
            List<String> optionSetList = optionSetsDAO.findByModel(autoID);
            for(String optionSetName: optionSetList) {
                automobile.setOptionSet(optionSetName, 10); // The size of ArrayList is not fix.
                IOptionsDAO optionDAO = new OptionsDAOImpl();
                List<String> optionList = optionDAO.findOptionList(autoID, optionSetName);
                for(String option: optionList) {
                    double price = optionDAO.findOptionPrice(autoID, optionSetName, option);
                    automobile.setOption(optionSetName, option, price);
                }
            }          
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return automobile;
    }
}
