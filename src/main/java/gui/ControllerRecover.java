package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRecover implements Initializable {
    @FXML
    private TextField recoverEmail;
    @FXML
    private Button recoverButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendEmail(javafx.scene.input.MouseEvent actionEvent){
        //ConnectEmail.sendMail(recoverEmail.getText());
    }
}
