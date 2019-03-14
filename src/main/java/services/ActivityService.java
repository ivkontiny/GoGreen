package services;

import database.ActivityDao;
import pojos.Activity;

import java.util.ArrayList;

public class ActivityService {

    ActivityDao db = new ActivityDao();

    public ArrayList<Activity> getActivities(String description) {
        return db.getActivities(description);
    }


    public boolean createActivity(Activity act) {
        return db.createActivity(act);
    }


    public void setDb(ActivityDao db) {
        this.db = db;
    }
}



