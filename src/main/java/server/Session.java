package server;

import java.time.LocalDateTime;

public class Session {

    private String username;
    private LocalDateTime time;

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
