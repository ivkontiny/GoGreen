package server;

import org.junit.Before;
import org.junit.Test;
import pojos.Leaderboard;
import services.LeaderboardService;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LeaderboardControllerTest {

    private LeaderboardController lc = new LeaderboardController();
    private LeaderboardService ls = mock(LeaderboardService.class);

    @Before
    public void configure()
    {
        lc.setLs(ls);
    }

    @Test
    public void testGetAdd()
    {
        Leaderboard testleaderboard = new Leaderboard(new ArrayList<String>(), new ArrayList<Integer>());
        when(ls.getLeaderboard(anyString())).thenReturn(testleaderboard);
        assertEquals(testleaderboard,lc.getLeaderboard("username"));
        doNothing().when(ls).addPoints(anyInt(),anyString());
        assertTrue(lc.addPoints("username"));

    }

}
