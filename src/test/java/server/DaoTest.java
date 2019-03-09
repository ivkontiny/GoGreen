package server;

import database.AccountDao;
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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class DaoTest {


    private AccountDao accountDao;
    private FriendshipDao friendshipDao;
    private Account newaccount;

    @Before
    public void configure()
    {
        newaccount = new Account("user_test","user","user","user","user");
    }
    @Test
    public void CreateExists()
    {

        accountDao = new AccountDao();
        accountDao.deleteAccount(newaccount);
        assertFalse(accountDao.exists("user_test"));
        assertTrue(accountDao.createAccount(newaccount));
        assertFalse(accountDao.createAccount(newaccount));
        assertTrue(accountDao.exists("user_test"));
        accountDao.deleteAccount(newaccount);

    }

    @Test
    public void Get()
    {
        accountDao = new AccountDao();
        accountDao.deleteAccount(newaccount);
        assertNull(accountDao.getAccount("user_test"));
        accountDao.createAccount(newaccount);
        assertNotNull(accountDao.getAccount("user_test"));
        accountDao.deleteAccount(newaccount);

    }

}
