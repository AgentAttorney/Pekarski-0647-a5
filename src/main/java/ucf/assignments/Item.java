package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleDoubleProperty value;
    private SimpleStringProperty S_number;
    private SimpleStringProperty name;

    public Item(SimpleDoubleProperty value, SimpleStringProperty s_number, SimpleStringProperty name) {
        this.value = value;
        S_number = s_number;
        this.name = name;
    }

    public double getValue() {
        return value.get();
    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }

    public void setValue(double value) {
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
