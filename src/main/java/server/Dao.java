package server;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class Dao {

    private static HashMap<String, String> users;

    static {

        users = new HashMap<String, String>() {{
            put("user1", "password1");
            put("user2", "password2");
            put("user3", "password3");
        }};
    }

    public Collection<String> getAllUsers() {
        return this.users.keySet();
    }

   // public Greeting getGreetingById(int id) {
     //   return this.greetings.get(id);
    //}


}
