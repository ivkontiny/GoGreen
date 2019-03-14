package services;

import database.ActivityDao;
import pojos.Activity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityService {

    ActivityDao db = new ActivityDao();

    public ArrayList<Activity> getActivities(String description) {
        try {
            return db.getActivities(description);
        } catch (SQLException e) {
            return null;
        }
    }


    public boolean createActivity(Activity act) {
        try {
            return db.createActivity(act);
        } catch (SQLException e) {
            return false;
        }
    }


    public void setDb(ActivityDao db) {
        this.db = db;
    }
}



