package tech.neurobyte.dev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import tech.neurobyte.dev.utils.Currency;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // internal
    private final HashMap<String, Entry> table = new HashMap<>();
    @FXML
    private JFXComboBox<Label> currencyPicker;

    @FXML
    private JFXTextField expenseField;
    @FXML
    private JFXTextField valueField;
    @FXML
    private JFXButton add;
    // components
    @FXML
    private JFXListView<GridPane> list;
    private static String currency = "£";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add currencies to combobox
        for (String name : Currency.list.keySet()) {
            currencyPicker.getItems().add(new Label(name));
        }

        currencyPicker.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
            MainController.currency = Currency.list.get(newVal.getText());
            // refresh display
            for (Entry e : table.values()) {
                e.refreshDisplay();
            }
        });

        valueField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                add.getOnAction().handle(null);
            }
        });

        add.setOnAction(e -> {
            if (!expenseField.getText().isEmpty() && !valueField.getText().isEmpty()) {
                float value = Float.parseFloat(valueField.getText());

                if (table.containsKey(expenseField.getText())) {
                    table.get(expenseField.getText()).increment(value);
                } else {
                    Label display = new Label();
                    Entry entry = new Entry(value, display);
                    table.put(expenseField.getText(), entry);

                    GridPane gridEntry = new GridPane();
                    gridEntry.setHgap(30);
                    gridEntry.add(new Label(expenseField.getText()), 0, 0);
                    gridEntry.add(display, 1, 0);
                    list.getItems().add(gridEntry);
                }

                expenseField.setText("");
                valueField.setText("");
            }
        });
    }

    private static class Entry {
        private float value;
        private final Label display;

        Entry(float value, Label display) {
            this.display = display;
            setValue(value);
        }

        void increment(float value) {
            setValue(this.value + value);
        }

        void setValue(float value) {
            this.value = value;
            refreshDisplay();
        }

        void refreshDisplay() {
            this.display.setText(String.format("%s%.2f", MainController.currency, value));
        }
    }
}
