package server;

import database.AccountDao;
import org.junit.Assert;
import org.junit.Test;
import pojos.Account;
import services.AccountService;

import javax.validation.constraints.AssertTrue;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogInTest {

    @Test
    public void LogInOK()
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
    public void RegisterUserOK()
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
}
