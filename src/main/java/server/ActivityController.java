package server;


import org.springframework.web.bind.annotation.*;
import pojos.Activity;
import services.ActivityService;

@RestController
public class ActivityController {

    ActivityService as = new ActivityService();

    @RequestMapping("/get_activity/{description}")
    public Activity getActivity(@PathVariable("description") String description ) {
        return as.getActivity(description);
    }

    @PostMapping("/add_activity")
    public boolean addActivity(@RequestBody Activity activity) {
        return as.createActivity(activity);
    }
}
