package server;

import database.AccountDao;
import org.junit.Before;
import org.junit.Test;
import services.AccountService;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private AccountDao db = mock(AccountDao.class);
    private AccountService as = new AccountService();

    @Before
    public void configure()
    {
        as.setDb(db);
    }

    @Test
    public void testExistsAccount() throws SQLException
    {
        when(db.exists("username")).thenReturn(true);
        assertTrue(as.userExists("username"));
    }
}
