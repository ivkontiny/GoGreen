package server;

import services.SessionService;
import database.Dao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pojos.Session;
import util.SessionIdGenerator;

import java.time.LocalDateTime;


@RestController
public class LoginController {



    /** Trying to log a user in.
     * @param credentials the username and password, concatenated with a :-sign.
     * @return the sessionID of the user if the logging in was successful, null otherwise
     */
    @PostMapping("/login")
    public static String logIn(@RequestBody String credentials) {

        String sessionId = null;
        String[] userlogin = credentials.split(":");
        String username = userlogin[0];
        String password = userlogin[1];
        if (Dao.getAllUsers().containsKey(username)
                && password.equals(Dao.getAllUsers().get(username).getPassword())) {
            sessionId = new SessionIdGenerator().getAlphaNumericString(42);
            SessionService.putSession(sessionId, new Session(username, LocalDateTime.now()));
        }
        return sessionId;
    }

}
