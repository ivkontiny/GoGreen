package server;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojos.Activity;
import services.ActivityService;

@RestController
public class ActivityController {

    ActivityService as = new ActivityService();

    @RequestMapping("/get_activity/{description}")
    public Activity getActivity(@PathVariable("description") String description ) {
        return as.getActivity(description);
    }
}
