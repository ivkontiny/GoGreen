package client;

import org.junit.Before;
import org.junit.Test;
import util.User;

import static org.junit.Assert.*;

public class UserTest {

    User user;

    @Before public void initialize() {
        user = new User("john", "john@mail.com", "alala", "John", "Baker");
    }

    @Test
    public void testConstructor() {
        assertEquals("john", user.getUsername());
    }

    @Test
    public void testUserName() {
        assertEquals("john", user.getUsername());
    }

    @Test
    public void testMail() {
        assertEquals("john@mail.com", user.getMail());
    }

    @Test
    public void testPassword() {
        assertEquals("alala", user.getPassword());
    }

    @Test
    public void testName() {
        assertEquals("John", user.getName());
    }

    @Test
    public void testSurname() {
        assertEquals("Baker", user.getSurname());
    }

    @Test
    public void testSetUserName() {
        user.setUsername("jack");
        assertEquals("jack", user.getUsername());
    }

    @Test
    public void testSetMail() {
        user.setMail("jack@mail.com");
        assertEquals("jack@mail.com", user.getMail());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("baba");
        assertEquals("baba", user.getPassword());
    }

    @Test
    public void testSetName() {
        user.setName("Jack");
        assertEquals("Jack", user.getName());
    }

    @Test
    public void testSetSurname() {
        user.setSurname("Cooker");
        assertEquals("Cooker", user.getSurname());
    }

    @Test
    public void testDefaultConstructor() {
        user = new User();
        assertNotNull(user);
    }
}