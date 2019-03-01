package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public String logingin(@RequestBody String credentials) {

        String sessionID = null;
        String[] userlogin = credentials.split(":");
        String username = userlogin[0];
        String password = userlogin[1];
        if(Dao.getAllUsers().containsKey(username) && password.equals(Dao.getAllUsers().get(username).getPassword())) {
            sessionID = SessionIDGenerator.getAlphaNumericString(42);
            Dao.putsession(sessionID, new Session(username, LocalDateTime.now()));
        }
        return sessionID;
    }

}
