package server;

import database.FriendshipDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Friendship;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class FriendshipDaoTest {

    private FriendshipDao fd = new FriendshipDao();
    private Friendship testfriendships = new Friendship("test", "bob");
    @Before
    public void configure()
    {
        fd.removeFriendship(testfriendships);
    }
    @Test
    public void existsFriendship()
    {
        fd.sendRequest(testfriendships);
        assertTrue(fd.friendshipExists(testfriendships));
        fd.removeFriendship(testfriendships);
        assertFalse(fd.friendshipExists(testfriendships));
        assertNotNull(fd.getFriendships("test"));
    }

    @Test
    public void sendAcceptFriendship()
    {
        Friendship testfriendships = new Friendship("test", "bob");
        assertTrue(fd.sendRequest(testfriendships));
        assertTrue(fd.acceptRequest(testfriendships));
        assertFalse(fd.sendRequest(testfriendships));
        fd.removeFriendship(testfriendships);
        assertFalse(fd.acceptRequest(testfriendships));
    }
}
