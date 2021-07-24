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
}