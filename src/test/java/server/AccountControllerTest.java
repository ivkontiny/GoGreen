package server;

import database.AccountDao;
import database.ActivityDao;
import org.junit.Before;
import org.junit.Test;
import pojos.Account;
import pojos.Activity;
import services.AccountService;
import services.ActivityService;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    private AccountController ac = new AccountController();
    @Before
    public void configure() {}

    @Test
    public void testLogInOk()
    {
        AccountService test = mock(AccountService.class);
        when(test.checkLogin("user", "password")).thenReturn(true);
        ac.setLs(test);
        assertNotNull(ac.logIn("user:password"));
    }
    @Test
    public void testLogInFailed()
    {
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        when(test.checkLogin("user", "password")).thenReturn(false);
        ac.setLs(test);
        assertNull(ac.logIn("user:password"));
    }
    @Test
    public void testGetAccountNotFound()
    {
        AccountDao test = mock(AccountDao.class);
        AccountController ac = new AccountController();
        when(test.getAccount("user")).thenReturn(null);
        ac.setDb(test);
        assertNull(ac.getAccounts("user"));
    }
    @Test
    public void GetAccountFound()
    {
        AccountDao test = mock(AccountDao.class);
        AccountController ac = new AccountController();
        when(test.getAccount("user")).thenReturn(new Account());
        ac.setDb(test);
        assertNotNull(ac.getAccounts("user"));
    }
    @Test
    public void testRegisterUserOk()
    {
        Account accounttoadd = new Account();
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        ac.setLs(test);
        when(test.createAccount(accounttoadd)).thenReturn(true);
        assertTrue(ac.registerUser("username",accounttoadd));
    }
    @Test
    public void testRegisterUserFailed()
    {
        Account accounttoadd = new Account();
        AccountService test = mock(AccountService.class);
        AccountController ac = new AccountController();
        ac.setLs(test);
        when(test.createAccount(accounttoadd)).thenReturn(false);
        assertFalse(ac.registerUser("username",accounttoadd));
    }

    @Test
    public void testAccountServiceOk()
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
    public void testAccountServiceFailed()
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
    public void testAccountServiceCreateFailed()
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
    public void testAccountServiceCreateOk()
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
        Activity testactivity = new Activity();
        when(test.getActivity("lunch")).thenReturn(testactivity);
        assertEquals(testactivity,acts.getActivity("lunch"));

    }

    @Test
    public void testActivityServiceFailed()
    {
        ActivityDao test = mock(ActivityDao.class);
        ActivityService acts = new ActivityService();
        acts.setDb(test);
        //Activity testactivity = new Activity();
        when(test.getActivity("lunch")).thenReturn(null);
        assertNull(acts.getActivity("lunch"));

    }

}


