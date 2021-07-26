package ucf.assignments;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML TextField MonetaryValueInput;
    @FXML TextField SerialNumberInput;
    @FXML TextField NameInput;
    @FXML TextField SearchInput;

    @FXML Label ErrorSN;

    @FXML Button ConfirmButton;
    @FXML Button ResetTable;
    @FXML Button ValidateButton;

    @FXML private TableView<Item> listItem;
    @FXML private TableView<Item> listItemSearched;

    @FXML private TableColumn<Item, String> valueList;
    @FXML private TableColumn<Item, String> SerList;
    @FXML private TableColumn<Item, String> NameList;

    @FXML private TableColumn<Item, String> valueListSearch;
    @FXML private TableColumn<Item, String> SerListSearch;
    @FXML private TableColumn<Item, String> NameListSearch;

    @FXML
    public void AddItemClicked(ActionEvent event) throws IOException {
        // gets the count of how many SNs our new item will not conflict with
        int count = Item.NotMatchSN(listItem.getItems(),SerialNumberInput.getText());
        // Create a formatter for our monetary value
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        // Convert String inputs into SimpleStringProperties (and get the value of the double and format it)
        String sspMoney = formatter.format((Double.parseDouble(MonetaryValueInput.getText())));
        String ssp1 = SerialNumberInput.getText();
        String ssp2 = NameInput.getText();

        // if our new Item SN does not match all elements
        // make the item with the created properties and update our list
        // Set the ErrorSN message invisible if it wasn't already
        if(count == listItem.getItems().size()){
            Item item = new Item(sspMoney,ssp1,ssp2);
            ObservableList<Item> items_Plus1 = Item.addItem(listItem.getItems(),item);
            listItem.setItems(items_Plus1);
            ErrorSN.setVisible(false);
        }
        else{
            // There was an item that matched the SN
            ErrorSN.setVisible(true);
        }
        // Make our text fields editable for a new item and disable the confirm / add item button
        MonetaryValueInput.setEditable(true);
        SerialNumberInput.setEditable(true);
        NameInput.setEditable(true);

        ConfirmButton.setDisable(true);
    }
    public void ValidateButtonPressed(ActionEvent event){
        // Get if the properties of the textfields are valid for a hypothetical item
        boolean vp = Item.ValidPrice(MonetaryValueInput.getText());
        boolean vsn = Item.ValidSN(SerialNumberInput.getText());
        boolean vname = Item.ValidName(NameInput.getText());

        // If the properties are all valid, enable the add Item button and set the fields to be uneditable
        if(vp && vsn && vname){
            ConfirmButton.setDisable(false);
            SerialNumberInput.setEditable(false);
            MonetaryValueInput.setEditable(false);
            NameInput.setEditable(false);
        }

    }
    public void CancelButtonPressed(ActionEvent event){

        // Reset the state of the item-related buttons and their fields

        ConfirmButton.setDisable(true);

        MonetaryValueInput.setEditable(true);
        SerialNumberInput.setEditable(true);
        NameInput.setEditable(true);

        MonetaryValueInput.setText("");
        SerialNumberInput.setText("");
        NameInput.setText("");
    }

    public void changeNameEdit(TableColumn.CellEditEvent<Item,String> editedCell){
        // Get the item selected and its new name
        Item item_selected = listItem.getSelectionModel().getSelectedItem();
        String new_name = editedCell.getNewValue();
        boolean was_changed = Item.changeName(item_selected,new_name);

        if(!was_changed){
            // bro this works, thank god; refresh the (old) table
            listItem.refresh();
        }
    }
    @FXML
    public void changeValueEdit(TableColumn.CellEditEvent<Item,String> editedCell){
        // get the item selected and the raw input
        Item item_selected = listItem.getSelectionModel().getSelectedItem();
        String raw_number = editedCell.getNewValue();
        boolean was_changed = Item.changeValue(item_selected,raw_number);

        if(!was_changed){
            // refresh the old table view
            listItem.refresh();
        }
    }
    @FXML
    public void changeSNEdit(TableColumn.CellEditEvent<Item,String> editedCell){
        // Get the item and the value of our selected cell
        Item item_selected = listItem.getSelectionModel().getSelectedItem();
        String new_SN = editedCell.getNewValue();
        boolean was_changed = Item.changeSN(listItem.getItems(),item_selected,new_SN);
        if(!was_changed){
            listItem.refresh();
        }
    }
    public void deleteButtonClicked(ActionEvent event){
        ObservableList<Item> all = listItem.getItems();
        ObservableList<Item> to_remove = listItem.getSelectionModel().getSelectedItems();

        ObservableList<Item> not_removed = Item.removeItems(to_remove,all);
        listItem.setItems(not_removed);

    }

    @FXML
    public void SearchButtonClicked(ActionEvent event){
        String search = SearchInput.getText().toLowerCase();
        ObservableList<Item> search_matches = Item.SearchCriteria(search,listItem.getItems());

        listItemSearched.getItems().addAll(search_matches);
        listItem.setVisible(false);
        listItemSearched.setVisible(true);
        ResetTable.setDisable(false);
        ValidateButton.setDisable(true);

    }
    @FXML
    public void ResetTableClicked(ActionEvent event){
        ObservableList<Item> empty = FXCollections.observableArrayList();
        listItemSearched.setItems(empty);
        listItemSearched.setVisible(false);
        listItem.setVisible(true);
        ResetTable.setDisable(true);
        ValidateButton.setDisable(false);
    }
    @FXML
    public void SaveButtonClicked(ActionEvent event){
    }
    @FXML
    public void LoadButtonClicked(ActionEvent event){
    }
    @FXML
    public void ExitAppClicked(ActionEvent event){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set what each of our columns data types are
        valueList.setCellValueFactory(new PropertyValueFactory<Item,String>("value"));
        SerList.setCellValueFactory(new PropertyValueFactory<Item,String>("S_number"));
        NameList.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));

        valueListSearch.setCellValueFactory(new PropertyValueFactory<Item,String>("value"));
        SerListSearch.setCellValueFactory(new PropertyValueFactory<Item,String>("S_number"));
        NameListSearch.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));

        // set our table view editable and Set how each column in our table view is editable
        listItem.setEditable(true);
        listItem.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //valueList.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        valueList.setCellFactory(TextFieldTableCell.forTableColumn());
        SerList.setCellFactory(TextFieldTableCell.forTableColumn());
        NameList.setCellFactory(TextFieldTableCell.forTableColumn());

        ConfirmButton.setDisable(true);
        ResetTable.setDisable(true);
        listItemSearched.setVisible(false);

        ErrorSN.setText("The Last SN matched an existing item, and was not added.");
        ErrorSN.setVisible(false);

        // return an empty list
        listItem.setItems(listItem.getItems());
        listItemSearched.setItems(listItemSearched.getItems());
    }
}
