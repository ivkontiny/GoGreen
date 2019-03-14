package server;

import database.AccountDao;
import database.ActivityDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Account;
import pojos.Activity;
import services.AccountService;
import services.ActivityService;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    private AccountController ac = new AccountController();
    @Before
    public void configure() {}

    @Test
    public void testLogInOk() throws SQLException {
        AccountService test = mock(AccountService.class);
        when(test.checkLogin("user", "password")).thenReturn(true);
        ac.setLs(test);
        assertNotNull(ac.logIn("user:password"));
    }
    @Test
    public void testLogInFailed() throws SQLException
    {
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        when(test.checkLogin("user", "password")).thenReturn(false);
        ac.setLs(test);
        assertNull(ac.logIn("user:password"));
    }
    @Test
    public void testGetAccountNotFound () throws SQLException
    {
        AccountDao test = mock(AccountDao.class);
        AccountController ac = new AccountController();
        when(test.getAccount("user")).thenReturn(null);
        ac.setDb(test);
        assertNull(ac.getAccounts("user"));
    }
    @Test
    public void GetAccountFound() throws SQLException
    {
        AccountDao test = mock(AccountDao.class);
        AccountController ac = new AccountController();
        when(test.getAccount("user")).thenReturn(new Account());
        ac.setDb(test);
        assertNotNull(ac.getAccounts("user"));
    }
    @Test
    public void testRegisterUserOk() throws SQLException
    {
        Account accounttoadd = new Account("user","user","user","user","user");
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        ac.setLs(test);
        when(test.createAccount(accounttoadd)).thenReturn(true);
        assertTrue(ac.registerUser("username",accounttoadd));
    }
    @Test
    public void testRegisterUserFailed() throws SQLException
    {
        Account accounttoadd = new Account("user","user","user","user","user");
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        ac.setLs(test);
        when(test.createAccount(accounttoadd)).thenReturn(false);
        assertFalse(ac.registerUser("username",accounttoadd));
    }

    @Test
    public void testAccountServiceOk() throws SQLException
    {
        Account testaccount = new Account("user", "user", "password", "user", "user");
        AccountDao test = mock(AccountDao.class);
        AccountService as = new AccountService();
        as.setDb(test);
        when(test.exists("user")).thenReturn(true);
        when(test.getAccount("user")).thenReturn(testaccount);
        assertTrue(as.checkLogin("user", "password"));
    }

    @Test
    public void testAccountServiceFailed() throws SQLException
    {
        //Account testaccount = new Account("user", "user", "password", "user", "user");
        AccountDao test = mock(AccountDao.class);
        AccountService as = new AccountService();
        as.setDb(test);
        when(test.exists("user")).thenReturn(false);
        //when(test.getAccount("user")).thenReturn(testaccount);
        assertFalse(as.checkLogin("user", "password"));
    }

    @Test
    public void testAccountServiceCreateFailed() throws SQLException
    {
        Account testaccount = new Account("user", "user", "password", "user", "user");
        AccountDao test = mock(AccountDao.class);
        AccountService as = new AccountService();
        as.setDb(test);
        when(test.exists("user")).thenReturn(true);
        when(test.createAccount(testaccount)).thenReturn(false);
        assertFalse(as.createAccount(testaccount));
    }

    @Test
    public void testAccountServiceCreateOk() throws SQLException
    {
        Account testaccount = new Account("user", "user", "password", "user", "user");
        AccountDao test = mock(AccountDao.class);
        AccountService as = new AccountService();
        as.setDb(test);
        when(test.exists("user")).thenReturn(false);
        when(test.createAccount(testaccount)).thenReturn(true);
        assertTrue(as.createAccount(testaccount));
    }

    @Test
    public void testActivityServiceOk()
    {
        ActivityDao test = mock(ActivityDao.class);
        ActivityService acts = new ActivityService();
        acts.setDb(test);
        ArrayList<Activity> testactivity = new ArrayList<Activity>();
        testactivity.add(new Activity());
        when(test.getActivities("user")).thenReturn(testactivity);
        assertEquals(1,acts.getActivities("user").size());

    }

    @Test
    public void testActivityServiceFailed()
    {
        ActivityDao test = mock(ActivityDao.class);
        ActivityService acts = new ActivityService();
        acts.setDb(test);
        //Activity testactivity = new Activity();
        when(test.getActivities("test")).thenReturn(null);
        assertNull(acts.getActivities("test"));

    }

}


