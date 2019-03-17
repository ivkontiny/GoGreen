package GUI;

import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import pojos.Activity;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerMyLog implements Initializable {
    @FXML
    private BorderPane rootPane;

    @FXML
    private TableView logTable;
    @FXML
    private TableColumn<Activity, String> categoryColumn;
    @FXML
    private TableColumn<Activity, String> descriptionColumn;
    @FXML
    private TableColumn<Activity, Integer> pointsColumn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        logTable.setItems(getActivity());
    }


    /** Loads the home page.
     *
     * @param actionEvent the event needed to be made to go to the home page
     * @throws IOException when there is an error in the action
     */
    public void loadHome(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
        ControllerHome.welcomeMessage(pane);
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Loads the activity page on action.
     * @param actionEvent the action on which the activity page is loaded
     * @throws IOException when there is an error in the action
     */
    public void loadActivities(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane =
                FXMLLoader.load(getClass().getClassLoader().getResource("Activities.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Loads the statistics page.
     * @param actionEvent the action event on which the statistics page should be displayed
     * @throws IOException when something with the action event goes wrong
     */
    public void loadStatistics(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(
                getClass().getClassLoader().getResource("Statistics.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Displays the activities of the current user.
     * @return an observable list containing all activities
     */
    public ObservableList<Activity> getActivity() {
        ObservableList<pojos.Activity> activities = FXCollections.observableArrayList();
        ArrayList<Activity> myActivities = Connect.getActivities();
        for (Activity activity: myActivities) {
            activities.add(activity);
        }
        return activities;
    }

    /**
     * Logs a user out.
     * @param actionEvent the event on which the user should be logged out
     * @throws IOException when something with the action event goes wrong
     */
    public void logOut(javafx.event.ActionEvent actionEvent) throws IOException {
        Connect.logOut();
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
