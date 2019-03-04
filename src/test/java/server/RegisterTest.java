package server;

import org.junit.Test;
import util.User;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterTest {

    @Test
    public void RegisterOK()
    {
        User test = mock(User.class);
        //user1 does not exist yet
        assertFalse(Dao.getAllUsers().containsKey("user1"));
        when(test.getUsername()).thenReturn("user1");
        //we add user1
        RegisterController.RegisterUser("user1", test);
        //now user1 exists
        assertTrue(Dao.getAllUsers().containsKey("user1"));

        Dao.removeUser("user1");
    }

    @Test
    public void RegisterFAILED()
    {
        User test = mock(User.class);
        when(test.getUsername()).thenReturn("user1");
        //we add user2
        RegisterController.RegisterUser("user1", test);
        //now user2 is stored in the server
        assertTrue(Dao.getAllUsers().containsKey("user1"));
        //no other user should be able to register with user2
        assertNull(RegisterController.RegisterUser("user1",test));

        Dao.removeUser("user1");

    }


}
