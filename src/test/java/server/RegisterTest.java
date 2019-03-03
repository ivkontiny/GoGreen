package server;

import org.junit.Test;

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
        assertFalse(Dao.getAllUsers().containsKey("user1"));
        when(test.getUsername()).thenReturn("user1");
        RegisterController.newuser("user1", test);
        assertTrue(Dao.getAllUsers().containsKey("user1"));
    }

    @Test
    public void RegisterFAILED()
    {
        User test = mock(User.class);
        when(test.getUsername()).thenReturn("user2");
        RegisterController.newuser("user2", test);
        assertTrue(Dao.getAllUsers().containsKey("user2"));
        assertNull(RegisterController.newuser("user2",test));

    }


}
