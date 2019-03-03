package server;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LogInTest {

    @Test
    public void TestLogInFailed()
    {
        assertNull(LoginController.logingin("1:1"));
    }

    @Test
    public void TestLogInOK()
    {
        //Dao test = mock(Dao.class);
        User usertest = new User("1","1","1","1","1");
        Dao.putuser("1",usertest);
        assertNotNull(LoginController.logingin("1:1"));
    }
    
}
