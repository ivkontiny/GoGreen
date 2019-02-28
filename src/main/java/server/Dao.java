package server;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class Dao {

    private static HashMap<String,User> users;

    static {

        users = new HashMap<String, User>() {
            {
                put("username1", new User("username1", "user@mail.com", String.valueOf("password1".hashCode()), "user", "user"));
            }
        };
    }

    public static HashMap<String,User> getAllUsers() {
        return users;
    }


    public static void putuser(String key, User value)
    {
        users.put(key,value);
    }
   // public Greeting getGreetingById(int id) {
     //   return this.greetings.get(id);
    //}


}
