package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.NumberFormat;

public class Item {

    private final SimpleStringProperty value;
    private final SimpleStringProperty S_number;
    private final SimpleStringProperty name;

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

    public static ObservableList<Item> SearchCriteria(String search, ObservableList<Item> items) {
        ObservableList<Item> search_matches = FXCollections.observableArrayList();

        for(Item item: items){
            char[] charsName = item.getName().toLowerCase().toCharArray();
            char[] charsSN = item.getS_number().toLowerCase().toCharArray();
            for(Character character: charsName){
                if(search.contains(String.valueOf(character)) && (!search_matches.contains(item))){
                    Item.addItem(search_matches,item);
                }
            }
            for(Character character: charsSN){
                if(search.contains(String.valueOf(character)) && (!search_matches.contains(item))){
                    Item.addItem(search_matches,item);
                }
            }
        }
        return search_matches;
    }

    public static boolean changeName(Item item_selected, String new_name) {
        boolean valid_change = Item.ValidName(new_name);
        if(valid_change){
            // Set the name of the item to the new name
            item_selected.setName(new_name);
        }
        return valid_change;
    }

    public static boolean changeValue(Item item_selected, String raw_number) {
        // determine if the number is a valid double
        boolean valid_change = Item.ValidPrice(raw_number);
        if(valid_change){
            // set the value of the selected item to the Double value of the edited Cell,
            // formatting it with the US currency
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            item_selected.setValue(formatter.format(Double.parseDouble(raw_number)));
        }
        return valid_change;
    }

    public static boolean changeSN(ObservableList<Item> items, Item item_selected, String new_SN) {
        // determine whether the new SN is valid and if it does not match all other items
        boolean valid_change = Item.ValidSN(new_SN);
        int count = Item.NotMatchSN(items,new_SN);

        // set the Serial Number of the selected item to the new item, or refresh the (old) table
        if(count == items.size() && valid_change){
            item_selected.setS_number(new_SN);
            return true;
        }
        return false;
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
