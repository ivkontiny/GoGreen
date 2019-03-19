package gui;

import client.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerActivities implements Initializable {



    ObservableList<String> transportationActivitieList = FXCollections.observableArrayList("Bike");

    @FXML
    private BorderPane rootPane;
    @FXML
    private AnchorPane activityPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Loads the home page.
     * @param actionEvent the action event on which the home page should be loaded
     * @throws IOException when the event isn't properly handled
     */
    public void loadHome(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
        ControllerHome.welcomeMessage(pane);
        rootPane.getChildren().setAll(pane);
    }


    public void loadMyLog(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("MyLog.fxml"));
        rootPane.getChildren().setAll(pane);
    }


    /**
     * Loads the statistics page.
     * @param actionEvent the action event on which the statistics page should be opened
     * @throws IOException when something with the action event goes wrong
     */
    public void loadStatistics(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(
                getClass().getClassLoader().getResource("Statistics.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Logs the user out.
     * @param actionEvent the action event on which the user should be logged out
     * @throws IOException when something in the action went wrong
     */
    public void logOut(javafx.event.ActionEvent actionEvent) throws IOException {
        Connect.logOut();
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void loadFoodActivity() throws IOException {
        AnchorPane foodPane = FXMLLoader.load(getClass().getClassLoader().getResource("Food.fxml"));
        activityPane.getChildren().setAll(foodPane);

    }

    public void loadTransportActivity(MouseEvent mouseEvent) throws IOException {
        AnchorPane transportPane = FXMLLoader.load(getClass().getClassLoader().getResource("Transport.fxml"));
        activityPane.getChildren().setAll(transportPane);
    }

    public void loadEnergyActivity(MouseEvent mouseEvent) throws IOException {
        AnchorPane energyPane = FXMLLoader.load(getClass().getClassLoader().getResource("Energy.fxml"));
        activityPane.getChildren().setAll(energyPane);
    }
}
