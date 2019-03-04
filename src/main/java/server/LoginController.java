package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;


@RestController
public class LoginController {

    @Autowired
    private LoginService greetingService;


    @RequestMapping("/login")
    public Login logingin(@RequestParam(value="username", defaultValue = "user") String username,
                          @RequestParam(value="password", defaultValue="pass") String password) {

        Login actualUser;
        if(Dao.getAllUsers().containsKey(username) && password.equals(Dao.getAllUsers().get(username))) actualUser =  new Login(username,password,true);
        else
        {
            actualUser = new Login(username,password,false);
        }
        return actualUser;
    }

}