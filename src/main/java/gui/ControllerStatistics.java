package gui;

import client.Connect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerStatistics implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private LineChart<String, Number> linechart;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button addButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // X-axis: dates array
        String[] dates = {"2-3-19", "3-3-19", "4-3-19", "5-3-19", "6-3-19", "7-3-19", "8-3-19"};

        // User's own series
        XYChart.Series userSeries = new XYChart.Series();
        userSeries.setName("You");

        int[] userPoints = {10, 50, 100, 25, 50, 30, 100};

        for(int i = 0; i <= 6; i++) {
            userSeries.getData().add(new XYChart.Data(dates[i], userPoints[i]));
        }

        // Friend's series
        choiceBox.getItems().addAll("Frank", "Dik", "Jasper");
        addButton.setOnAction(e -> addFriend(choiceBox, dates));

        // Plot series of points
        linechart.getData().add(userSeries);
    }

    /**
     * Loads the home page.
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
     * Loads the my log page on action.
     *
     * @param actionEvent the action on which the my log page should be loaded
     * @throws IOException when something with the action event goes wrong
     */
    public void loadMyLog(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("MyLog.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Loads the activity page on action.
     *
     * @param actionEvent the action on which the activity page is loaded
     * @throws IOException when there is an error in the action
     */
    public void loadActivities(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane =
                FXMLLoader.load(getClass().getClassLoader().getResource("Activities.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Logs the user out.
     * @param actionEvent the event on which the user should be logged out
     * @throws IOException when something with the action event goes wrong
     */
    public void logOut(javafx.event.ActionEvent actionEvent) throws IOException {
        Connect.logOut();
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Get choice from choicebox
     * @param ChoiceBox
     * @return String
     */
    public void addFriend(ChoiceBox<String> choiceBox, String[] dates) {
        String friend = choiceBox.getValue();

        XYChart.Series friendSeries = new XYChart.Series();
        friendSeries.setName(friend);

        int[] friendPoints = {100, 30, 200, 10, 30, 100, 100};

        for(int i = 0; i <= 6; i++) {
            friendSeries.getData().add(new XYChart.Data(dates[i], friendPoints[i]));
        }

        linechart.getData().add(friendSeries);
    }
}

