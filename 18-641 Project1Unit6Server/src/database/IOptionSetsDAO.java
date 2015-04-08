package database;

import java.util.List;

/**
 * 
 * @author hsuantzl
 * This interface provides available functions for accessing the database
 */
public interface IOptionSetsDAO {
    public void save(String modelName, String optionSetName);
    public void update(String modelName, String optionSetName, String newName);
    public void delete(String modelName, String optionSetName);
    public List<String> findByModel(Integer autoID);
}
