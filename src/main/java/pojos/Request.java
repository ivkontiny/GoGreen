package pojos;

import client.ConnectFriends;
import javafx.scene.control.Button;

public class Request {
    String username;
    Button accept;
    Button reject;

    public Request(String user, Button accept, Button reject) {
        this.username = user;
        this.accept = accept;
        this.reject = reject;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Button getAccept() {
        return accept;
    }

    public void setAccept(Button accept) {
        this.accept = accept;
    }

    public Button getReject() {
        return reject;
    }

    public void setReject(Button reject) {
        this.reject = reject;
    }
}
