package server;

import database.AccountDao;
import services.SessionService;
import org.junit.Test;
import pojos.Session;
import pojos.Account;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class AccountDaoTest {

    public Account testuser = new Account("1","1","1","1","1");
    public Session testsession = new Session("1", LocalDateTime.now());
    @Test
    public void existsTest()
    {
        assertFalse(SessionService.sessionExists("1"));
        assertFalse(AccountDao.userExists("1"));
        AccountDao.putUser("1",testuser);
        SessionService.putSession("1",testsession);
        assertTrue(SessionService.sessionExists("1"));
        assertTrue(AccountDao.userExists("1"));
        AccountDao.removeUser("1");
        SessionService.removeSession("1");
    }

    @Test
    public void RemovePutTest()
    {
        AccountDao.putUser("1",testuser);
        assertTrue(AccountDao.userExists("1"));
        AccountDao.removeUser("1");
        assertFalse(AccountDao.userExists("1"));
    }
}
