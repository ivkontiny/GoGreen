package gui;

import client.ConnectAccount;
import client.ConnectFriends;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.textfield.TextFields;
import pojos.Request;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerFriends implements Initializable {


    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField friendsField;
    @FXML
    private Label errorLabel;

    private ArrayList<String> usernames;

    @FXML
    private TableView<Request> requestTable;
    @FXML
    private TableColumn<Request, String> userColumn;
    @FXML
    private TableColumn<Request, Button> acceptColumn;
    @FXML
    private TableColumn<Request, Button> rejectColumn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        acceptColumn.setCellValueFactory(new PropertyValueFactory<>("accept"));
        rejectColumn.setCellValueFactory(new PropertyValueFactory<>("reject"));
        requestTable.setItems(getPendings());
        errorLabel.setVisible(false);

    }
    /**
     * Displays the pending requests of the current user.
     * @return an observable list containing all activities
     */
    public ObservableList<Request> getPendings() {
        ObservableList<Request> requests = FXCollections.observableArrayList();
        ArrayList<String> pendings = ConnectFriends.getPendingFriends();
        for (String user: pendings) {
            Button accept = new Button();
            Button reject = new Button();
            reject.setOnAction(e -> {
                removeRequest(user);
            });
            accept.setOnAction(e -> {
                ConnectFriends.acceptRequest(user);
                removeRequest(user);
            });

            requests.add(new Request(user,accept,reject));
        }
        return requests;
    }

    public void removeRequest(String user) {
        int counter = 0;
        for (Request search : requestTable.getItems()) {
            if (search.getUsername().equals(user)) {
                requestTable.getItems().remove(counter);
                break;
            }
            counter++;
        }

    }
    public void getUsers() {
        errorLabel.setVisible(false);
        if (friendsField.getText().length() == 1) {
            String match = friendsField.getText();
            usernames = ConnectAccount.getMatchingUsers(match);
            TextFields.bindAutoCompletion(friendsField, usernames);
        } else if(friendsField.getText().length() < 1){
            usernames = new ArrayList<>();
            TextFields.bindAutoCompletion(friendsField, usernames);
        }
    }

    public void loadMyLog(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("MyLog.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void sendRequest(javafx.event.ActionEvent actionEvent) {
        if (ConnectFriends.sendRequest(friendsField.getText())) {
            errorLabel.setStyle("-fx-text-fill: green");
            errorLabel.setText("Friend request sent SUCCESS");
        } else {
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setText("Friend request sent FAILED");
            if(usernames.contains(friendsField.getText())) errorLabel.setText("Friend request already sent");
        }
        errorLabel.setVisible(true);
    }

    /**
     * The message to be displayed on the home screen.
     * @param root the window of the application
     */
    public static void welcomeMessage(Parent root) {
        javafx.scene.control.Label welcome = (Label) root.lookup("#Welcome");
        if (welcome != null) {
            welcome.setText("Welcome " + ConnectAccount.getUsername());
        }
    }

    /**
     * Loads the activities window.
     * @param actionEvent the action on which the activity window needs to open
     * @throws IOException when there is a problem in the action event
     */
    public void loadActivities(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(
                getClass().getClassLoader().getResource("Activities.fxml"));

        rootPane.getChildren().setAll(pane);
    }

    /**
     * Loads the statistics page.
     * @param actionEvent the action event on which the statistics page should be displayed
     * @throws IOException when something with the action event goes wrong
     */
    public void loadStatistics(javafx.event.ActionEvent actionEvent) throws IOException {
        BorderPane pane = FXMLLoader.load(
                getClass().getClassLoader().getResource("Statistics.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Logs the user out.
     * @throws IOException when something with the event went wrong
     */
    public void logOut() throws IOException {
        ConnectAccount.logOut();
        BorderPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
