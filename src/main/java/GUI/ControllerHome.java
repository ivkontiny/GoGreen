package GUI;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private BorderPane rootPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void loadMyLog(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("MyLog.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public static void welcomeMessage(Parent root)
    {
        javafx.scene.control.Label Welcome = (Label) root.lookup("#Welcome");
        if (Welcome!=null) Welcome.setText("Welcome " + Connect.getUsername());
    }

    public void testing(ActionEvent actionEvent) {
        System.out.println("Button is working!");
    }

    public void loadActivities(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Activities.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}

