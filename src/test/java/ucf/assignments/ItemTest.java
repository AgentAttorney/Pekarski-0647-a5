package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void validName_Small() {
        String input = "Hello World";
        boolean vinput = Item.ValidName(input);
        assertTrue(vinput);
    }
    @Test
    void validNameFalse_Small() {
        String input = "E";
        boolean vinput = Item.ValidName(input);
        assertFalse(vinput);
    }
    @Test
    void validName_Large() {
        // The alphabet 10 times minus 4 characters. 256 chars in total
        String input = "abcdefghijklmnopqrstuvwxyzabcdefghijk" +
                "lmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefgh" +
                "ijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv";

        boolean vinput = Item.ValidName(input);
        assertTrue(vinput);
    }
    @Test
    void validName_False_Large() {
        // same as validNameLarge input except added 'w' char at end for 257 chars
        String input = "abcdefghijklmnopqrstuvwxyzabcdefghijk" +
                "lmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefgh" +
                "ijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvw";

        boolean vinput = Item.ValidName(input);
        assertFalse(vinput);
    }

    @Test
    void validSNSimple() {
        String input = "xxxxxxxxxx";
        assertTrue(Item.ValidSN(input));
    }
    @Test
    void validSN_numbers_and_chars() {
        String input = "x12xx4xx40";
        assertTrue(Item.ValidSN(input));
    }
    @Test
    void validSN_false_numbers_and_chars_and_special_chars() {
        String input = "x12xx4xx#0";
        assertFalse(Item.ValidSN(input));
    }
    @Test
    void validSN_Capital_Letters() {
        String input = "ABCDEFGHIJ";
        assertTrue(Item.ValidSN(input));
    }
    @Test
    void validSN_Capital_Letters_and_lowercase_and_numbers() {
        String input = "ABCcE3GHE1";
        assertTrue(Item.ValidSN(input));
    }
    @Test
    void validSN_false_length_9() {
        String input = "ABCDEFGHI";
        assertFalse(Item.ValidSN(input));
    }
    @Test
    void validSN_false_length_11() {
        String input = "ABCDEFGHIJK";
        assertFalse(Item.ValidSN(input));
    }

    @Test
    void validPrice_Basic() {
        String input = "9.45";
        assertTrue(Item.ValidPrice(input));
    }
    @Test
    void validPrice_Large() {
        String input = "1111111111111.11";
        assertTrue(Item.ValidPrice(input));
    }
    @Test
    void validPrice_Small() {
        String input = "0.001";
        assertTrue(Item.ValidPrice(input));
    }
    @Test
    void validPrice_zero() {
        String input = "0";
        assertTrue(Item.ValidPrice(input));
    }
    @Test
    void validPrice_Hexadecimal_lol() {
        String input = "9.3D";
        assertTrue(Item.ValidPrice(input));
    }
    @Test
    void validPrice_False_String() {
        String input = "Nine Forty-Five";
        assertFalse(Item.ValidPrice(input));
    }
    @Test
    void validPrice_False_String_Numbers() {
        String input = "9.D3";
        assertFalse(Item.ValidPrice(input));
    }

    @Test
    void addItem_empty_List() {
        String val = "9.32";
        String sNum = "xxxxxxxxxx";
        String name = "name";
        Item item1 = new Item(val,sNum,name);
        ObservableList<Item> items = FXCollections.observableArrayList();
        ObservableList<Item> items_plus_1 = Item.addItem(items,item1);
        assertEquals(1,items_plus_1.size());
        assertEquals(item1,items_plus_1.get(0));
    }
    @Test
    void addItem_filled_List() {
        String val = "9.32";
        String sNum = "xxxxxxxxxx";
        String name = "name";
        Item item1 = new Item(val,sNum,name);
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item1);
        ObservableList<Item> items_plus_1 = Item.addItem(items,item1);
        assertEquals(3,items_plus_1.size());
        assertEquals(item1,items_plus_1.get(2));
    }

    @Test
    void removeItems_basic() {
        Item item1 = new Item("1","xxxxxxxxxx","name");
        Item item2 = new Item("2","xxxxxxxxxx","name2");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2);
        ObservableList<Item> to_remove = FXCollections.observableArrayList(item2);
        ObservableList<Item> item_minus_1 = Item.removeItems(to_remove,items);
        assertEquals(1,item_minus_1.size());
        assertEquals(item1,item_minus_1.get(0));

    }
    @Test
    void removeItems_basic_large() {
        Item item1 = new Item("1","xxxxxxxxxx","name");
        Item item2 = new Item("2","xxxxxxxxxx","name2");
        Item item3 = new Item("1","xxxxxxxxxx","name3");
        Item item4 = new Item("2","xxxxxxxxxx","name4");
        Item item5 = new Item("1","xxxxxxxxxx","name5");
        Item item6 = new Item("2","xxxxxxxxxx","name6");

        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2,item3,item4,item5,item6);
        ObservableList<Item> to_remove = FXCollections.observableArrayList(item1,item6);
        ObservableList<Item> item_minus_1 = Item.removeItems(to_remove,items);

        assertEquals(4,item_minus_1.size());
        assertEquals(item2,item_minus_1.get(0));
        assertEquals(item3,item_minus_1.get(1));
        assertEquals(item4,item_minus_1.get(2));
        assertEquals(item5,item_minus_1.get(3));
    }
    @Test
    void removeItems_Item_not_in_list() {
        Item item1 = new Item("1","xxxxxxxxxx","name");
        Item item2 = new Item("2","xxxxxxxxxx","name2");
        Item item3 = new Item("3","xxxxxxxxxx","name3");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2);
        ObservableList<Item> to_remove = FXCollections.observableArrayList(item3);
        ObservableList<Item> item_minus_1 = Item.removeItems(to_remove,items);
        assertEquals(2,item_minus_1.size());
        assertEquals(item1,item_minus_1.get(0));
        assertEquals(item2,item_minus_1.get(1));

    }
    @Test
    void removeItems_No_Removal_Item() {
        Item item1 = new Item("1","xxxxxxxxxx","name");
        Item item2 = new Item("2","xxxxxxxxxx","name2");

        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2);
        ObservableList<Item> to_remove = FXCollections.observableArrayList();
        ObservableList<Item> item_minus_1 = Item.removeItems(to_remove,items);
        assertEquals(2,item_minus_1.size());
        assertEquals(item1,item_minus_1.get(0));
        assertEquals(item2,item_minus_1.get(1));

    }

    @Test
    void searchCriteria_name_match() {
        Item item1 = new Item("5","xxxxxxxxxw","NameA");
        Item item2 = new Item("5","xxxxxxxxxy","NameB");
        Item item3 = new Item("5","xxxxxxxxxz","NameC");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2,item3);
        String search_criteria = "Name";
        ObservableList<Item> match_list = Item.SearchCriteria(search_criteria,items);

        assertEquals(item1.getName(),match_list.get(0).getName());
        assertEquals(item2.getName(),match_list.get(1).getName());
        assertEquals(item3.getName(),match_list.get(2).getName());
    }
    @Test
    void searchCriteria_name_contains_char() {
        Item item1 = new Item("5","xxxxxxxxxw","NameA");
        Item item2 = new Item("5","xxxxxxxxxy","NameB");
        Item item3 = new Item("5","xxxxxxxxxz","NameC");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2,item3);
        String search_criteria = "n";
        ObservableList<Item> match_list = Item.SearchCriteria(search_criteria,items);

        assertEquals(item1.getName(),match_list.get(0).getName());
        assertEquals(item2.getName(),match_list.get(1).getName());
        assertEquals(item3.getName(),match_list.get(2).getName());
    }
    @Test
    void searchCriteria_name_and_SN_contains_at_least_one_Char_of_Either() {
        Item item1 = new Item("5","xxxxxxxxxw","NameD");
        Item item2 = new Item("5","xxxxxxxxxy","NameB");
        Item item3 = new Item("5","xxxxxxxxxz","NameC");

        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2,item3);
        String search_criteria = "d";
        String search_criteria2 = "b";
        String search_criteria3 = "c";
        String search_criteria4 = "w";
        String search_criteria5 = "y";
        String search_criteria6 = "z";

        ObservableList<Item> match_list = Item.SearchCriteria(search_criteria,items);
        ObservableList<Item> match_list2 = Item.SearchCriteria(search_criteria2,items);
        ObservableList<Item> match_list3 = Item.SearchCriteria(search_criteria3,items);
        ObservableList<Item> match_list4 = Item.SearchCriteria(search_criteria4,items);
        ObservableList<Item> match_list5 = Item.SearchCriteria(search_criteria5,items);
        ObservableList<Item> match_list6 = Item.SearchCriteria(search_criteria6,items);

        assertEquals(item1.getName(),match_list.get(0).getName());
        assertEquals(item2.getName(),match_list2.get(0).getName());
        assertEquals(item3.getName(),match_list3.get(0).getName());
        assertEquals(item1.getS_number(),match_list4.get(0).getS_number());
        assertEquals(item2.getS_number(),match_list5.get(0).getS_number());
        assertEquals(item3.getS_number(),match_list6.get(0).getS_number());
    }
    @Test
    void searchCriteria_no_match() {
        Item item1 = new Item("5","xxxxxxxxxw","NameA");
        Item item2 = new Item("5","xxxxxxxxxy","NameB");
        Item item3 = new Item("5","xxxxxxxxxz","NameC");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2,item3);
        String search_criteria = "q";
        ObservableList<Item> match_list = Item.SearchCriteria(search_criteria,items);

        assertEquals(0,match_list.size());
    }

    @Test
    void changeName_valid() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        String new_name = "Cookiezi";
        Item.changeName(item1,new_name);
        assertEquals(item1.getName(),new_name);
    }
    @Test
    void changeName_invalid_single_letter() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        String new_name = "E";
        Item.changeName(item1,new_name);
        assertEquals(item1.getName(),"Name");
    }
    @Test
    void changeName_invalid_all_the_letter() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        String new_name = "abcdefghijklmnopqrstuvwxyzabcdefghijk" +
        "lmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefgh" +
                "ijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvw";
        Item.changeName(item1,new_name);
        assertEquals(item1.getName(),"Name");
    }
    @Test
    void changeName_valid_all_the_letter() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        String new_name = "abcdefghijklmnopqrstuvwxyzabcdefghijk" +
                "lmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef" +
                "ghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefgh" +
                "ijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv";
        Item.changeName(item1,new_name);
        assertEquals(item1.getName(),new_name);
    }

    @Test
    void changeValue() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        String new_value = "4";
        String money_amount = "$4.00";
        Item.changeValue(item1,new_value);
        assertEquals(money_amount,item1.getValue());

    }
    @Test
    void changeValue_Dont_Change_Invalid() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        String new_value = "string";
        Item.changeValue(item1,new_value);
        assertEquals("3",item1.getValue());

    }

    @Test
    void changeSN() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        Item item2 = new Item("3","bbbbbbbbbb","Name");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2);
        String new_SN = "aaaaaaaaaa";
        Item.changeSN(items,item1,new_SN);
        assertEquals(new_SN,item1.getS_number());
    }
    @Test
    void changeSN_Invalid_SN() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        Item item2 = new Item("3","bbbbbbbbbb","Name");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2);
        String new_SN = "aaaaaaaaaaa";
        Item.changeSN(items,item1,new_SN);
        assertEquals("xxxxxxxxxx",item1.getS_number());
    }
    @Test
    void changeSN_Matching_SN() {
        Item item1 = new Item("3","xxxxxxxxxx","Name");
        Item item2 = new Item("3","bbbbbbbbbb","Name");
        ObservableList<Item> items = FXCollections.observableArrayList(item1,item2);
        String new_SN = "bbbbbbbbbb";
        Item.changeSN(items,item1,new_SN);
        assertEquals("xxxxxxxxxx",item1.getS_number());
    }
}