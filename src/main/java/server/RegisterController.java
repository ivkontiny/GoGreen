package server;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import util.Account;


@RestController
public class RegisterController {


    //Post Request for registering new user
    @PostMapping("/register")
    static Account registerUser(@RequestParam(value = "username",
            defaultValue = "user") String username, @RequestBody Account newuser) {
        if (Dao.getAllUsers().containsKey(username)) {
            newuser = null;
        } else {
            Dao.putUser(newuser.getUsername(),newuser);
        }
        return newuser;
    }



}