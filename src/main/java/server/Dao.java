package server;

import org.springframework.stereotype.Repository;
import util.User;

import java.util.HashMap;

@Repository
public class Dao {

    private static HashMap<String, User> users;
    private static HashMap<String,Session> activesessions;

    static {

        activesessions = new HashMap<String,Session>() {
            {
                //put("1", new Session("username1", LocalDateTime.now()));
            }
        };
        users = new HashMap<String, User>() {
            {
                put("username1", new User("username1", "use1@mail.com", String.valueOf("user".hashCode()), "user", "user"));
                put("username2", new User("username2", "user2@mail.com", String.valueOf("user".hashCode()), "user", "user"));
                put("username3", new User("username3", "user3@mail.com", String.valueOf("user".hashCode()), "user", "user"));
            }
        };
    }

    public  static HashMap<String,User> getAllUsers() {
        return users;
    }


    public  static HashMap<String,Session> getAllSessions() {
        return activesessions;
    }


    public static void putUser(String key, User value) {
        users.put(key,value);
    }

    public static boolean userExists(String key) { return Dao.getAllUsers().containsKey(key); }

    public static boolean sessionExists(String key) { return Dao.getAllSessions().containsKey(key); }

    public static void putSession(String sessionID, Session newsession) {
        activesessions.put(sessionID,newsession);
    }

    public static void removeUser(String key)
    {
        users.remove(key);
    }

    public static void removeSession(String key)
    {
        activesessions.remove(key);
    }


}
