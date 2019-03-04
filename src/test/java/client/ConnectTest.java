package client;

import org.junit.Before;
import org.junit.Test;
import util.User;

import static org.junit.Assert.*;

public class ConnectTest {

    User user;

    @Before public void initialize() {
        user = new User("john", "john@mail.com", "pass", "John", "Baker");
    }



    @Test
    public void testRegisterTrue() {
        assertTrue(Connect.serverRegister(user));
    }

    @Test
    public void testRegisterFalse() {
        user.setUsername("username1");
        assertFalse(Connect.serverRegister(user));

    }

    @Test
    public void testLoginFalse() {
        user.setUsername("jack");
        String value = Connect.serverLogin(user.getUsername(), user.getPassword());
        assertEquals("Login Failed", value);
    }

    @Test
    public void testLoginTrue() {
        user.setUsername("username1");
        user.setPassword(String.valueOf("user".hashCode()));
        String value = Connect.serverLogin(user.getUsername(), user.getPassword());
        assertEquals("Logged in as username1", value);
    }

}