package client;

import org.junit.Before;
import pojos.Account;

public class ConnectTest {

    Account account;

    @Before public void initialize() {
        account = new Account("frank", "frank@mail.com", "pass", "Frank", "Mulder");
    }



    /**@Test
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
        account.setUsername("test");
        account.setPassword(String.valueOf("test".hashCode()));
        String value = Connect.serverLogin(account.getUsername(), account.getPassword());
        assertEquals("Logged in as test", value);
    }**/

    /**
    @Test
    public void testRegisterTrue() {
        assertTrue(Connect.serverRegister(account));
        AccountDao ad = new AccountDao();
        ad.deleteAccount(account);
    }

    @Test
    public void testRegisterFalse() {
        account.setUsername("azaidman");
        assertFalse(Connect.serverRegister(account));
    }

    @Test
    public void testLoginTrue() {
        account.setUsername("azaidman");
        account.setPassword("48690");
        assertTrue(Connect.serverLogin(account.getUsername(), account.getPassword()));
    }

    @Test
    public void testLoginFalse() {
        account.setPassword("456");
        assertFalse(Connect.serverLogin(account.getUsername(), account.getPassword()));
    }
    */

}