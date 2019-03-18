package gui;

import client.Connect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;
import pojos.Account;

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
     * Checks fields.
     * @return alert message
     */
    public String checkFields() {
        EmailValidator ev = EmailValidator.getInstance();
        String mail = email.getText();
        String user = username.getText();
        String pass = password.getText();

        if (mail.length() == 0) {
            return "Email field empty";
        }
        if (!ev.isValid(mail)) {
            return "Email not valid";
        }
        if (user.length() <= 5) {
            return "Username too short (6 characters at least)";
        }
        if (!user.matches("[a-zA-Z0-9]*")) {
            return "Username contains a forbidden character";
        }
        if (user.charAt(0) >= '0' && user.charAt(0) <= '9') {
            return "Username cannot start with a digit";
        }
        if (surname.getText().length() == 0) {
            return "Surname field empty";
        }
        if (name.getText().length() == 0) {
            return "Name field empty";
        }
        if (pass.length() <= 6) {
            return "Password too short (7 characters at least)";
        }
        if (!pass.matches("[a-zA-Z0-9]*")) {
            return "Password contains a forbidden character!";
        }
        if (!pass.equals(confirmPassword.getText())) {
            return "The passwords do not match";
        }
        return "OK";
    }

    /**
     * Alerts user of username already in use.
     */
    public void alertSameUsername() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Something went wrong");
        alert.setContentText("Choose another username");
        alert.showAndWait();
    }

    /**
     * Alerts users with custom alert message.
     */
    public void customAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * Confirmation of registering user.
     */
    public void alertUserCreated() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Account successfully created");
        alert.showAndWait();
    }

    /**
     * Creates an account and then closes window when user click button.
     * @param actionEvent the action event
     */
    public void createAccount(javafx.event.ActionEvent actionEvent) {

        Account user = new Account(username.getText(),email.getText(),
                String.valueOf(password.getText().hashCode()),name.getText(),
                surname.getText());

        if (!checkFields().equals("OK")) {
            customAlert(checkFields());
            return;
        }

        if (Connect.serverRegister(user)) {
            alertUserCreated();
            final Node source = (Node) actionEvent.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else {
            alertSameUsername();
        }
    }
}
