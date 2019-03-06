package Services;

import pojos.Session;

import java.util.HashMap;

public class SessionService {

    private static HashMap<String, Session> activesessions;

    static
    {
        activesessions = new HashMap<>();
    }

    public static boolean sessionExists(String key) {
        return SessionService.getAllSessions().containsKey(key);
    }

    public static void putSession(String sessionId, Session newsession) {
        activesessions.put(sessionId, newsession);
    }


    public  static HashMap<String,Session> getAllSessions() {
        return activesessions;
    }

    public static void removeSession(String key) {
        activesessions.remove(key);
    }

}
