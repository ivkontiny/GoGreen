package GUI;

import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pojos.Activity;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static pojos.Category.food;

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

    public void loadStatistics(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Statistics.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public ObservableList<Activity> getActivity(){
        ObservableList<pojos.Activity> activities = FXCollections.observableArrayList();
        ArrayList<Activity> my_activities = Connect.getActivities();
        for(Activity activity: my_activities) activities.add(activity);
        return activities;
    }

    public void logOut(javafx.event.ActionEvent actionEvent) throws IOException {
        Connect.logOut();
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
