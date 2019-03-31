package server;

import org.springframework.web.bind.annotation.*;
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
                                       String receiver) {
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
    public boolean acceptRequest(
        @PathVariable("sessionId") String sessionId,
        @RequestParam(value = "username", defaultValue = "user") String sender) {

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
        return new ArrayList<>();
    }

    /**
     * Returns the inactive friendships of the user.
     * @param sessionId the user whose friendships we are interested in
     * @return an array list containing all friendships of the current user
     */
    @RequestMapping("/inactive_friendships/{sessionId}")
    public ArrayList<Friendship> getInactiveFriendships(
        @PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return frs.getInactiveFriendships(ss.getAllSessions().get(sessionId).getUsername());
        }
        return new ArrayList<>();
    }
    
    /**
     * Gets the username's of a users friends.
     * @param sessionId The sessionId belonging to the users current session
     * @return all friends of the user provided
     */
    @RequestMapping("/get_friends/{sessionId}")
    public ArrayList<String> getFriends(@PathVariable("sessionId") String sessionId) {
        if (ss.sessionExists(sessionId)) {
            return frs.getFriends(ss.getAllSessions().get(sessionId).getUsername());
        }

        return new ArrayList<>();
    }

    @RequestMapping("/get_match/{sessionId}/{matching}")
    public ArrayList<String> getMatchings(@PathVariable("sessionId") String sessionId ,@PathVariable("matching") String match) {
        if(ss.sessionExists(sessionId)) {
            return frs.getMatchings(match);
        }
        return new ArrayList<>();
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
