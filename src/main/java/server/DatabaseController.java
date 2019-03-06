package server;

import database.Dao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojos.Account;

@RestController
public class DatabaseController {

    @RequestMapping("/get_accounts/{username}")
    public Account getAccounts(@PathVariable("username") String username) {
        Dao db = new Dao();

        try {
            return db.getAccount(username);

        } catch(Exception e) {

        }
        return null;
    }
}
