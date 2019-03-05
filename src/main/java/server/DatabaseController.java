package server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

@RestController
public class DatabaseController {

    @RequestMapping("/get_accounts/{username}")
    public Account getAccounts(@PathVariable("username") String username) {
        DatabaseInteraction db = new DatabaseInteraction();

        try {
            return db.getAccount(username);

        } catch(Exception e) {

        }
        return null;
    }
}
