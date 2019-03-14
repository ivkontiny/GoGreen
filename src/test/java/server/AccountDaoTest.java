package server;

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
        assertFalse(accountDao.exists("user_test"));
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


}
