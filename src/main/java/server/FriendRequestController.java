package server;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojos.Friendship;
import services.AccountService;
import services.FriendRequestService;

import java.util.ArrayList;

@RestController
public class FriendRequestController {

    FriendRequestService frs = new FriendRequestService();
    AccountService as = new AccountService();

    @RequestMapping("/request/{username}")
    public boolean sendRequest(@PathVariable("username") String sender,@RequestParam(value = "username",
            defaultValue = "user") String receiver)
    {
        //return receiver;
        if(!as.userExists(sender)) return false;
        if(!as.userExists(receiver)) return false;
        if(frs.friendshipExists(sender,receiver)) return false;
        //if(frs.friendshipExists(sender,receiver)) return false;
        return frs.sendRequest(sender,receiver);
    }

    @RequestMapping("/accept_request/{username}")
    public boolean acceptRequest(@PathVariable("username") String receiver,@RequestParam(value = "username",
            defaultValue = "user") String sender)
    {
        //return receiver;
        if(!as.userExists(receiver)) return false;
        if(!as.userExists(sender)) return false;
        if(!frs.friendshipExists(sender,receiver)) return false;
        return frs.acceptRequest(sender,receiver);
    }

    @RequestMapping("/friendships/{username}")
    public ArrayList<Friendship> getFriendships(@PathVariable("username") String user)
    {
        return frs.getFriendships(user);
    }

}
