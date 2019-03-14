package server;

import org.junit.Test;
import pojos.Activity;
import services.AccountService;
import services.ActivityService;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityControllerTest {


    @Test
    public void testGetActivity() throws SQLException
    {

        ArrayList<Activity> testactivity = new ArrayList<>();
        ActivityService test = mock(ActivityService.class);
        ActivityController ac = new ActivityController();
        ac.as  = test;
        when(test.getActivities("test")).thenReturn(testactivity);
        assertEquals(ac.getActivities("test"), testactivity);
    }
}
