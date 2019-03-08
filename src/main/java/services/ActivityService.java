package services;

import database.ActivityDao;
import pojos.Activity;

public class ActivityService {

    ActivityDao db = new ActivityDao();

    public Activity getActivity(String description)
    {
        return db.getActivity(description);
    }

    public boolean createActivity(Activity act)
    {
        return db.createActivity(act);
    }
    public void setDb(ActivityDao db)
    {
        this.db = db;
    }
}



