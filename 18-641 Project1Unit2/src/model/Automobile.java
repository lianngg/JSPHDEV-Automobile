package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author hsuantzl
 * This class holds name of the model, base price and information about all the OptionSet
 */
public class Automobile implements Serializable {
    /**
     * The Serial Version ID is used when serializing and deSerializing an object.
     * Java recognizes if the bytes to deSerialize match the local class version.
     * If not, it will throw an exception.
     */
    private static final long serialVersionUID = 2181066470719363336L;
    private String make;
    private String model;
    private double baseprice;
    private ArrayList<OptionSet> opset;
    
    public Automobile(ArrayList<OptionSet> opset) {
        this.opset = new ArrayList<>(opset);
    }
    
    public Automobile() {
        this.opset = new ArrayList<OptionSet>();
    }
    
    public Automobile(String name, double baseprice) {
        this.opset = new ArrayList<OptionSet>();
        this.baseprice = baseprice;
        this.model = name;
    }
    
    public Automobile(String name, double baseprice, ArrayList<OptionSet> opset) {
        this.opset = new ArrayList<OptionSet>(opset);
        this.baseprice = baseprice;
        this.model = name;
    }
    
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getBaseprice() {
        return baseprice;
    }
    
    /* Get the whole OptionSet */
    public ArrayList<OptionSet> getOpset() {
        return opset;
    }
    
    /* Get an OptionSet with index value */
    public OptionSet getOpset(int index) {
        if(index < opset.size())
            return opset.get(index);
        return null;
    }
    public String getOptionChoice(String setName) {
        if(findOptionSet(setName).getOptionChoice() == null)
            return "None";
        return findOptionSet(setName).getOptionChoice().getName();
    }
    public double getOptionChoicePrice(String setName) {
        if(findOptionSet(setName).getOptionChoice() == null)
            return 0d;
        return findOptionSet(setName).getOptionChoice().getPrice(); 
    }
    /* Calculate the total price */
    public double getTotalPrice() {
        double price = baseprice;
        for(OptionSet optionSet: opset) {
            if(optionSet.getOptionChoice() != null)
                price += optionSet.getOptionChoice().getPrice();
        }
        return price;
    }
    
    public void setMake(String name) {
        this.make = name;
    }
    
    public void setModel(String name) {
        this.model = name;
    }

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }

    /* Set the name and initial size of an OptionSet */
    public void setOptionSet(String name, int size) {
        opset.add(new OptionSet(name, size));
    }
    
    /* Add a new option in OptionSet by OptionSet name */
    public void setOption(String optionSet, String name, double price) {
        OptionSet optset = findOptionSet(optionSet);
        if(optset != null)
            optset.addOpt(name, price);
    }
    
    /* Choose a particular option in an option set */
    public void setOptionChoice(String setName, String optionName) {
        findOptionSet(setName).setOptionChoice(optionName);
    }
    
    /* Find OptinoSet with name  */
    public OptionSet findOptionSet(String name) {
        for(int i = 0; i < opset.size(); i++) {
            if(opset.get(i).getName().equals(name))
                return opset.get(i);
        }
        return null;
    }
    
    /* Find Option with name in context of OptionSet */
    public OptionSet findOption(String name) {
        for(int i = 0; i < opset.size(); i++) {
            if(opset.get(i).findOption(name) != null)
                return opset.get(i);
        }
        return null;
    }
    
    /* Delete an OptionSet with OptionSet name */
    public void deleteOptionSet(String optionSetName) {
        OptionSet toBeDelete = findOptionSet(optionSetName);
        if(toBeDelete != null) {
            opset.remove(toBeDelete);
        }
        return;
    }
    
    /* Delete an Option with Option name */
    public void deleteOption(String optionName) {
        OptionSet toBeDelete = findOption(optionName);
        if(toBeDelete != null) {
            toBeDelete.deleteOption(optionName);
        }
        return;
    }
    
    /* Find the corresponding OptionSet and set its name */
    public void updateOptionSet(String optionSetName, String name) {
        OptionSet optionSet = findOptionSet(optionSetName);
        if(optionSet != null) {
            optionSet.setName(name);
        }
    }
   
    /* Find the corresponding option and set its data */
    public void updateOption(String optionSetName, String optionName, String newOptionName, double price) {
        OptionSet optionSet = findOptionSet(optionSetName);
        if(optionSet != null) {
            optionSet.updateOpt(optionName, newOptionName, price);
        }
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Automobile = ");
        builder.append(make);
        builder.append(" ");
        builder.append(model);
        builder.append("\nbaseprice = $");
        builder.append(baseprice);
        builder.append("\nopset =");
        for(int i = 0; i < opset.size(); i++) {
            builder.append("\t");
            builder.append(opset.get(i).toString());
            builder.append("\n");
        }
        builder.append("Current Total Price = $");
        builder.append(getTotalPrice());
        return builder.toString();
    }
    
    public void print() {
        System.out.println(this.toString());
    }
}
