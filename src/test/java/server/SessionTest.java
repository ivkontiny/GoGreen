package server;

import org.junit.Before;
import org.junit.Test;
import util.User;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

public class SessionTest {

    User testuser;
    Session testsession;

    @Before
    public void configure()
    {
        testsession = new Session("user1", LocalDateTime.now());
        testuser = new User("1","1","1","1","1");
    }
    @Test
    public void SessionConstructor()
    {
        LocalDateTime testtime = LocalDateTime.now();
        Session testsession = new Session("1",testtime);
        assertTrue(testsession.getUsername().equals("1"));
        assertTrue(testtime.equals(testsession.getTime()));
    }

    @Test
    public void SessiongetUsername()
    {
        LocalDateTime testtime = LocalDateTime.now();
        Session testsession = new Session("1",testtime);
        assertTrue(testsession.getUsername().equals("1"));
    }

    @Test
    public void SessiongetTime()
    {
        LocalDateTime testtime = LocalDateTime.now();
        Session testsession = new Session("1",testtime);
        assertTrue(testtime.equals(testsession.getTime()));
    }

    @Test
    public void SessionsetTime()
    {
        LocalDateTime testtime = LocalDateTime.now();
        Session testsession = new Session("1",testtime);
        testtime = LocalDateTime.now();
        testsession.setTime(testtime);
        assertTrue(testsession.getTime().equals(testtime));
    }

    @Test
    public void SessionsetUsername()
    {
        LocalDateTime testtime = LocalDateTime.now();
        Session testsession = new Session("1",testtime);
        testsession.setUsername("2");
        assertTrue(testsession.getUsername().equals("2"));
    }

    @Test
    public void SessionControl()
    {
        Dao.putSession("1", testsession);
        assertTrue(SessionController.getusers("1").equals("user1"));
        Dao.removeSession("1");
        assertNull(SessionController.getusers("1"));
    }
}
