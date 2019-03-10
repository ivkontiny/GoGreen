package server;

import database.AccountDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojos.Account;
import pojos.Session;
import services.AccountService;
import services.SessionService;
import util.SessionIdGenerator;

import java.time.LocalDateTime;

@RestController
public class AccountController {

    AccountService ls = new AccountService();
    AccountDao db = new AccountDao();
    SessionService ss = new SessionService();

    /**
     * Trying to log a user in.
     *
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
        ss.putSession(sessionId, new Session(username, LocalDateTime.now()));
        return sessionId;
    }


    @RequestMapping("/get_account/{username}")
    public Account getAccounts(@PathVariable("username") String username) {
        return db.getAccount(username);
    }


    @PostMapping("/register")
    public boolean registerUser(@RequestParam(value = "username",
            defaultValue = "user") String username, @RequestBody Account newuser) {
        return ls.createAccount(newuser);
    }
}
