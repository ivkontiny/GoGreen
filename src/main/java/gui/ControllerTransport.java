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
import pojos.Activity;
import pojos.Category;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerTransport implements Initializable {

    ObservableList<String> transportActivitieList =
            FXCollections.observableArrayList(ActivityDb.Transportation.descriptions);

    @FXML
    private ComboBox transportBox;

    @FXML
    private Label pointsText;


    @FXML
    private TextField distanceField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transportBox.setItems(transportActivitieList);
        distanceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    distanceField.setText(oldValue);
                }

                inputActivity();
            }
        });
    }


    /*/** Gets an activity and calculates its points.
     *
     * param actionEvent the action event on which points should be calculated
     */
    public void inputActivity(/*javafx.event.ActionEvent actionEvent*/) {

        for (int i = 0; i < transportActivitieList.size(); i++) {
            if (ActivityDb.Transportation.descriptions.get(i).equals(transportBox.getValue())) {
                if (distanceField.getText().equals("")) {
                    pointsText.setText("Points: 0");
                } else {
                    pointsText.setText("Points: " + ActivityDb.Transportation.points.get(i) 
                        * Integer.parseInt(distanceField.getText()));
                }
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

        for (int i = 0; i < ActivityDb.Transportation.descriptions.size(); i++) {
            if (ActivityDb.Transportation.descriptions.get(i).equals(transportBox.getValue())) {
                actDesc = ActivityDb.Transportation.descriptions.get(i);
                points = ActivityDb.Transportation.points.get(i) 
                        * Integer.parseInt(distanceField.getText());
            }
        }

        Activity activity = new Activity(actDesc, Category.transportation, points,
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

