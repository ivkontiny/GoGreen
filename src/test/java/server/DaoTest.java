package server;

import Services.SessionService;
import database.Dao;
import org.junit.Test;
import pojos.Session;
import pojos.Account;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class DaoTest {

    public Account testuser = new Account("1","1","1","1","1");
    public Session testsession = new Session("1", LocalDateTime.now());
    @Test
    public void existsTest()
    {
        assertFalse(SessionService.sessionExists("1"));
        assertFalse(Dao.userExists("1"));
        Dao.putUser("1",testuser);
        SessionService.putSession("1",testsession);
        assertTrue(SessionService.sessionExists("1"));
        assertTrue(Dao.userExists("1"));
        Dao.removeUser("1");
        SessionService.removeSession("1");
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
