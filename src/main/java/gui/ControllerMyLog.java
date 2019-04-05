package gui;

import client.ConnectAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import pojos.Activity;

import java.io.FileInputStream;
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

    @FXML
    private VBox feed;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Activity> activities = getActivity();

        for(int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);

            feed.getChildren().add(ControllerMyLog.newLog(activity.getDescription(), activity.getDate().toString(), Integer.toString(activity.getPoints())));
        }
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
     * Loads the friends page.
     * @param actionEvent the action event on which the friends page should be displayed
     * @throws IOException when something with the action event goes wrong
     */
    public void loadFriends(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Friends.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Displays the activities of the current user.
     * @return an observable list containing all activities
     */
    public ObservableList<Activity> getActivity() {
        ObservableList<pojos.Activity> activities = FXCollections.observableArrayList();
        ArrayList<Activity> myActivities = ConnectAccount.getActivities();
        for (Activity activity: myActivities) {
            activities.add(activity);
        }
        return activities;
    }

    public static AnchorPane newLog(String activityDescription, String date, String points) {
        AnchorPane logLayout = new AnchorPane();
        logLayout.setPrefSize(480, 100);

        Rectangle bigRect = new Rectangle(490, 80);
        bigRect.setStyle("-fx-fill: white; -fx-stroke: #9bc782;");
        bigRect.setLayoutX(1.0);
        bigRect.setLayoutY(8.0);

        Rectangle smallRect = new Rectangle(340, 80);
        smallRect.setStyle("-fx-fill: #9bc782;");
        smallRect.setLayoutX(76.0);
        smallRect.setLayoutY(8.0);

        Label logDescr = new Label(activityDescription);
        logDescr.setStyle("-fx-text-fill: white; -fx-text-alignment: center; -fx-font-size: 22px;");
        logDescr.setLayoutX(90.0);
        logDescr.setLayoutY(18.0);

        Label logDate = new Label("Added on " + date);
        logDate.setLayoutX(90.0);
        logDate.setLayoutY(53.0);

        Label logNumPoints = new Label(points);
        logNumPoints.setStyle("-fx-font-size: 24px; -fx-alignment: center;");
        logNumPoints.setPrefHeight(46.0);
        logNumPoints.setPrefWidth(75.0);
        logNumPoints.setLayoutX(415.0);
        logNumPoints.setLayoutY(12.0);

        Label logPoints = new Label("POINTS");
        logPoints.setLayoutX(427.0);
        logPoints.setLayoutY(54.0);

        logLayout.getChildren().addAll(bigRect, smallRect, logDescr, logDate, logNumPoints, logPoints);

        return logLayout;
    }

    /**
     * Logs a user out.
     * @param actionEvent the event on which the user should be logged out
     * @throws IOException when something with the action event goes wrong
     */
    public void logOut(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        ConnectAccount.logOut();
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
