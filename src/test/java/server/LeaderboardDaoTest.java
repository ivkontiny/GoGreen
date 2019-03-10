package server;

import database.LeaderboardDao;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LeaderboardDaoTest {

    private LeaderboardDao ld = new LeaderboardDao();
    @Test
    public void getPoints()
    {
        ld.resetPoints("test");
        assertEquals(ld.getPoints("test"), 0);
        ld.addPoints(1,"test");
        assertEquals(ld.getPoints("test"), 1);
    }

    @Test
    public void getLeaderboard()
    {
        assertEquals(0,ld.getLeaderboard("not_existing_user").getUsernames().size());
    }
}
