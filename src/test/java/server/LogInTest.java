package server;

import database.Dao;
import org.junit.Before;
import org.junit.Test;
import pojos.Account;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LogInTest {

    /**Account usertest;

    @Before
    public void initialize()
    {
        usertest = new Account("1","1","1","1","1");
    }
    @Test
    public void TestLogInFailed()
    {
        LoginController lc = new LoginController();
        assertNull(lc.logIn("1:1"));
    }
    @Test
    public void TestLogInOK()
    {
        //Dao test = mock(Dao.class);
        Dao db = new Dao();
        LoginController lc = new LoginController();
        db.putUser("1",usertest);
        assertNotNull(lc.logIn("1:1"));
        Dao.removeUser("1");
    }**/
    
}
