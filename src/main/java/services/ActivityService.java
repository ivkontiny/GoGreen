package services;

import database.ActivityDao;
import pojos.Activity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityService {

    private ActivityDao db = new ActivityDao();
    private AccountService as = new AccountService();

    /**
     * Get all activities with a certain description.
     * @param description the description of the activities we are looking for
     * @return a list containing all the activities with the wanted description
     */
    public ArrayList<Activity> getActivities(String description) {
        try {
            return db.getActivities(description);
        } catch (SQLException e) {
            return null;
        }
    }


    /**
     * Create an activity.
     * @param act the activity to be created
     * @return true if the activity was created successfully, false otherwise
     */
    public boolean createActivity(Activity act) {
        try {
            as.updatePoints(act.getUsername(), act.getPoints());
            return db.createActivity(act);
        } catch (SQLException e) {
            return false;
        }
    }


    public void setDb(ActivityDao db) {
        this.db = db;
    }
}



