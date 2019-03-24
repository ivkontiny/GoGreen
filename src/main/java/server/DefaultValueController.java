package server;

import org.springframework.web.bind.annotation.*;
import pojos.Category;
import pojos.DefaultValue;
import services.DefaultValueService;
import services.SessionService;

import java.util.ArrayList;

public class DefaultValueController {
    private DefaultValueService dvs = new DefaultValueService();
    private SessionService ss = new SessionService();

    @RequestMapping("/get_dv_by_category/{sessionId}")
    public ArrayList<String> getDefaultValuesFor(@RequestBody Category category,
                                                 @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return dvs.getValuesFromCategory(category);
        }

        return new ArrayList<>();
    }


    @DeleteMapping("/delete_default_value/{sessionId}")
    public void deleteDefaultValue(@RequestBody String desc,
                                   @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            dvs.deleteDefaultValue(desc);
        }
    }


    @RequestMapping("/get_default_value/{sessionId}")
    public DefaultValue getDefaultValue(@RequestBody String desc,
                                        @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return dvs.getDefaultValue(desc);
        }

        return null;
    }


    @PostMapping("/add_default_value/{sessionId}")
    public boolean addDefaultValue(@RequestBody DefaultValue dv,
                                   @PathVariable("sessionId") String sessionId) {
        if(ss.sessionExists(sessionId)) {
            return dvs.createDefaultValue(dv);
        }

        return false;
    }
}
