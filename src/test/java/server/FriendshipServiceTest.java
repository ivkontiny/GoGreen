package server;

import database.FriendshipDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Friendship;
import services.FriendRequestService;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendshipServiceTest {

    private FriendRequestService frs = new FriendRequestService();
    private FriendshipDao test = mock(FriendshipDao.class);
    private Friendship testfriendship = new Friendship("from","to");

    @Before
    public void configure()
    {
        frs.setDb(test);
    }

    @Test
    public void testFriendshipExists() throws SQLException
    {
        when(test.friendshipExists(testfriendship)).thenReturn(true);
        when(test.acceptRequest(testfriendship)).thenReturn(true);
        assertTrue(frs.friendshipExists(testfriendship));
        assertTrue(frs.acceptRequest(testfriendship));
        when(test.friendshipExists(testfriendship)).thenReturn(false);
        when(test.acceptRequest(testfriendship)).thenReturn(false);
        assertFalse(frs.friendshipExists(testfriendship));
        assertFalse(frs.acceptRequest(testfriendship));
    }

    @Test
    public void testFriendshipSend() throws SQLException
    {
        when(test.sendRequest(testfriendship)).thenReturn(true);
        when(test.getFriendships("test")).thenReturn(null);
        assertTrue(frs.sendRequest(testfriendship));
        assertNull(frs.getFriendships("test"));
        when(test.sendRequest(testfriendship)).thenReturn(false);
        assertFalse(frs.sendRequest(testfriendship));
    }
    @Test
    public void testHandleException() throws  SQLException
    {
        when(test.sendRequest(testfriendship)).thenThrow(new SQLException());
        when(test.getFriendships("test")).thenThrow(new SQLException());
        when(test.acceptRequest(testfriendship)).thenThrow(new SQLException());
        assertFalse(frs.sendRequest(testfriendship));
        assertNull(frs.getFriendships("test"));
        assertFalse(frs.acceptRequest(testfriendship));
    }
}
