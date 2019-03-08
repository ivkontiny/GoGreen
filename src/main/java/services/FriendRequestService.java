package services;

import database.FriendshipDao;
import pojos.Friendship;

import java.util.ArrayList;

public class FriendRequestService {

    FriendshipDao db = new FriendshipDao();

    public boolean friendshipExists(String sender, String receiver)
    {
        Friendship friendship = new Friendship(sender,receiver);
        return db.friendshipExists(friendship);
    }

    public boolean sendRequest(String requester, String receiver)
    {
        Friendship friendship = new Friendship(requester,receiver);
        return db.sendRequest(friendship);
    }
    public ArrayList<Friendship> getFriendships(String user)
    {
        return db.getFriendships(user);
    }
}
