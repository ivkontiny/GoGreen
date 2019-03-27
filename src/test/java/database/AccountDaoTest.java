package database;

import database.AccountDao;
import database.ActivityDao;
import database.FriendshipDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.Account;
import pojos.Friendship;

import javax.validation.constraints.AssertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class AccountDaoTest {


    private AccountDao accountDao = new AccountDao();
    private FriendshipDao friendshipDao;
    private Account newaccount;
    private ActivityDao activityDao;

    @Before
    public void configure() {
        accountDao.changeDatabase("test");
        newaccount = new Account("user_test", "user", "user", "user", "user");
    }

    @Test
    public void testCreateExistsAccount ()throws SQLException
    {
        accountDao.deleteAccount(newaccount);
        assertFalse(accountDao.exists("user_test"));
        assertFalse(accountDao.updatePoints("user_test",100));
        assertTrue(accountDao.createAccount(newaccount));
        assertFalse(accountDao.createAccount(newaccount));
        assertTrue(accountDao.exists("user_test"));
        accountDao.deleteAccount(newaccount);

    }

    @Test
    public void testGetAccount() throws SQLException
    {
        assertNull(accountDao.getAccount("user_test"));
        accountDao.createAccount(newaccount);
        assertNotNull(accountDao.getAccount("user_test"));
        accountDao.deleteAccount(newaccount);

    }


    @Test
    public void testUpdatePoints() throws SQLException {
        assertTrue(accountDao.createAccount(newaccount));
        assertTrue(accountDao.updatePoints(newaccount.getUsername(), 100));
        accountDao.deleteAccount(newaccount);
    }


    @Test
    public void testSetPanels() throws SQLException {
        assertFalse(accountDao.setPanels(newaccount.getUsername(), 2));
        assertTrue(accountDao.createAccount(newaccount));
        assertTrue(accountDao.setPanels(newaccount.getUsername(), 2));
        accountDao.deleteAccount(newaccount);
    }

    @Test
    public void testGetAccounts() throws SQLException {
        ArrayList<Account> exp = new ArrayList<>();
        assertEquals(exp, accountDao.getAccounts());
        assertTrue(accountDao.createAccount(newaccount));
        exp.add(newaccount);
        assertEquals(exp, accountDao.getAccounts());
        accountDao.deleteAccount(newaccount);
    }
}
