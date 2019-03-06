package server;

import database.AccountDao;
import org.springframework.web.bind.annotation.*;
import pojos.Account;
import services.AccountService;
import util.SessionIdGenerator;

@RestController
public class AccountController {

    AccountService ls = new AccountService();

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

    @RequestMapping("/get_accounts/{username}")
    public Account getAccounts(@PathVariable("username") String username) {
        AccountDao db = new AccountDao();

        try {
            return db.getAccount(username);

        } catch(Exception e) {

        }
        return null;
    }

    @PostMapping("/register")
    public boolean registerUser(@RequestParam(value = "username",
            defaultValue = "user") String username, @RequestBody Account newuser) {
        return ls.createAccount(newuser);
    }
}
