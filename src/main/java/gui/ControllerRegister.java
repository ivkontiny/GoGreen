package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegister implements Initializable {

    @FXML
    private javafx.scene.control.TextField email;
    @FXML
    private javafx.scene.control.TextField name;
    @FXML
    private javafx.scene.control.TextField surname;
    @FXML
    private javafx.scene.control.TextField username;
    @FXML
    private javafx.scene.control.TextField password;
    @FXML
    private javafx.scene.control.TextField confirmPassword;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Creates an account and then closes window when user click button
     * @param actionEvent
     */
    public void createAccount(javafx.event.ActionEvent actionEvent) {



        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
