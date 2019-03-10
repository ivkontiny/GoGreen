package services;

import database.FriendshipDao;
import pojos.Friendship;

import java.util.ArrayList;

public class FriendRequestService {

    FriendshipDao db = new FriendshipDao();

    public boolean friendshipExists(Friendship friendship)
    {
        //Friendship friendship = new Friendship(sender,receiver);
        return db.friendshipExists(friendship);
    }

    public boolean sendRequest(Friendship friendship)
    {
        //Friendship friendship = new Friendship(sender,receiver);
        return db.sendRequest(friendship);
    }

    public boolean acceptRequest(Friendship friendship)
    {
        //Friendship friendship = new Friendship(sender, receiver);
        return db.acceptRequest(friendship);
    }

    public void setDb(FriendshipDao fd)
    {
        this.db = fd;
    }
    public ArrayList<Friendship> getFriendships(String user)
    {
        return db.getFriendships(user);
    }
}
