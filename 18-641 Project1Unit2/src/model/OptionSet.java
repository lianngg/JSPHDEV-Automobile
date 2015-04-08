package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author hsuantzl
 * This class holds the option set of the automotive, which is an array of option
 *
 */
public class OptionSet implements Serializable {
    /**
     * The Serial Version ID is used when serializing and deSerializing an object.
     * Java recognizes if the bytes to deSerialize match the local class version.
     * If not, it will throw an exception.
     */
    private static final long serialVersionUID = 4950072331096985781L;
    private ArrayList<Option> opt;
    private String name;
    private Option choise;  // tracking user choices

    protected OptionSet(ArrayList<Option> opt) {
        this.opt = new ArrayList<Option>(opt);
    }
    
    protected OptionSet(String name, int size) {
        opt = new ArrayList<Option>(size);
        this.name = name;
    }
    
    protected OptionSet(String name, ArrayList<Option> opt) {
        this.opt = new ArrayList<Option>(opt);
        this.name = name;
    }
    
    /* Get the whole options */
    protected ArrayList<Option> getOpt() {
        return opt;
    }
    
    /* Get option in OptionSet with its index */
    protected Option getOpt(int index) {
        if(index < opt.size())
            return opt.get(index);
        return null;
    }

    protected String getName() {
        return name;
    }
    
    /* Get the chosen option. Return null if no option was chosen */
    protected Option getOptionChoice() {
        return choise;
    }
    
    protected void setOpt(ArrayList<Option> opt) {
        this.opt = new ArrayList<Option>(opt);
    }

    protected void setName(String name) {
        this.name = name;
    }
    
    /* Save that choice inside the option set */
    protected void setOptionChoice(String optionName) {
        choise = findOption(optionName);
    }
    
    /* Find Option with name  */
    protected Option findOption(String name) {
        for(int i = 0; i < opt.size(); i++) {
            if(opt.get(i).getName().equals(name))
                return opt.get(i);
        }
        return null;
    }
    
    /* Add a new option */
    protected void addOpt(String name, double price) {
        opt.add(new Option(name, price));
    }
    
    /* Delete an Option with Option name */
    protected void deleteOption(String optionName) {
        Option toBeDelete = findOption(optionName);
        if(toBeDelete != null) {
            opt.remove(toBeDelete);
        }
        return;
    }
    
    /* Find the corresponding option and set its data */
    protected void updateOpt(String option, String name, double price) {
        Option opt = findOption(option);
        if(opt != null) {
            opt.setName(name);
            opt.setPrice(price);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(" ");
        builder.append(opt.toString());
        return builder.toString();
    }
    
    protected void print() {
        System.out.println(this.toString());
    }
    
    /* Inner class Option */
    protected class Option implements Serializable {
        private static final long serialVersionUID = 814096102005213381L;
        private String name;
        private double price;
        
        protected Option(String name, double price) {
            this.name = name;
            this.price = price;
        }
        
        protected String getName() {
            return name;
        }
        protected double getPrice() {
            return price;
        }
        protected void setName(String name) {
            this.name = name;
        }
        protected void setPrice(double price) {
            this.price = price;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            builder.append(": $");
            builder.append(price);
            return builder.toString();
        }
        protected void print() {
            System.out.println(this.toString());
        }  
    }
}
