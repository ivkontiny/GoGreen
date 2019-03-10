package server;

import database.FriendshipDao;
import org.junit.Test;
import pojos.Friendship;
import services.FriendRequestService;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendshipsTest {

    private FriendRequestService frs = new FriendRequestService();
    @Test
    public void friendshipExists()
    {
        FriendshipDao test = mock(FriendshipDao.class);
        Friendship testfriendship = new Friendship("from","to");
        when(test.friendshipExists(testfriendship)).thenReturn(true);
        when(test.acceptRequest(testfriendship)).thenReturn(true);
        frs.setDb(test);
        assertTrue(frs.friendshipExists(testfriendship));
        assertTrue(frs.acceptRequest(testfriendship));
        when(test.friendshipExists(testfriendship)).thenReturn(false);
        when(test.acceptRequest(testfriendship)).thenReturn(false);
        assertFalse(frs.friendshipExists(testfriendship));
        assertFalse(frs.acceptRequest(testfriendship));
    }
    @Test
    public void friendshipSend()
    {
        FriendshipDao test = mock(FriendshipDao.class);
        Friendship testfriendship = new Friendship("from","to");
        when(test.sendRequest(testfriendship)).thenReturn(true);
        when(test.getFriendships("test")).thenReturn(null);
        frs.setDb(test);
        assertTrue(frs.sendRequest(testfriendship));
        assertNull(frs.getFriendships("test"));
        when(test.sendRequest(testfriendship)).thenReturn(false);
        assertFalse(frs.sendRequest(testfriendship));
    }
}
