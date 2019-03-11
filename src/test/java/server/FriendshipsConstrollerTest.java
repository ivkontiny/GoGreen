package server;

import org.junit.Before;
import org.junit.Test;
import pojos.Friendship;
import services.AccountService;
import services.FriendRequestService;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendshipsConstrollerTest {

    FriendRequestController frc;
    Friendship testfriendship;
    FriendRequestService test1 = mock(FriendRequestService.class);
    AccountService test2 = mock(AccountService.class);
    Friendship test3 = mock(Friendship.class);
    @Before
    public void configure()
    {
        frc = new FriendRequestController();
        testfriendship = new Friendship("from", "to");
        frc.setFrs(test1);
        frc.setAs(test2);
    }
    @Test
    public void sendRequestFailed()
    {
        when(test2.userExists("from")).thenReturn(false);
        assertFalse(frc.sendRequest("from","to"));
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(false);
        assertFalse(frc.sendRequest("from","to"));
        when(test2.userExists("to")).thenReturn(true);
        when(test1.friendshipExists(any(Friendship.class))).thenReturn(true);
        assertFalse(frc.sendRequest("from","to"));
    }
    @Test
    public void sendRequestOK()
    {
        when(test1.friendshipExists(any(Friendship.class))).thenReturn(false);
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(true);
        when(test1.sendRequest(any(Friendship.class))).thenReturn(true);
        assertTrue(frc.sendRequest("from","to"));
    }
    @Test
    public void acceptRequestFailed()
    {
        when(test2.userExists("from")).thenReturn(false);
        assertFalse(frc.acceptRequest("from","to"));
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(false);
        assertFalse(frc.acceptRequest("from","to"));
        when(test1.friendshipExists(any(Friendship.class))).thenReturn(false);
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(true);
        when(test1.acceptRequest(any(Friendship.class))).thenReturn(false);
        assertFalse(frc.acceptRequest("from","to"));
    }

    @Test
    public void acceptRequestOk()
    {
        when(test1.friendshipExists(any(Friendship.class))).thenReturn(true);
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(true);
        when(test1.acceptRequest(any(Friendship.class))).thenReturn(true);
        assertTrue(frc.acceptRequest("from","to"));
    }

    @Test
    public void getFriendships()
    {
        when(test1.getFriendships(any(String.class))).thenReturn(new ArrayList<Friendship>());
        assertTrue(frc.getFriendships("user").size() == 0);
    }
}
