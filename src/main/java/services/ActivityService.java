package services;

import database.AccountDao;
import database.ActivityDao;
import pojos.Activity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityService {

   private ActivityDao db = new ActivityDao();
   private AccountService as = new AccountService();

    public ArrayList<Activity> getActivities(String description) {
        try {
            return db.getActivities(description);
        } catch (SQLException e) {
            return null;
        }
    }


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



