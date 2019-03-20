package gui;

        import client.Connect;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Alert;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.Label;
        import pojos.Activity;
        import pojos.Category;

        import java.net.URL;
        import java.sql.Date;
        import java.time.LocalDate;
        import java.util.ResourceBundle;

public class ControllerTransport implements Initializable {

    ObservableList<String> transportActivitieList =
            FXCollections.observableArrayList("Bike", "Train");

    @FXML
    private ComboBox transportBox;

    @FXML
    private Label pointsText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transportBox.setItems(transportActivitieList);
    }


    /** Gets an activity and calculates its points.
     *
     * @param actionEvent the action event on which points should be calculated
     */
    public void inputActivity(javafx.event.ActionEvent actionEvent) {

        for (int i = 0; i < transportActivitieList.size(); i++) {
            if (ActivityDb.Food.descriptions.get(i).equals(transportBox.getValue())) {
                pointsText.setText("Points: " + ActivityDb.Food.points.get(i));
            }
        }
    }

    /**
     * Adds an activity to the current user.
     * @param actionEvent the action event on which a new activity should be added
     */
    public void addActivity(javafx.event.ActionEvent actionEvent) {
        String actDesc = null;
        int points = 0;

        for (int i = 0; i < ActivityDb.Food.descriptions.size(); i++) {
            if (ActivityDb.Food.descriptions.get(i).equals(transportBox.getValue())) {
                actDesc = ActivityDb.Food.descriptions.get(i);
                points = ActivityDb.Food.points.get(i);
            }
        }

        Activity activity = new Activity(actDesc, Category.food, points,
                Date.valueOf(LocalDate.now()), Connect.getUsername());

        if (Connect.addActivity(activity)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Activity added successfully!");
            alert.setContentText(actDesc + " added successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("Your " + actDesc + " couldn't be added!");
            alert.showAndWait();
        }
    }
}

