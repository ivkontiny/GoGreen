package gui;

import client.ConnectAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pojos.Activity;
import pojos.Category;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        energyBox.setItems(energyActivitieList);
    }


    /** Gets an activity and calculates its points.
     *
     * @param actionEvent the action event on which points should be calculated
     */
    public void inputActivity(javafx.event.ActionEvent actionEvent) {

        for (int i = 0; i < energyActivitieList.size(); i++) {
            if (ActivityDb.Energy.descriptions.get(i).equals(energyBox.getValue())) {
                pointsText.setText("Points: " + ActivityDb.Energy.points.get(i));
            }
        }
    }

    /**
     * Adds an activity to the current user.
     * @param actionEvent the action event on which a new activity should be added
     */
    public void addActivity(javafx.event.ActionEvent actionEvent) {
        String actDesc = null;
        int points = 0;

        for (int i = 0; i < ActivityDb.Energy.descriptions.size(); i++) {
            if (ActivityDb.Energy.descriptions.get(i).equals(energyBox.getValue())) {
                actDesc = ActivityDb.Energy.descriptions.get(i);
                points = ActivityDb.Energy.points.get(i);
            }
        }

        Activity activity = new Activity(actDesc, Category.energy, points,
                Date.valueOf(LocalDate.now()), ConnectAccount.getUsername());

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
