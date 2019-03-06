package server;

import Services.LoginService;
import Services.SessionService;
import database.Dao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pojos.Session;
import util.SessionIdGenerator;

import java.time.LocalDateTime;


@RestController
public class LoginController {

    LoginService ls = new LoginService();

    /** Trying to log a user in.
     * @param credentials the username and password, concatenated with a :-sign.
     * @return the sessionID of the user if the logging in was successful, null otherwise
     */
    @PostMapping("/login")
    public String logIn(@RequestBody String credentials) {

        String sessionId = null;
        String[] userlogin = credentials.split(":");
        String username = userlogin[0];
        String password = userlogin[1];
        if (ls.checkLogin(username, password)) {
            sessionId = new SessionIdGenerator().getAlphaNumericString(42);
        }
        return sessionId;
    }

}
