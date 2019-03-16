package services;

import org.apache.tomcat.jni.Local;
import pojos.Session;

import java.time.LocalDateTime;
import java.util.HashMap;

public class SessionService {

    private static HashMap<String, Session> activesessions;

    static {
        activesessions = new HashMap<>();
    }

    public boolean sessionExists(String key) {
        if(getAllSessions().containsKey(key))
        {
            LocalDateTime time1 = getAllSessions().get(key).getTime();
            LocalDateTime time2 = LocalDateTime.now();
            if(time1.isBefore(time2.minusHours(1)))
            {
                activesessions.remove(key);
                return false;
            }
        }
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
