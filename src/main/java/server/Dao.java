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
                User adduser = new User("u1", "u1", String.valueOf("user".hashCode()), "u1", "u1");
                put("username1",adduser);
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

    public static boolean userExists(String key) {
        return Dao.getAllUsers().containsKey(key);
    }

    public static boolean sessionExists(String key) {
        return Dao.getAllSessions().containsKey(key);
    }

    public static void putSession(String sessionId, Session newsession) {
        activesessions.put(sessionId,newsession);
    }

    public static void removeUser(String key) {
        users.remove(key);
    }

    public static void removeSession(String key) {
        activesessions.remove(key);
    }


}
