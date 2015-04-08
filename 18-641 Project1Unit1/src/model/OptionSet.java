package model;

import java.io.Serializable;
import java.util.Arrays;

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
    private Option opt[];
    private String name;
    private int optSize;    // optSize keeps track of array size being used
    
    protected OptionSet(Option opt[]) {
        this.opt = opt.clone();
        optSize = opt.length;
    }
    
    protected OptionSet(String name, int size) {
        opt = new Option[size];
        this.name = name;
        optSize = 0;
    }
    
    protected OptionSet(String name, Option opt[]) {
        this.opt = opt.clone();
        optSize = opt.length;
        this.name = name;
    }
    
    /* Get the whole options */
    protected Option[] getOpt() {
        return opt;
    }
    
    /* Get option in OptionSet with its index */
    protected Option getOpt(int index) {
        if(index < optSize)
            return opt[index];
        return null;
    }

    protected String getName() {
        return name;
    }
    
    protected void setOpt(Option opt[]) {
        this.opt = opt.clone();
    }

    protected void setName(String name) {
        this.name = name;
    }
    
    /* Find Option with name  */
    protected Option findOption(String name) {
        for(int i = 0; i < optSize; i++) {
            if(opt[i].getName().equals(name))
                return opt[i];
        }
        return null;
    }
    
    /* Add a new option */
    protected void addOpt(String name, double price) {
        opt[optSize] = new Option(name, price);
        optSize++;
    }
    
    /* Delete an Option with Option name */
    protected void deleteOption(String optionName) {
        Option toBeDelete = findOption(optionName);
        if(toBeDelete != null) {
            Option[] newOption = new Option[opt.length];
            int index = 0;
            for(int i = 0; i < optSize; i++) {
                if(opt[i].getName().equals(optionName))
                    continue;
                newOption[index] = opt[i];
                index++;
            }
            optSize--;
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
        builder.append(Arrays.toString(opt));
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
