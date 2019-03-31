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

    private AccountService ls = new AccountService();
    private AccountDao db = new AccountDao();
    private SessionService ss = new SessionService();

    /**
     * Trying to log a user in.
     *
     * @param credentials the username and password, concatenated with a :-sign.
     * @return the sessionID of the user if the logging in was successful, null otherwise
     */
    @PostMapping("/login")
    public String logIn(@RequestBody String credentials) {

        String sessionId = null;
        String[] userlogin = credentials.split(":", 0);
        String username = userlogin[0];
        String password = userlogin[1];

        if (ls.checkLogin(username, password)) {
            sessionId = new SessionIdGenerator().getAlphaNumericString(42);
        }
        ss.putSession(sessionId, new Session(username, LocalDateTime.now()));
        return sessionId;
    }


    @RequestMapping("/get_account/{sessionId}")
    public Account getAccounts(@PathVariable("sessionId") String sessionId) {
        if(ss.sessionExists(sessionId)){
            String username = ss.getAllSessions().get(sessionId).getUsername();
            return ls.getAccount(username);
        }
        return new Account();
    }


    @PostMapping("/register")
    public boolean registerUser(@RequestParam(value = "username",
            defaultValue = "user") String username, @RequestBody Account newuser) {
        System.out.println(newuser.getPassword());
        return ls.createAccount(newuser);
    }


    @RequestMapping("/set_energy/{sessionId}")
    public boolean setEnergy(@RequestBody int energy,
                             @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            String user = ss.getAllSessions().get(sessionId).getUsername();
            return ls.setEnergy(user, energy);
        }

        return false;
    }

    public void setLs(AccountService ls) {
        this.ls = ls;
    }

    public void setDb(AccountDao db) {
        this.db = db;
    }

    public void setSs(SessionService ss) {
        this.ss = ss;
    }

}
