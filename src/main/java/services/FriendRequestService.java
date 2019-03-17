package services;

import database.FriendshipDao;
import pojos.Friendship;

import java.sql.SQLException;
import java.util.ArrayList;

public class FriendRequestService {

    FriendshipDao db = new FriendshipDao();

    /**
     * Returns whether a friendship exists.
     * @param friendship the friendship we want to check if exists
     * @return true if the friendship exists, false otherwise
     */
    public boolean friendshipExists(Friendship friendship) {
        try {
            return db.friendshipExists(friendship);
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Send a friend request.
     * @param friendship the friend request represented as a Friendship object
     * @return true if the friend request was sent successfully, false otherwise
     */
    public boolean sendRequest(Friendship friendship) {
        try {
            return db.sendRequest(friendship);
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Accept a friend request.
     * @param friendship the friend request to be accepted represented as a Friendship object
     * @return true if the friend request was successfully accepted, false otherwise
     */
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

    /**
     * Returns all friendships of a certain user.
     * @param user the user whose friendships we want to know
     * @return a list containing all the friendships of the user
     */
    public ArrayList<Friendship> getFriendships(String user) {
        try {
            return db.getFriendships(user);
        } catch (SQLException e) {
            return null;
        }
    }
}
