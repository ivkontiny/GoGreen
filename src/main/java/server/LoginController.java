package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    /** Trying to log a user in.
     * @param credentials the username and password, concatenated with a :-sign.
     * @return the sessionID of the user if the logging in was successful, null otherwise
     */
    @PostMapping("/login")
    public static String LogIN(@RequestBody String credentials) {

        String sessionID = null;
        String[] userlogin = credentials.split(":");
        String username = userlogin[0];
        String password = userlogin[1];
        if (Dao.getAllUsers().containsKey(username)
                && password.equals(Dao.getAllUsers().get(username).getPassword())) {
            sessionID = new SessionIDGenerator().getAlphaNumericString(42);
            Dao.putSession(sessionID, new Session(username, LocalDateTime.now()));
        }
        return sessionID;
    }

}
