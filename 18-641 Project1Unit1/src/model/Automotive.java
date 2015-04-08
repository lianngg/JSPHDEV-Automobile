package model;

import java.io.Serializable;

/**
 * @author hsuantzl
 * This class holds name of the model, base price and information about all the OptionSet
 */
public class Automotive implements Serializable {
    /**
     * The Serial Version ID is used when serializing and deSerializing an object.
     * Java recognizes if the bytes to deSerialize match the local class version.
     * If not, it will throw an exception.
     */
    private static final long serialVersionUID = 2181066470719363336L;
    private String name;
    private double baseprice;
    private OptionSet[] opset;
    private int opsetSize;  // opsetSize keeps track of the actual array size being used
    
    public Automotive(OptionSet[] opset) {
        this.opset = opset.clone();
        opsetSize = opset.length;
    }
    
    public Automotive(int size) {
        this.opset = new OptionSet[size];
        opsetSize = 0;
    }
    
    public Automotive(String name, double baseprice, int size) {
        this.opset = new OptionSet[size];
        this.baseprice = baseprice;
        this.name = name;
        opsetSize = 0;
    }
    
    public Automotive(String name, double baseprice, OptionSet[] opset) {
        this.opset = opset.clone();
        opsetSize = opset.length;
        this.baseprice = baseprice;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBaseprice() {
        return baseprice;
    }
    
    /* Get the whole OptionSet */
    public OptionSet[] getOpset() {
        return opset;
    }
    
    /* Get an OptionSet with index value */
    public OptionSet getOpset(int index) {
        if(index < opset.length)
            return opset[index];
        return null;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }

    /* Set the name and initial size of an OptionSet */
    public void setOptionSet(String name, int size) {
        opset[opsetSize] = new OptionSet(name, size);
        opsetSize++;
    }
    
    /* Add a new option in OptionSet by OptionSet name */
    public void setOption(String optionSet, String name, double price) {
        OptionSet optset = findOptionSet(optionSet);
        if(optset != null)
            optset.addOpt(name, price);
    }
    
    /* Find OptinoSet with name  */
    public OptionSet findOptionSet(String name) {
        for(int i = 0; i < opsetSize; i++) {
            if(opset[i].getName().equals(name))
                return opset[i];
        }
        return null;
    }
    
    /* Find Option with name in context of OptionSet */
    public OptionSet findOption(String name) {
        for(int i = 0; i < opsetSize; i++) {
            if(opset[i].findOption(name) != null)
                return opset[i];
        }
        return null;
    }
    
    /* Delete an OptionSet with OptionSet name */
    public void deleteOptionSet(String optionSetName) {
        OptionSet toBeDelete = findOptionSet(optionSetName);
        if(toBeDelete != null) {
            OptionSet[] newOptionSet = new OptionSet[opset.length];
            int index = 0;
            for(int i = 0; i < opsetSize; i++) {
                if(opset[i].getName().equals(optionSetName))
                    continue;
                newOptionSet[index] = opset[i];
                index++;
            }
            opsetSize--;
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
        builder.append("Automotive name = ");
        builder.append(name);
        builder.append("\nbaseprice = $");
        builder.append(baseprice);
        builder.append("\nopset =");
        for(int i = 0; i < opsetSize; i++) {
            builder.append("\t");
            builder.append(opset[i].toString());
            builder.append("\n");
        }
        return builder.toString();
    }
    
    public void print() {
        System.out.println(this.toString());
    }
}
