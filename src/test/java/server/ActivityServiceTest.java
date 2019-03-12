package server;

import database.ActivityDao;
import org.junit.Test;
import org.mockito.Matchers;
import pojos.Activity;
import services.ActivityService;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityServiceTest {

    private ActivityService as = new ActivityService();
    private ActivityDao ad = mock(ActivityDao.class);
    Activity testactivity = new Activity();

    @Test
    public void testCreateActivity()
    {
        as.setDb(ad);
        when(ad.createActivity(Matchers.any(Activity.class))).thenReturn(true);
        assertTrue(as.createActivity(testactivity));
        when(ad.createActivity(Matchers.any(Activity.class))).thenReturn(false);
        assertFalse(as.createActivity(testactivity));
    }

}
