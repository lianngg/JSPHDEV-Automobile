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
 * for accessing OptionSets table in the database
 */
public class OptionSetsDAOImpl implements IOptionSetsDAO {
    private DBUtils db = new DBUtils();
    
    public void save(String modelName, String optionSetName) {
        // Save data into OptionSets table
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().insertOptionSets())) {
            stmt.setString(1, optionSetName);
            stmt.setString(2, modelName);
            stmt.executeUpdate();
//            int rowCount = stmt.executeUpdate();
//            System.out.println("Statement executed, and " + rowCount + " rows inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public void update(String modelName, String optionSetName, String newName) {
        // The update function is handled by AutomobileDAOImple
        // which updates the whole object into database 
    }
    
    public void delete(String modelName, String optionSetName) {
        // The optionSets will automatically be deleted together with Automobile
        // because of foreign key ON DELETE CASCADE
    }
    
    public List<String> findByModel(Integer autoID) {
        List<String> list = new ArrayList<>();
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(new SQL().queryOptionSets())) {
            stmt.setInt(1, autoID);
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
}
