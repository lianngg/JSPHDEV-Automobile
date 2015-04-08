package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils;
/**
 * 
 * @author hsuantzl
 * This class provides implementation of available functions
 * for accessing AutoOptions table in the database
 */
public class OptionsDAOImpl implements IOptionsDAO {
    private DBUtils db = new DBUtils();
    
    public void save(String modelName, String optionSetName, String optionName, double value) {
        // Save data into AutoOptions table
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().insertOptions())) {
            stmt.setString(1, optionName);
            stmt.setDouble(2, value);
            stmt.setString(3, modelName);
            stmt.setString(4, optionSetName);
            stmt.executeUpdate();
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(String modelName, String optionSetName,
            String optionName, String newOptionName, double value) {
        // The update function is handled by AutomobileDAOImple,
        // which updates the whole object into database 
    }
    
    public void delete(String modelName, String optionSetName, String optionName) {
        // The option will automatically be deleted together with Automobile
        // because of foreign key ON DELETE CASCADE
    }
    
    public List<String> findOptionList(Integer autoID, String optionSetName) {
        List<String> list = new ArrayList<>();
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().queryOptions())) {
            stmt.setInt(1, autoID);
            stmt.setString(2, optionSetName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }            
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
    }
    
    public double findOptionPrice(Integer autoID, String optionSetName, String optionName) {
        double price = 0d;
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().queryOptionPrice())) {
            stmt.setInt(1, autoID);
            stmt.setString(2, optionSetName);
            stmt.setString(3, optionName);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                price = rs.getDouble(2);
            }            
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return price;
        
    }
}
