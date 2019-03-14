package services;

import database.FriendshipDao;
import pojos.Friendship;

import java.sql.SQLException;
import java.util.ArrayList;

public class FriendRequestService {

    FriendshipDao db = new FriendshipDao();

    public boolean friendshipExists(Friendship friendship) {
        try {
            return db.friendshipExists(friendship);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean sendRequest(Friendship friendship) {
        try {
            return db.sendRequest(friendship);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean acceptRequest(Friendship friendship) {
        try {
            return db.acceptRequest(friendship);
        } catch (SQLException e) {
            return false;
        }
    }

    public void setDb(FriendshipDao fd) {
        this.db = fd;
    }

    public ArrayList<Friendship> getFriendships(String user) {
        try {
            return db.getFriendships(user);
        } catch (SQLException e) {
            return null;
        }
    }
}
