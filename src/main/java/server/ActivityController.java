package server;


import org.springframework.web.bind.annotation.*;
import pojos.Activity;
import services.ActivityService;

import java.util.ArrayList;

@RestController
public class ActivityController {

    ActivityService as = new ActivityService();

    @RequestMapping("/get_activity/{username}")
    public ArrayList<Activity> getActivities(@PathVariable("username") String username) {
        return as.getActivities(username);
    }

    @PostMapping("/add_activity")
    public boolean addActivity(@RequestBody Activity activity) {
        return as.createActivity(activity);
    }
}
