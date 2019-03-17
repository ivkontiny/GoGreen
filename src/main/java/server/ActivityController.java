package server;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.*;
import pojos.Activity;
import services.ActivityService;
import services.SessionService;

import java.util.ArrayList;

@RestController
public class ActivityController {

    ActivityService as = new ActivityService();
    SessionService ss = new SessionService();
    SessionController sc = new SessionController();

    /**
     * Gets the activities of a user.
     * @param sessionId the sessionId of the user whose activities we want
     * @return a list containing all activities of the user
     */
    @RequestMapping("/get_activity/{sessionId}")
    public ArrayList<Activity> getActivities(@PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return as.getActivities(sc.getUser(sessionId));
        }
        return new ArrayList<Activity>();
    }

    /**
     * Adds activity to a current user.
     * @param activity the activity to be added
     * @param sessionId the sessionId of the user
     * @return true if the process was successful, false otherwise
     */
    @PostMapping("/add_activity/{sessionId}")
    public boolean addActivity(@RequestBody Activity activity,
                               @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return as.createActivity(activity);
        }
        return false;
    }
}
