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
     * Returns all active friendships of a certain user.
     * @param user the user whose friendships we want to know
     * @return a list containing all the friendships of the user
     */
    public ArrayList<Friendship> getActiveFriendships(String user) {
        try {
            ArrayList<Friendship> active = new ArrayList<>();
            ArrayList<Friendship> all = db.getFriendships(user);
            for (Friendship search : all) {
                if (search.getStatus()) {
                    active.add(search);
                }
            }
            return active;
        } catch (SQLException e) {
            return new ArrayList<Friendship>();
        }
    }

    /**
     * Returns all inactive friendships of a certain user.
     * @param user the user whose friendships we want to know
     * @return a list containing all the friendships of the user
     */
    public ArrayList<Friendship> getInactiveFriendships(String user) {
        try {
            ArrayList<Friendship> inactive = new ArrayList<>();
            ArrayList<Friendship> all = db.getFriendships(user);
            for (Friendship search : all) {
                if (search.getStatus()) {
                    inactive.add(search);
                }
            }
        } catch (SQLException e) {
            return new ArrayList<Friendship>();
        }
        return new ArrayList<Friendship>();
    }


    public ArrayList<String> getFriends (String user) {
        try {
            ArrayList<Friendship> friendships = db.getFriendships(user);
            ArrayList<String> friends = new ArrayList<>();
            for(Friendship fr : friendships) {
                if (fr.getStatus()) {
                    friends.add(fr.getReceiver().equals(user) ? fr.getSender()
                            : fr.getReceiver());
                }
            }

            return friends;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}
