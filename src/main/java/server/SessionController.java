package server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {


    @Autowired
    private LoginService loginService;

    @GetMapping("/user/{sessionID}")
    public String getusers(@PathVariable("sessionID") String sessionID ){

        //return sessionID;
        String username = Dao.getAllSessions().get(sessionID).getUsername();
        return Dao.getAllUsers().get(sessionID).getMail();
    }
}
