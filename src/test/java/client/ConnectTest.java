package client;

import org.junit.Before;
import org.junit.Test;
import util.Account;

import static org.junit.Assert.*;

public class ConnectTest {

    Account account;

    @Before public void initialize() {
        account = new Account("john", "john@mail.com", "pass", "John", "Baker");
    }



    @Test
    public void testRegisterTrue() {
        assertTrue(Connect.serverRegister(account));
    }

    @Test
    public void testRegisterFalse() {
        account.setUsername("username1");
        assertFalse(Connect.serverRegister(account));

    }

    @Test
    public void testLoginFalse() {
        account.setUsername("jack");
        String value = Connect.serverLogin(account.getUsername(), account.getPassword());
        assertEquals("Login Failed", value);
    }

    @Test
    public void testLoginTrue() {
        account.setUsername("username1");
        account.setPassword(String.valueOf("account".hashCode()));
        String value = Connect.serverLogin(account.getUsername(), account.getPassword());
        assertEquals("Logged in as username1", value);
    }

}