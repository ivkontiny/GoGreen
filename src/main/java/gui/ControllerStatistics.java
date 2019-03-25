package gui;

import client.Connect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerStatistics implements Initializable {
    @FXML
    private BorderPane rootPane;
    @FXML
    private LineChart<String, Number> linechart;
    @FXML
    private CategoryAxis dateaxis;
    @FXML
    private NumberAxis pointsaxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series userSeries = new XYChart.Series();

        userSeries.getData().add(new XYChart.Data("Jan", 23));
        userSeries.getData().add(new XYChart.Data("Feb", 14));
        userSeries.getData().add(new XYChart.Data("Mar", 15));
        userSeries.getData().add(new XYChart.Data("Apr", 24));
        userSeries.getData().add(new XYChart.Data("May", 34));
        userSeries.getData().add(new XYChart.Data("Jun", 36));
        userSeries.getData().add(new XYChart.Data("Jul", 22));
        userSeries.getData().add(new XYChart.Data("Aug", 45));
        userSeries.getData().add(new XYChart.Data("Sep", 43));
        userSeries.getData().add(new XYChart.Data("Oct", 17));
        userSeries.getData().add(new XYChart.Data("Nov", 29));
        userSeries.getData().add(new XYChart.Data("Dec", 25));

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
}

