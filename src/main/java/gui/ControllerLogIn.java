package gui;

import client.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogIn implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private BorderPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Loads the home page when the user is logged in.
     * @param actionEvent the action event on which the user should be logged in
     * @throws IOException when something with the action event goes wrong
     */
    public void loadHome(ActionEvent actionEvent) throws IOException {

        System.out.println(username.getText());
        if (Connect.serverLogin(username.getText(),String.valueOf(password.getText().hashCode()))) {
            BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
            ControllerHome.welcomeMessage(pane);
            rootPane.getChildren().setAll(pane);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid Credentials!");
            alert.setContentText("Please check you password and username");
            alert.showAndWait();
        }

    }

    /**
     * Loads the register page.
     * @param actionEvent the action event on which the user should be registered
     * @throws IOException when something with the action event goes wrong
     */
    public void loadRegister(ActionEvent actionEvent) throws IOException {
        Parent register = FXMLLoader.load(getClass().getClassLoader().getResource("Register.fxml"));
        Stage registerStage = new Stage();
        registerStage.setTitle("GO GREEN");
        registerStage.setScene(new Scene(register));
        registerStage.show();
    }
}
