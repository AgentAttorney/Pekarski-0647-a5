package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Item {

    private SimpleStringProperty value;
    private SimpleStringProperty S_number;
    private SimpleStringProperty name;

    public Item(String value, String s_number, String name) {
        this.value = new SimpleStringProperty(value);
        this.S_number = new SimpleStringProperty(s_number);
        this.name = new SimpleStringProperty(name);
    }

    public static boolean ValidName(String cur_name){
        return cur_name.length() >= 2 && cur_name.length() <= 256;
    }

    public static boolean ValidSN(String cur_SN) {
        // checks first if the length of the SN is 10 characters
        // it then converts the input into a char array
        // loop through each char in the array and check if Letter or Digit
        // if a char is, add it to the count, and have our boolean return the
        // truth value of count == 10 (what the length of the string is)
        int count = 0;
        if(cur_SN.length() == 10){
            char[] cur_SN_chars = cur_SN.toCharArray();
            for(char c: cur_SN_chars){
                if(Character.isLetterOrDigit(c))
                    count += 1;
            }
            return count == 10;
        }
        return false;
    }

    public static boolean ValidPrice(String cur_price) {
        // checks if we get a NumberFormatException for parsing our Double
        // then checks if the value of the parsed String is positive
        try{
            return Double.parseDouble(cur_price) >= 0;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public static int NotMatchSN(ObservableList<Item> items, String SN) {
        int count = 0;
        for(Item item: items){
            if(!(item.getS_number().equals(SN))){
                count += 1;
            }
        }
        return count;
    }

    public static ObservableList<Item> addItem(ObservableList<Item> ret_value,Item item) {
        ret_value.add(item);
        return ret_value;
    }

    public static ObservableList<Item> removeItems(ObservableList<Item> to_remove, ObservableList<Item> all) {
        if(to_remove != null){
            all.removeAll(to_remove);
        }
        return all;
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getS_number() {
        return S_number.get();
    }

    public SimpleStringProperty s_numberProperty() {
        return S_number;
    }

    public void setS_number(String s_number) {
        this.S_number.set(s_number);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
