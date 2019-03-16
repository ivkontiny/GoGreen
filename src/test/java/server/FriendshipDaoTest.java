package server;

import database.AccountDao;
import database.FriendshipDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Account;
import pojos.Friendship;

import javax.validation.constraints.AssertTrue;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendshipDaoTest {

    private FriendshipDao fd = new FriendshipDao();
    private AccountDao ad = new AccountDao();
    private Friendship testfriendships = new Friendship("from", "to");
    private Account testaccount1 = new Account("from","from","from","from","from");
    private Account testaccount2 = new Account("to","to","to","to","to");
    @Before
    public void configure()
    {
        ad.changeDatabase("test");
        fd.changeDatabase("test");
        fd.setAd(ad);
    }

    @Test
    public void testExistsFriendship() throws SQLException
    {
        assertFalse(fd.sendRequest(testfriendships));
        assertFalse(fd.friendshipExists(testfriendships));
        ad.createAccount(testaccount1);
        ad.createAccount(testaccount2);
        fd.sendRequest(testfriendships);
        assertTrue(fd.friendshipExists(new Friendship("to","from")));
        assertTrue(fd.friendshipExists(testfriendships));
        fd.removeFriendship(testfriendships);
        assertFalse(fd.friendshipExists(testfriendships));
        assertNotNull(fd.getFriendships("from"));
    }

    @Test
    public void testSendAcceptFriendship() throws SQLException
    {

        assertTrue(fd.sendRequest(testfriendships));
        assertTrue(fd.acceptRequest(testfriendships));
        assertFalse(fd.sendRequest(testfriendships));
        fd.removeFriendship(testfriendships);
        assertFalse(fd.acceptRequest(testfriendships));
        ad.deleteAccount(testaccount1);
        ad.deleteAccount(testaccount2);
    }
}
