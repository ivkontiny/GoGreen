package gui;

import client.ConnectAccount;
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
        primaryStage.setTitle("GO GREEN");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (ConnectAccount.getSessionId() == null) {
            return;
        }
        if (!ConnectAccount.getSessionId().equals("")) {
            ConnectAccount.logOut();
        }
    }


}
