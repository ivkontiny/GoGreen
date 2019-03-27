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

    /**
     * Gets the all defaultvalues by category.
     * @param category The category of the activity
     * @param sessionId The sessionId belonging to the current session of the user
     * @return An arraylist of all the defaultvalues
     */
    @RequestMapping("/get_dv_by_category/{sessionId}")
    public ArrayList<String> getDefaultValuesFor(@RequestBody Category category,
                                                 @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return dvs.getValuesFromCategory(category);
        }

        return new ArrayList<>();
    }

    /**
     * Deletes a specified defaultvalue.
     * @param desc The description of the activity
     * @param sessionId The sessionId of the user
     */
    @DeleteMapping("/delete_dv/{sessionId}")
    public void deleteDefaultValue(@RequestBody String desc,
                                   @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            dvs.deleteDefaultValue(desc);
        }
    }

    /**
     * Gets a default value matching the given description.
     * @param desc The description of the activity
     * @param sessionId The sessionId of the user
     * @return the default value that was requested
     */
    @RequestMapping("/get_dv/{sessionId}")
    public DefaultValue getDefaultValue(@RequestBody String desc,
                                        @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return dvs.getDefaultValue(desc);
        }

        return null;
    }

    /**
     * Adds the defaultvalue provided.
     * @param dv The defaultValue to be added
     * @param sessionId The sessionId belonging to the user
     * @return true if adding was successful and false if adding was unsuccessful
     */
    @PostMapping("/add_dv/{sessionId}")
    public boolean addDefaultValue(@RequestBody DefaultValue dv,
                                   @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return dvs.createDefaultValue(dv);
        }

        return false;
    }

    public void setDvs(DefaultValueService dvs) {
        this.dvs = dvs;
    }

    public void setSs(SessionService ss) {
        this.ss = ss;
    }
}
