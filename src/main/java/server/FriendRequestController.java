package server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojos.Friendship;
import services.AccountService;
import services.FriendRequestService;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class FriendRequestController {

    private FriendRequestService frs = new FriendRequestService();
    private AccountService as = new AccountService();

    /**
     * Sends a friend request.
     * @param sender the user sending the friend request
     * @param receiver the user receiving the friend request
     * @return true if the request was sent successfully, false otherwise
     */
    @RequestMapping("/request/{username}")
    public boolean sendRequest(@PathVariable("username") String sender,
                               @RequestParam(value = "username", defaultValue = "user")
                                       String receiver) throws SQLException {
        //return receiver;
        if (!as.userExists(sender)) {
            return false;
        }

        if (!as.userExists(receiver)) {
            return false;
        }

        if (frs.friendshipExists(new Friendship(sender,receiver))) {
            return false;
        }
        //if(frs.friendshipExists(sender,receiver)) return false;
        return frs.sendRequest(new Friendship(sender,receiver));
    }


    /**
     * User accepts a friend request.
     * @param receiver the receiver of the friend request
     * @param sender the sender of the friend request
     * @return true if the friend request was successfully accepted, false otherwise
     */
    @RequestMapping("/accept_request/{username}")
    public boolean acceptRequest(@PathVariable("username") String receiver,
                                 @RequestParam(value = "username", defaultValue = "user")
                                         String sender) throws SQLException {
        //return receiver;
        if (!as.userExists(receiver)) {
            return false;
        }

        if (!as.userExists(sender)) {
            return false;
        }

        if (!frs.friendshipExists(new Friendship(sender,receiver))) {
            return false;
        }

        return frs.acceptRequest(new Friendship(sender,receiver));
    }

    /**
     * Returns the friendships of the user.
     * @param user the user whose friendships we are interested in
     * @return an array list containing all friendships of the current user
     */
    @RequestMapping("/friendships/{username}")
    public ArrayList<Friendship> getFriendships(@PathVariable("username") String user) {
        return frs.getFriendships(user);
    }

    public void setFrs(FriendRequestService frs)
    {
        this.frs = frs;
    }

    public void setAs(AccountService as)
    {
        this.as = as;
    }

}
