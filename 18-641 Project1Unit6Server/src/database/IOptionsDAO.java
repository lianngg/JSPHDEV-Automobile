package database;

import java.util.List;

public interface IOptionsDAO {
    public void save(String modelName, String optionSetName, String optionName, double value);
    public void update(String modelName, String optionSetName,
            String optionName, String newOptionName, double value);
    public void delete(String modelName, String optionSetName, String optionName);
    public List<String> findOptionList(Integer autoID, String optionSetName);
    public double findOptionPrice(Integer autoID, String optionSetName, String optionName);
}
