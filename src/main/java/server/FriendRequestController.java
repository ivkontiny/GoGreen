package server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojos.Friendship;
import services.AccountService;
import services.FriendRequestService;
import services.SessionService;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class FriendRequestController {

    private FriendRequestService frs = new FriendRequestService();
    private AccountService as = new AccountService();
    private SessionService ss = new SessionService();

    /**
     * Sends a friend request.
     * @param sessionId the user sending the friend request
     * @param receiver the user receiving the friend request
     * @return true if the request was sent successfully, false otherwise
     */
    @RequestMapping("/request/{sessionId}")
    public boolean sendRequest(@PathVariable("sessionId") String sessionId,
                               @RequestParam(value = "username", defaultValue = "user")
                                       String receiver) throws SQLException {
        if (!ss.sessionExists(sessionId)) {
            return false;
        }
        String sender = ss.getAllSessions().get(sessionId).getUsername();
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
     * @param sessionId of the receiver of the friend request
     * @param sender the sender of the friend request
     * @return true if the friend request was successfully accepted, false otherwise
     */
    @RequestMapping("/accept_request/{sessionId}")
    public boolean acceptRequest(@PathVariable("sessionId") String sessionId,
                                 @RequestParam(value = "username", defaultValue = "user") String sender) throws SQLException {

        if (!ss.sessionExists(sessionId)) {
            return false;
        }

        String receiver = ss.getAllSessions().get(sessionId).getUsername();
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
     * Returns the active friendships of the user.
     * @param sessionId the user whose friendships we are interested in
     * @return an array list containing all friendships of the current user
     */
    @RequestMapping("/active_friendships/{sessionId}")
    public ArrayList<Friendship> getActiveFriendships(@PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return frs.getActiveFriendships(ss.getAllSessions().get(sessionId).getUsername());
        }
        return new ArrayList<Friendship>();
    }

    /**
     * Returns the inactive friendships of the user.
     * @param sessionId the user whose friendships we are interested in
     * @return an array list containing all friendships of the current user
     */
    @RequestMapping("/inactive_friendships/{sessionId}")
    public ArrayList<Friendship> getInactiveFriendships(@PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return frs.getInactiveFriendships(ss.getAllSessions().get(sessionId).getUsername());
        }
        return new ArrayList<Friendship>();
    }


    public void setFrs(FriendRequestService frs) {
        this.frs = frs;
    }

    public void setAs(AccountService as) {
        this.as = as;
    }

    public void setSs(SessionService ss) {
        this.ss = ss;
    }

}
