package server;

import database.AccountDao;
import database.ActivityDao;
import org.junit.Test;
import pojos.Account;
import pojos.Activity;
import services.AccountService;
import services.ActivityService;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogInTest {

    @Test
    public void LogInOk()
    {
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        when(test.checkLogin("user", "password")).thenReturn(true);
        ac.ls = test;
        assertNotNull(ac.logIn("user:password"));
    }
    @Test
    public void LogInFailed()
    {
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        when(test.checkLogin("user", "password")).thenReturn(false);
        ac.ls = test;
        assertNull(ac.logIn("user:password"));
    }
    @Test
    public void GetAccountNotFound()
    {
        AccountDao test = mock(AccountDao.class);
        AccountController ac = new AccountController();
        when(test.getAccount("user")).thenReturn(null);
        ac.db = test;
        assertNull(ac.getAccounts("user"));
    }
    @Test
    public void GetAccountFound()
    {
        AccountDao test = mock(AccountDao.class);
        AccountController ac = new AccountController();
        when(test.getAccount("user")).thenReturn(new Account());
        ac.db = test;
        assertNotNull(ac.getAccounts("user"));
    }
    @Test
    public void RegisterUserOk()
    {
        Account accounttoadd = new Account();
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        ac.ls = test;
        when(test.createAccount(accounttoadd)).thenReturn(true);
        assertTrue(ac.registerUser("username",accounttoadd));
    }
    @Test
    public void RegisterUserFailed()
    {
        Account accounttoadd = new Account();
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        ac.ls = test;
        when(test.createAccount(accounttoadd)).thenReturn(false);
        assertFalse(ac.registerUser("username",accounttoadd));
    }

    @Test
    public void AccountServiceOk()
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
    public void AccountServiceFailed()
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
    public void AccountServiceCreateFailed()
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
    public void AccountServiceCreateOk()
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
    public void ActivityServiceOk()
    {
        ActivityDao test = mock(ActivityDao.class);
        ActivityService acts = new ActivityService();
        acts.setDb(test);
        Activity testactivity = new Activity();
        when(test.getActivity("lunch")).thenReturn(testactivity);
        assertEquals(testactivity,acts.getActivity("lunch"));

    }

    @Test
    public void ActivityServiceFailed()
    {
        ActivityDao test = mock(ActivityDao.class);
        ActivityService acts = new ActivityService();
        acts.setDb(test);
        Activity testactivity = new Activity();
        when(test.getActivity("lunch")).thenReturn(null);
        assertNull(acts.getActivity("lunch"));

    }

}


