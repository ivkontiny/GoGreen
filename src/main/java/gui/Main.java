package gui;

import client.Connect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        ActivityDb.initialize();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
        //ControllerHome.welcomeMessage(root);
        primaryStage.setTitle("GO GREEN");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (Connect.getSessionId() == null) {
            return;
        }
        if (!Connect.getSessionId().equals("")) {
            Connect.logOut();
        }
    }
}
