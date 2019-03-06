package server;

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
        //AccountDao test = mock(AccountDao.class);
        AccountDao db = new AccountDao();
        LoginController lc = new LoginController();
        db.putUser("1",usertest);
        assertNotNull(lc.logIn("1:1"));
        AccountDao.removeUser("1");
    }**/
    
}
