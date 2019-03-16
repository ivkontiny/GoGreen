package GUI;

import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import pojos.Activity;
import pojos.Category;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerActivities implements Initializable {

    ObservableList<String> foodActivitieList =
            FXCollections.observableArrayList(ActivityDB.Food.descriptions);

    ObservableList<String> transportationActivitieList = FXCollections.observableArrayList("Bike");

    @FXML
    private BorderPane rootPane;
    @FXML
    private ComboBox activityBox;

    @FXML
    private Label pointsText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        activityBox.setItems(foodActivitieList);
    }

    public void loadHome(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
        ControllerHome.welcomeMessage(pane);
        rootPane.getChildren().setAll(pane);
    }


    public void loadMyLog(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("MyLog.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    /** Gets an activity and calculates its points.
     *
     * @param actionEvent the action that was made
     */
    public void inputActivity(javafx.event.ActionEvent actionEvent) {

        for(int i = 0; i< ActivityDB.Food.descriptions.size(); i++)
        {
            if(ActivityDB.Food.descriptions.get(i).equals(activityBox.getValue()))
            {
                pointsText.setText("Points: " + ActivityDB.Food.points.get(i));
            }
        }
    }

    public void addActivity(javafx.event.ActionEvent actionEvent) {
        String actDesc = null;
        int points = 0;

        for (int i = 0; i< ActivityDB.Food.descriptions.size(); i++) {
            if (ActivityDB.Food.descriptions.get(i).equals(activityBox.getValue())) {
                actDesc = ActivityDB.Food.descriptions.get(i);
                points = ActivityDB.Food.points.get(i);
            }
        }

        Activity activity = new Activity(actDesc, Category.food, points,
                Date.valueOf(LocalDate.now()), Connect.getUsername());

        if (Connect.addActivity(activity)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Activity added successfully!");
            alert.setContentText(actDesc + " added successfully!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("Your " + actDesc + " couldn't be added!");
            alert.showAndWait();
        }
    }

    public void loadStatistics(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Statistics.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void logOut(javafx.event.ActionEvent actionEvent) throws IOException {
        Connect.logOut();
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
