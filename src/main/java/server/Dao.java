package server;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class Dao {

    private static HashMap<String, String> users;

    static {

        users = new HashMap<String, String>() {{
            put("username1", "password1");
            put("username2", "password2");
            put("username3", "password3");
        }};
    }

    public static HashMap<String,String> getAllUsers() {
        return users;
    }

   // public Greeting getGreetingById(int id) {
     //   return this.greetings.get(id);
    //}


}
