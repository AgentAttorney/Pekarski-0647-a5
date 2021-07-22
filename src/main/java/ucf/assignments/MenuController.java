package ucf.assignments;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML TextField MonetaryValueInput;
    @FXML TextField SerialNumberInput;
    @FXML TextField NameInput;

    @FXML Button ConfirmButton;

    /*FXML MenuBar optionsBar;

    @FXML Menu File;
    @FXML Menu Item;
    @FXML Menu Search;

    @FXML MenuItem Save;
    @FXML MenuItem Load;
    @FXML MenuItem Exit;
    @FXML MenuItem Add;
    @FXML MenuItem Name;
    @FXML MenuItem SerialNum;*/

    @FXML private TableView<Item> listItem;
    @FXML private TableColumn<Item, Double> valueList;
    @FXML private TableColumn<Item, String> SerList;
    @FXML private TableColumn<Item, String> NameList;

    @FXML
    public void AddItemClicked(ActionEvent event) throws IOException {
        // will change to testable method later
        // gets the values from our textfields, converts them to the correct property types
        // Creates a new item with those properties and adds it to the table
        SimpleDoubleProperty sdp = new SimpleDoubleProperty(Double.parseDouble(MonetaryValueInput.getText()));
        SimpleStringProperty ssp1 = new SimpleStringProperty(SerialNumberInput.getText());
        SimpleStringProperty ssp2 = new SimpleStringProperty(NameInput.getText());
        Item item = new Item(sdp,ssp1,ssp2);
        listItem.getItems().add(item);
    }

    @FXML
    public void ExitAppClicked(ActionEvent event){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set what each of our columns data types are
        valueList.setCellValueFactory(new PropertyValueFactory<Item,Double>("value"));
        SerList.setCellValueFactory(new PropertyValueFactory<Item,String>("S_number"));
        NameList.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));

        // set our table view editable and Set how each column in our table view is editable
        listItem.setEditable(true);
        valueList.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        SerList.setCellFactory(TextFieldTableCell.forTableColumn());
        NameList.setCellFactory(TextFieldTableCell.forTableColumn());
        // return an empty list
        listItem.setItems(listItem.getItems());

    }
}
