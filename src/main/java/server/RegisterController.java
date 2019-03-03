package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController {

    @Autowired
    private LoginService greetingService;


    //Post Request for registering new user
    @PostMapping("/register")
    static User newuser(@RequestParam(value="username", defaultValue = "user") String username,@RequestBody User newuser) {
        if(Dao.getAllUsers().containsKey(username)) newuser = null;
        else
        {
            Dao.putuser(newuser.getUsername(),newuser);
        }
        return newuser;
    }



}