package server;

import java.time.LocalDateTime;

public class Session {

    private String username;
    private LocalDateTime time;

    /** Creatiing a new sessionID.
     * @param username the user for which the sessionID is created
     * @param now the time of the creation of the sessionID
     */
    public Session(String username, LocalDateTime now) {

        this.time = now;
        this.username = username;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


}
