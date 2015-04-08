package adapter;

import java.util.LinkedHashMap;

import exception.AutoException;
import exception.ErrorCodes;
import scale.EditOptions;
import util.FileIO;
import model.Automobile;
import model.OptionSet;
/**
 * @author hsuantzl
 * Build an Automobile object with given filename.
 * Automobile objects are stored in LinkedHashMap 
 * where key is model name and value is the object.
 */
public abstract class ProxyAutomobile {
    private static LinkedHashMap<String, Automobile> automobiles = new LinkedHashMap<>();
    
    /* Build an Automobile object with given filename */
    public void buildAuto(String fileName) {
        FileIO io = new FileIO();
        Automobile automobile = io.buildAutoObject(fileName);
        if(automobile == null)
            return;
        automobiles.put(automobile.getModel(), automobile);
        try {
            if(Double.compare(automobile.getBaseprice(), 0.0d) == 0)
                throw new AutoException(ErrorCodes.MISSING_PRICE);
            if(automobile.getBaseprice() < 0)
                throw new AutoException(ErrorCodes.NEGATIVE_BASEPRICE);
            if(automobile.getOpset().size() == 0)
                throw new AutoException(ErrorCodes.MISSING_OPTIONSET);
        } catch (AutoException e) {
            e.print();
            io.log(e.toString());
            e.fix(e, automobile);
        }
    }
    
    /* Print the information of the Automobile given its model name*/
    public void printAuto(String modelName) {
        if(automobiles.containsKey(modelName))
            automobiles.get(modelName).print();
    }
    
    /* Update the name of option set given the model name of the automobile */
    public void updateOptionSetName(String modelName, String optionSetName, String newName) {
        if(automobiles.containsKey(modelName)) {
            Automobile automobile = automobiles.get(modelName);
            OptionSet optionSet = automobile.findOptionSet(optionSetName);
            if(optionSet != null)
                automobile.updateOptionSet(optionSetName, newName);
        }
    }

    /* Update the name of certain option given the model name of the automobile */
    public void updateOptionName(String modelName, String optionSetName, String optionName, String newName) {
        if(automobiles.containsKey(modelName)) {
            Automobile automobile = automobiles.get(modelName);
            OptionSet optionSet = automobile.findOption(optionName);
            if(optionSet != null)
                automobile.updateOptionName(optionSetName, optionName, newName);
        }
    }
    
    /* Update the price of certain option given the model name of the automobile */
    public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice) {
        if(automobiles.containsKey(modelName)) {
            Automobile automobile = automobiles.get(modelName);
            OptionSet optionSet = automobile.findOption(optionName);
            if(optionSet != null)
                automobile.updateOptionValue(optionSetName, optionName, optionName, newPrice);
        }    
    }
    
    /* Fix the exception caused by the last added automobile */
    public void fix(AutoException e) {
        Automobile last = null;
        for(Automobile automobile: automobiles.values())
            last = automobile;
        e.fix(e, last);
    }
    
    /* Get the direct access to the Automobile for internal use */
    public Automobile getInstace(String modelName) {
        return automobiles.get(modelName);
    }
    
    /* Edit the optionSet name */
    public void editOptionSetName(String model, String name, String newName) {
        EditOptions editOptions = new EditOptions(model, name, newName);
        Thread thread = new Thread(editOptions);
        thread.start();
    }
    
    /* Edit the option name in an optionSet */
    public void editOptionName(String model, String optionSetName, String optionName, String newOptionName) {
        EditOptions editOptions = new EditOptions(model, optionSetName, optionName, newOptionName);
        Thread thread = new Thread(editOptions);
        thread.start();
    }
    
    /* Edit the value of a option in an optionSet  */
    public void editOptionValue(String model, String optionSetName, String optionName, double newValue) {
        EditOptions editOptions = new EditOptions(model, optionSetName, optionName, newValue);
        Thread thread = new Thread(editOptions);
        thread.start();
    }

}
