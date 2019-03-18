package server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import pojos.Friendship;
import pojos.Session;
import services.AccountService;
import services.FriendRequestService;
import services.SessionService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendshipsConstrollerTest {

    FriendRequestController frc;
    Friendship testfriendship;
    FriendRequestService test1 = mock(FriendRequestService.class);
    AccountService test2 = mock(AccountService.class);
    SessionService ss = mock(SessionService.class);
    Friendship test3 = mock(Friendship.class);

    @Before
    public void configure()
    {
        frc = new FriendRequestController();
        testfriendship = new Friendship("from", "to");
        frc.setFrs(test1);
        frc.setAs(test2);
        frc.setSs(ss);
        when(ss.sessionExists("from")).thenReturn(true);
        HashMap<String,Session> test = new HashMap<>();
        test.put("from",new Session("from", LocalDateTime.now()));
        when(ss.getAllSessions()).thenReturn(test);
    }
    @Test
    public void testSendRequestFailed() throws SQLException {
        when(test2.userExists("from")).thenReturn(false);
        assertFalse(frc.sendRequest("from","to"));
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(false);
        assertFalse(frc.sendRequest("from","to"));
        when(test2.userExists("to")).thenReturn(true);
        when(test1.friendshipExists(Matchers.any(Friendship.class))).thenReturn(true);
        assertFalse(frc.sendRequest("from","to"));
    }

    @Test
    public void testSendRequestOK() throws SQLException
    {
        when(test1.friendshipExists(Matchers.any(Friendship.class))).thenReturn(false);
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(true);
        when(test1.sendRequest(Matchers.any(Friendship.class))).thenReturn(true);
        assertTrue(frc.sendRequest("from","to"));
    }

    @Test
    public void testAcceptRequestFailed() throws SQLException
    {
        when(test2.userExists("from")).thenReturn(false);
        assertFalse(frc.acceptRequest("from","to"));
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(false);
        assertFalse(frc.acceptRequest("from","to"));
        when(test1.friendshipExists(Matchers.any(Friendship.class))).thenReturn(false);
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(true);
        when(test1.acceptRequest(Matchers.any(Friendship.class))).thenReturn(false);
        assertFalse(frc.acceptRequest("from","to"));
    }

    @Test
    public void testAcceptRequestOk () throws SQLException {
        when(test2.userExists("from")).thenReturn(true);
        when(test2.userExists("to")).thenReturn(true);
        when(test1.acceptRequest(Matchers.any(Friendship.class))).thenReturn(true);
        when(test1.friendshipExists(Matchers.any(Friendship.class))).thenReturn(true);
        assertTrue(frc.acceptRequest("from","to"));
    }

    @Test
    public void testGetFriendships() {
        when(test1.getActiveFriendships(Matchers.any(String.class))).thenReturn(new ArrayList<Friendship>());
        assertTrue(frc.getActiveFriendships("user").size() == 0);
    }

    @Test
    public void noSessionFound() throws SQLException
    {
        assertFalse(frc.acceptRequest("to","to"));
        assertFalse(frc.sendRequest("to","from"));
        assertEquals(0,frc.getActiveFriendships("to").size());
        assertEquals(0,frc.getActiveFriendships("to").size());
        assertEquals(0,frc.getInactiveFriendships("to").size());

    }
}
