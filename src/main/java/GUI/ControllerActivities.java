package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerActivities implements Initializable {

    ObservableList<String> foodActivitieList =
            FXCollections.observableArrayList("Vegetarian meal", "Biological meal");

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
        if (activityBox.getValue().equals("Vegetarian meal")) {
            pointsText.setText("Points: 150");
        }

        if (activityBox.getValue().equals("Biological meal")) {
            pointsText.setText("Points: 100");
        }
    }


}
