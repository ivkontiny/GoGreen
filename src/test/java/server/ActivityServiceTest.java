package server;

import database.ActivityDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import pojos.Activity;
import services.ActivityService;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityServiceTest {

    private ActivityService as = new ActivityService();
    private ActivityDao ad = mock(ActivityDao.class);
    Activity testactivity = new Activity();

    @Before
    public void configure()
    {
        as.setDb(ad);
    }

    @Test
    public void testActivityServiceOk() throws SQLException
    {
        ArrayList<Activity> testactivity = new ArrayList<Activity>();
        testactivity.add(new Activity());
        when(ad.getActivities("user")).thenReturn(testactivity);
        assertEquals(1,as.getActivities("user").size());
    }

    @Test
    public void testActivityServiceFailed() throws SQLException
    {
        when(ad.getActivities("test")).thenReturn(null);
        assertNull(as.getActivities("test"));

    }
    @Test
    public void testCreateActivity() throws SQLException
    {
        when(ad.createActivity(Matchers.any(Activity.class))).thenReturn(true);
        assertTrue(as.createActivity(testactivity));
        when(ad.createActivity(Matchers.any(Activity.class))).thenReturn(false);
        assertFalse(as.createActivity(testactivity));
    }

    @Test
    public void testHandleException() throws SQLException
    {
        when(ad.getActivities("username")).thenThrow(new SQLException());
        when(ad.createActivity(Matchers.any(Activity.class))).thenThrow(new SQLException());
        assertFalse(as.createActivity(testactivity));
        assertNull(as.getActivities("username"));
    }

}
