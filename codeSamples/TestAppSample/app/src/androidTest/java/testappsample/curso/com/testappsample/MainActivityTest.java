package testappsample.curso.com.testappsample;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrador on 6/12/2017.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = activityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch()
    {
        boolean result = false;

        View view = mainActivity.findViewById(R.id.user);

        assertNotNull(view);


        assertFalse(activityActivityTestRule.getActivity().CheckLogin("user1", "pass1"));
        assertTrue(activityActivityTestRule.getActivity().CheckLogin("walter", "admin"));
    }

    @Test
    public void LoginFail()
    {
        String[] array = new String[]{"walter", "admin"};
        View user = mainActivity.findViewById(R.id.user);
        View password = mainActivity.findViewById(R.id.password);

    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}