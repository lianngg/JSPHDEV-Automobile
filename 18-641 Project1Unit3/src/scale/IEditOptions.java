package scale;
/**
 * 
 * @author hsuantzl
 * Provide an interface for editOptions
 * The implementations are in ProxyAutomobile abstract class
 */
public interface IEditOptions {
    public void editOptionSetName(String model, String name, String newName);
    public void editOptionName(String model, String optionSetName, String optionName, String newOptionName);
    public void editOptionValue(String model, String optionSetName, String optionName, double newValue);
}
