package server;


import org.springframework.web.bind.annotation.*;
import pojos.Activity;
import pojos.Session;
import services.ActivityService;
import services.SessionService;

import java.util.ArrayList;

@RestController
public class ActivityController {

    ActivityService as = new ActivityService();
    SessionService ss = new SessionService();
    SessionController sc = new SessionController();

    @RequestMapping("/get_activity/{sessionId}")
    public ArrayList<Activity> getActivities(@PathVariable("sessionId") String sessionId) {
        if(ss.sessionExists(sessionId)) {
            return as.getActivities(sc.getUser(sessionId));
        }
        return new ArrayList<Activity>();
    }

    @PostMapping("/add_activity/{sessionId}")
    public boolean addActivity(@RequestBody Activity activity, @PathVariable("sessionId") String sessionId)
    {
        if(ss.sessionExists(sessionId)) {
            return as.createActivity(activity);
        }
        return false;
    }
}
