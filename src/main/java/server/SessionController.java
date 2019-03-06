package server;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {



    /** Check whether the sessionID exists.
     * @param sessionId the sessionID to check for
     * @return the username of the holder of the sessionID
     */
    @GetMapping("/user/{sessionID}")
    public static String getusers(@PathVariable("sessionID") String sessionId ) {

        String username = null;
        //return sessionID;
        if (SessionService.sessionExists(sessionId)) {
            username = SessionService.getAllSessions().get(sessionId).getUsername();
        }


        return username;
    }
}
