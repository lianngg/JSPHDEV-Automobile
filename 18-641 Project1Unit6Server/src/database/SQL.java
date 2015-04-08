package database;

import java.util.Properties;
import util.FileIO;

/**
 * 
 * @author hsuantzl
 * This class obtains SQL command by reading properties file
 * and provides SQL for other class in this package
 */
public class SQL {
    private FileIO io;
    private final String SQLFileName = "SQL.properties";
    
    public SQL() {
        io = new FileIO();
    }
    
    public String createDB() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("CREATE_DB");   
    }
    
    public String checkTableExistInDB() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("CHECK_TABLE_EXISTS_IN_DB");     
    }
    
    public String createAutomobilesTable() { 
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("CREATE_AUTOMOBILES");   
    }
    
    public String createOptionSetsTable() {  
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("CREATE_OPTIONSETS");
        
    }
    
    public String createOptionsTable() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("CREATE_OPTIONS"); 
    }
    
    public String insertAutomobile() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("INSERT_AUTOMOBILE"); 
    }
    
    public String insertOptionSets() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("INSERT_OPTIONSETS"); 
    }
    
    public String insertOptions() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("INSERT_OPTIONS"); 
    }
    public String getAutomobileID() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("QUERY_AUTOID");    
    }
    public String deleteAutomobile() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("DELETE_AUTOMOBILE"); 
    }
    public String queryAutomobile() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("QUERY_AUTOMOBILE"); 
    }
    public String queryOptionSets() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("QUERY_OPTIONSETS");   
    }
    public String queryOptions() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("QUERY_OPTIONS");   
    }
    public String queryOptionPrice() {
        Properties props = io.readPropertiesFile(SQLFileName);
        return props.getProperty("QUERY_OPTIONPRICE");   
    }
}
