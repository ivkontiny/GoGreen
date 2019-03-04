package server;

import org.junit.Test;
import util.User;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class DaoTest {

    public User testuser = new User("1","1","1","1","1");
    public Session testsession = new Session("1", LocalDateTime.now());
    @Test
    public void existsTest()
    {
        assertFalse(Dao.sessionExists("1"));
        assertFalse(Dao.userExists("1"));
        Dao.putUser("1",testuser);
        Dao.putSession("1",testsession);
        assertTrue(Dao.sessionExists("1"));
        assertTrue(Dao.userExists("1"));
        Dao.removeUser("1");
        Dao.removeSession("1");
    }

    @Test
    public void RemovePutTest()
    {
        Dao.putUser("1",testuser);
        assertTrue(Dao.userExists("1"));
        Dao.removeUser("1");
        assertFalse(Dao.userExists("1"));
    }
}
