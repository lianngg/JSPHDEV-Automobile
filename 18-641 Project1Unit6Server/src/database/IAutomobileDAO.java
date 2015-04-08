package database;

import model.Automobile;
/**
 * 
 * @author hsuantzl
 * This interface provides available functions for accessing the database
 */
public interface IAutomobileDAO {
    public void save(Automobile automobile);
    public void update(String modelName, Automobile automobile);
    public void delete(String modelName);
    public Automobile findByModel(String modelName);
}
