package services;

import pojos.Session;

import java.util.HashMap;

public class SessionService {

    private static HashMap<String, Session> activesessions;

    static {
        activesessions = new HashMap<>();
    }

    public boolean sessionExists(String key) {
        return getAllSessions().containsKey(key);
    }

    public void putSession(String sessionId, Session newsession) {
        activesessions.put(sessionId, newsession);
    }


    public HashMap<String,Session> getAllSessions() {
        return activesessions;
    }

    public static void removeSession(String key) {
        activesessions.remove(key);
    }

}
