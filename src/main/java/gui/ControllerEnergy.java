package gui;

import client.ConnectAccount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pojos.Account;
import pojos.Activity;
import pojos.Category;
import pojos.DefaultValue;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerEnergy implements Initializable {

    ObservableList<String> energyActivitieList =
            FXCollections.observableArrayList(ActivityDb.Energy.descriptions);

    @FXML
    private ComboBox energyBox;

    @FXML
    private Label pointsText;

    @FXML
    private TextField energyAmount;


    @FXML
    private Label amountLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        energyBox.setItems(energyActivitieList);
        energyAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    energyAmount.setText(oldValue);
                }
                inputActivity();
            }
        });

    }


    /**
     * Gets an activity and calculates its points.
     *
     *
     */
    public void inputActivity() {

        for (int i = 0; i < energyActivitieList.size(); i++) {
            if (ActivityDb.Energy.descriptions.get(i).equals(energyBox.getValue())) {
                if (energyBox.getValue().equals("Power saved by solar panels")) {
                    amountLabel.setText("Energy saved (in kWh)");
                    if (energyAmount.getText().equals("")) {
                        pointsText.setText("Points: 0");
                    } else {
                        pointsText.setText("Points: " + DefaultValue.kwhToPoints(Integer.parseInt(energyAmount.getText())));
                    }
                }
            }
            /*if (energyBox.getValue().equals("Power saved by solar panels")) {
                amountLabel.setText("Energy saved (in kWh)");
            }*/
        }
    }



    /**
     * Adds an activity to the current user.
     *
     * @param actionEvent the action event on which a new activity should be added
     */
    public void addActivity(javafx.event.ActionEvent actionEvent) {
        String actDesc = null;
        int points = 0;

        for (int i = 0; i < ActivityDb.Energy.descriptions.size(); i++) {
            if (ActivityDb.Energy.descriptions.get(i).equals(energyBox.getValue())) {
                actDesc = ActivityDb.Energy.descriptions.get(i);
                points = DefaultValue.kwhToPoints(Integer.parseInt(energyAmount.getText()));
            }
        }

        Activity activity = new Activity(actDesc, Category.energy, points,
                Date.valueOf(LocalDate.now()), ConnectAccount.getUsername());

        if(actDesc.equals("Power saved by solar panels")) {
            ConnectAccount.setEnergy(Integer.parseInt(energyAmount.getText()));
        }

        if (ConnectAccount.addActivity(activity)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Activity added successfully!");
            alert.setContentText(actDesc + " added successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("Your " + actDesc + " couldn't be added!");
            alert.showAndWait();
        }
    }
}
