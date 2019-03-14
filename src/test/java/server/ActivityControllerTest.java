package server;

import org.junit.Test;
import pojos.Activity;
import services.AccountService;
import services.ActivityService;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityControllerTest {


    @Test
    public void testGetActivity()
    {
        Activity testactivity = new Activity();
        ActivityService test = mock(ActivityService.class);
        ActivityController ac = new ActivityController();
        ac.as  = test;
        when(test.getActivity("ate lunch")).thenReturn(testactivity);
        assertEquals(ac.getActivity("ate lunch"), testactivity);
    }
}
