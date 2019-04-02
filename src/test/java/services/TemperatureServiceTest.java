package services;

import database.AccountDao;
import org.junit.Test;
import pojos.Account;

import static org.junit.Assert.*;

public class TemperatureServiceTest {

    @Test
    public void testInit() {
        Account account = new Account("test", "test", "test", "test", "test");
        AccountDao ad = new AccountDao();
        ad.changeDatabase("test");
        AccountService as = new AccountService();
        as.setDb(ad);
        TemperatureService.init();

        as.createAccount(account);
        TemperatureService.init();

        TemperatureService.init();

        as.deleteAccount(account);
    }

}